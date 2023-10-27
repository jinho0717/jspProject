package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ProjectBoardVO;
import domain.ProjectMemberVO;
import domain.ProjectPagingVO;
import handler.FileHandler;
import handler.ProjectPagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardServiceImpl;
import service.Service;


@WebServlet("/brd/*")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private static final Logger log = LoggerFactory.getLogger(boardController.class);
	
	private RequestDispatcher rdp;
	private Service bsv;
	
	private String destPage;
	
	private int isOk;
	private String savePath;
   
    public boardController() {
    	bsv = new BoardServiceImpl();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path>>"+path);
		
		switch(path) {
		
		case "register" :
			destPage = "/board/register.jsp";
			break;
		
//		case "insert" : 
//			try {
//				String title = request.getParameter("title");
//				String writer = request.getParameter("writer");
//				String content = request.getParameter("content");
//				log.info("insert check1");
//				ProjectBoardVO bvo = new ProjectBoardVO(title, writer, content);
//				isOk = bsv.register(bvo);
//				log.info((isOk>0)? "OK":"FAIL");
//				log.info("insert check4");
//				destPage="/index.jsp";
//			} catch (Exception e) {
//				log.info("insert error");
//				e.printStackTrace();
//				
//			}
//			break;
		case "insert" : 
			try {
				savePath = getServletContext().getRealPath("/_fileUpload");
				File filedir = new File(savePath);
				log.info("파일 저장 위치 : "+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(filedir);
				fileItemFactory.setSizeThreshold(2*1024*1024);
				ProjectBoardVO bvo = new ProjectBoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title" : 
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "writer" : 
						bvo.setWriter(item.getString("utf-8"));
						break;	
					case "content" : 
						bvo.setContent(item.getString("utf-8"));
						break;
					case "image_file" :
						if (item.getSize()>0) {
							String fileName = item.getName()
									.substring(item.getName().lastIndexOf("/")+1);
							fileName = System.currentTimeMillis()+"_"+fileName;
							
							File uploadFilePath = new File(filedir+File.separator+fileName);
							log.info("파일 경로+ 이름 : "+uploadFilePath);
							
							try {
								item.write(uploadFilePath);
								bvo.setImage_File(fileName);
								
								Thumbnails.of(uploadFilePath).size(60, 60).toFile(new File(filedir+File.separator
										+"_th_"+fileName));
							} catch (Exception e) {
								log.info(">>file writer on disk error");
								e.printStackTrace();
							}
						}
						
						isOk = bsv.register(bvo);
						log.info((isOk>0)? "OK":"FAIL");
						log.info("insert check4");
						destPage ="/index.jsp";
						break;	
					}
				}
				
			} catch (Exception e) {
				log.info("insert error");
				e.printStackTrace();
			}
			break;	
		case "list" :
			try {
				List<ProjectBoardVO> list = bsv.list();
				log.info("list check1");
				request.setAttribute("list", list);
				log.info("list check4");
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("list error");
				e.printStackTrace();
			}
			break;
		case "pageList" : 
			try {
				ProjectPagingVO pgvo = new ProjectPagingVO();
				if (request.getParameter("pageNo") != null) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					log.info("pageNo "+pageNo+" qty "+qty);
					pgvo = new ProjectPagingVO(pageNo, qty);
				}
				String type = request.getParameter("type");
				String keyword = request.getParameter("keyword");
				pgvo.setType(request.getParameter("type"));
				pgvo.setKeyword(keyword);
				log.info("type : "+ pgvo.getType()+", keyword : "+pgvo.getKeyword());
				
				int totalCount = bsv.getTotalCount(pgvo);
				log.info("전체 게시글 수"+totalCount);
				List<ProjectBoardVO> list = bsv.getPageList(pgvo);
				request.setAttribute("list", list);
				
				ProjectPagingHandler ph = new ProjectPagingHandler(totalCount, pgvo);
				request.setAttribute("ph", ph);
				log.info("paging 성공");
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("pageList error");
				e.printStackTrace();
			}
			break;	
		case "detail": 
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("detail check1");
				isOk = bsv.readCount(bno);
				ProjectBoardVO bvo = bsv.detail(bno);
				log.info("detail check4");
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}
			break;
		case "modify": 
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("modify check1");
				ProjectBoardVO bvo = bsv.detail(bno);
				request.setAttribute("bvo", bvo);
				log.info("modify check4");
				destPage = "/board/modify.jsp";
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;	
//		case "edit" : 
//			try {
//				int bno = Integer.parseInt(request.getParameter("bno"));
//				String title = request.getParameter("title");
//				String content = request.getParameter("content");
//				ProjectBoardVO bvo = new ProjectBoardVO(bno, title, content);
//				log.info("edit check1");
//				isOk = bsv.modify(bvo);
//				log.info((isOk>0)? "OK":"FAIL");
//				destPage = "detail?bno="+bno;
//			} catch (Exception e) {
//				log.info("edit error");
//				e.printStackTrace();
//			}
//			break;	
		case "edit" :
			try {
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2*1024*1024);
				
				ProjectBoardVO bvo = new ProjectBoardVO();
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">> update 준비 >");
				List<FileItem> itemList = fileUpload.parseRequest(request);
				String old_file = null; 
				
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "bno" : 
						bvo.setBno(Integer.parseInt(item.getString("utf-8")));
						break;
					case "title" : 
						bvo.setTitle(item.getString("utf-8"));
						break;	
					case "content" : 
						bvo.setContent(item.getString("utf-8"));
						break;	
					case "image_file":
						//수정 이전 파일
						old_file = item.getString("utf-8");
						break;
					case "new_file" : 
						if (item.getSize()>0) {
							if (old_file !=null) {
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);
							}
							String fileName = item.getName().substring(
									item.getName().lastIndexOf(File.separator)+1);
							log.info("new_fileName "+fileName);
							
							fileName = System.currentTimeMillis()+"_"+fileName;
							
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							try {
								item.write(uploadFilePath);
								bvo.setImage_File(fileName);
								
								Thumbnails.of(uploadFilePath).size(60, 60).toFile(fileDir+File.separator
										+"_th_"+fileName);
							} catch (Exception e) {
								log.info(">> New File save error");
								e.printStackTrace();
							}
						}else {
							bvo.setImage_File(old_file);
						break;	
					}
					
				}
				}
				isOk = bsv.modify(bvo);
				log.info((isOk>0)? "OK":"FAIL");
				destPage = "pageList";
			}catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
			}
			break;	
		case "remove" : 
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				String fileName = bsv.getFileName(bno);
				savePath = getServletContext().getRealPath("/_fileUpload");
				
				FileHandler filehandler = new FileHandler();
				isOk = filehandler.deleteFile(fileName, savePath);
				isOk = bsv.remove(bno);
				log.info("remove check4");
				destPage ="pageList";
			} catch (Exception e) {
				log.info("remove error");
				e.printStackTrace();
			}
			break;	
		case "mylist" : 
			try {
				ProjectBoardVO bvowriter = new ProjectBoardVO();
				String writer = request.getParameter("writer");
				log.info(writer);
				log.info("mylist check1");
				List<ProjectBoardVO> list = bsv.mylist(writer);
				request.setAttribute("list", list);
				log.info("mylist check4");
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("mylist error");
				e.printStackTrace();
			}
			break;	
		
		}
		rdp= request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
