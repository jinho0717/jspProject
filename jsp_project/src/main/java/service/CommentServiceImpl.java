package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.CommentDAO;
import dao.CommentDAOImpl;
import domain.ProjectCommentVO;

public class CommentServiceImpl implements CommentService {

	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}
	@Override
	public int post(ProjectCommentVO cvo) {
		log.info("comment check2");
		return cdao.insert(cvo);
	}
	@Override
	public List<ProjectCommentVO> getList(int bno) {
		log.info("commentlist check2 ");
		return cdao.getList(bno);
	}
	@Override
	public int modify(ProjectCommentVO cvo) {
		log.info("comment modify check2");
		return cdao.modify(cvo);
	}
	@Override
	public int remove(int cno) {
		log.info("comment remove check2");
		return cdao.remove(cno);
	}
	public int commentCount(int bno) {
		// TODO Auto-generated method stub
		return cdao.commentCount(bno);
	}
	public int deleteAll(int bno) {
		
		return cdao.removeAll(bno);
	}
	

}
