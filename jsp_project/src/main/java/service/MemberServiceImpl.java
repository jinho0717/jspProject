package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import domain.ProjectMemberVO;

public class MemberServiceImpl implements MemberService {

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl(); 
	}
	
	@Override
	public int register(ProjectMemberVO mvo) {
		log.info("register check2");
		return mdao.register(mvo);
	}

	@Override
	public ProjectMemberVO login(ProjectMemberVO mvo) {
		log.info("login check2");
		return mdao.login(mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info("logout check2");
		return mdao.logout(id);
	}

	@Override
	public List<ProjectMemberVO> list() {
		log.info("list check2");
		return mdao.list();
	}

	@Override
	public int update(ProjectMemberVO mvo) {
		log.info("update check2");
		return mdao.update(mvo);
	}

	@Override
	public int remove(String id) {
		log.info("remove check2");
		return mdao.remove(id);
	}

	

}
