package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ProjectMemberVO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(memberController.class);
	
	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	
	private MemberService msv;
    
    public memberController() {
    	msv = new MemberServiceImpl();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path>>"+ path);
		
		switch(path) {
		case "join" : 
			log.info("회원가입");
			destPage = "/member/join.jsp";
			break;
		case "register" : 
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String emailString = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				ProjectMemberVO mvo = new ProjectMemberVO(id, pwd, emailString, age);
				log.info("register check1"+ mvo);
				isOk = msv.register(mvo);
				log.info("register check4"+((isOk>0)? "OK":"FAIL"));
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("register error");
				e.printStackTrace();
			}
			break;
		case "login" : 
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				ProjectMemberVO mvo = new ProjectMemberVO(id, pwd);
				log.info("login check1");
				
				ProjectMemberVO loginmvo = msv.login(mvo);
				
				if (loginmvo != null) {
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginmvo);
					ses.setMaxInactiveInterval(30*60);
				}else {
					request.setAttribute("msg_login", 0);
				}
				log.info("login check4");
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("login error");
				e.printStackTrace();
			}
			break;
		case "logout" : 
			try {
				HttpSession ses = request.getSession();
				ProjectMemberVO mvo = (ProjectMemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				log.info("logout check1");
				isOk = msv.lastLogin(id);
				ses.invalidate();
				log.info("logout check4");
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("logout error");
				e.printStackTrace();
			}
			break;	
		case "list" : 
			try {
				List<ProjectMemberVO> list = msv.list();
				log.info("list check1");
				request.setAttribute("list", list);
				log.info("list check4");
				destPage = "/member/list.jsp";
			} catch (Exception e) {
				log.info("list error");
				e.printStackTrace();
			}
			break;	
		case "modify" : 
			destPage = "/member/modify.jsp";
			break;	
		case "update" : 
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				
				ProjectMemberVO mvo = new ProjectMemberVO(id, pwd, email, age);
				log.info("update check1");
				isOk = msv.update(mvo);
				log.info("update check4");
				destPage = "logout";
			} catch (Exception e) {
				log.info("update error");
				e.printStackTrace();
			}
			break;
		case "remove" : 
			try {
				HttpSession ses = request.getSession();
				ProjectMemberVO mvo = (ProjectMemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				log.info("remove check1");
				isOk = msv.remove(id);
				log.info("remove check4");
				ses.invalidate();
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("remove error");
				e.printStackTrace();
			}
			break;	
		}
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
