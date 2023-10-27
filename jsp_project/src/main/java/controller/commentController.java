package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ProjectCommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class commentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = LoggerFactory.getLogger(commentController.class);
	
	private CommentService csv;
	private int isOk;
    
    public commentController() {
    csv = new CommentServiceImpl();    
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		
		String pathUri = uri.substring("/cmt/".length());
		String path = pathUri;
		String pathvar = "";
		if (path.contains("/")) {
			path = pathUri.substring(0,pathUri.lastIndexOf("/"));
			pathvar = pathUri.substring(pathUri.lastIndexOf("/")+1);
		}
		log.info(">>uri> "+uri);
		log.info(">>pathUri> "+pathUri);
		log.info(">>path> "+path);
		log.info(">>pathvar> "+pathvar);
	
		switch(path) {
		
		case "post" : 
			try {
				StringBuffer sb = new StringBuffer();
				String  line = "";
				BufferedReader br = request.getReader();
				while((line = br.readLine())!=null) {
					sb.append(line);
				}
				log.info(">>sb : "+ sb.toString());
				
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject)parser.parse(sb.toString());
				
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				
				ProjectCommentVO cvo = new ProjectCommentVO(bno, writer, content);
				log.info(">>cvo> "+cvo);
				isOk = csv.post(cvo);
				log.info((isOk>0)? "OK" : "FAIL");
				
				PrintWriter out = response.getWriter();
				out.print(isOk);
				
			} catch (Exception e) {
				log.info("comment  > post > error");
				e.printStackTrace();
			}
			break;
		case "list" : 
			try {
				int bno = Integer.parseInt(pathvar);
				List<ProjectCommentVO> list = csv.getList(bno);
				log.info(">> comment> List >"+list);
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				
				JSONArray jsonList = new JSONArray();
				for(int i=0; i<list.size(); i++) {
					jsonObjArr[i] = new JSONObject();
					jsonObjArr[i].put("cno", list.get(i).getCno());
					jsonObjArr[i].put("bno", list.get(i).getBno());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("regdate", list.get(i).getRegdate());
				
					jsonList.add(jsonObjArr[i]);
				}
				String jsonData = jsonList.toJSONString();
				PrintWriter out = response.getWriter();
				out.print(jsonData);
			} catch (Exception e) {
				log.info(">> comment > list > error");
				e.printStackTrace();
			}
			break;	
		case "modify" : 
			try {
				StringBuffer sb = new StringBuffer();
				String line = "";
				
				BufferedReader br = request.getReader();
				while((line=br.readLine())!=null) {
					sb.append(line);
				}
				log.info(">>sb > "+ sb.toString());
				JSONParser parser = new JSONParser();
				JSONObject jsonobj = (JSONObject)parser.parse(sb.toString());
				int cno = Integer.parseInt(jsonobj.get("cno").toString());
				String writer = jsonobj.get("writer").toString();
				String content = jsonobj.get("content").toString();
				
				ProjectCommentVO cvo = new ProjectCommentVO(cno, content);
				isOk = csv.modify(cvo);
				log.info(">> modify > "+((isOk>0)? "OK":"FAIL"));
				PrintWriter out = response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info("comment > modify > error");
				e.printStackTrace();
			}
			break;	
		case "remove" : 
			try {
				int cno = Integer.parseInt(pathvar);
				isOk = csv.remove(cno);
				log.info("remove check1");
				log.info(">> remove >"+ ((isOk>0)? "OK":"FAIL"));
				PrintWriter out = response.getWriter();
				out.print(isOk);
				log.info("remove check4");
			} catch (Exception e) {
				log.info("comment remove error");
				e.printStackTrace();
			}
			break;	
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service(request, response);
	}

}
