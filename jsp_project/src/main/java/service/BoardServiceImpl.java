package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import domain.ProjectBoardVO;
import domain.ProjectPagingVO;

public class BoardServiceImpl implements Service {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}
	@Override
	public int register(ProjectBoardVO bvo) {
		log.info("insert check2");
		return bdao.insert(bvo);
	}
	@Override
	public List<ProjectBoardVO> list() {
		log.info("list check2");
		return bdao.list();
	}
	@Override
	public ProjectBoardVO detail(int bno) {
		log.info("detail check2");
		return bdao.detail(bno);
	}
	@Override
	public int readCount(int bno) {
		log.info("readcount check2");
		return bdao.readcount(bno);
	}
	@Override
	public int modify(ProjectBoardVO bvo) {
		log.info("modify check2");
		return bdao.modify(bvo);
	}
	@Override
	public int remove(int bno) {
		log.info("remove check2");
		CommentServiceImpl csv = new CommentServiceImpl();
		int cnt =csv.commentCount(bno);
		csv.deleteAll(bno);
		int isOk = csv.deleteAll(bno);
		return bdao.remove(bno);
	}
	@Override
	public int getTotalCount(ProjectPagingVO pgvo) {
		log.info("totalCount check2");
		return bdao.getTotalCount(pgvo);
	}
	@Override
	public List<ProjectBoardVO> getPageList(ProjectPagingVO pgvo) {
		log.info("pagelist check2");
		return bdao.getPageList(pgvo);
	}
	@Override
	public String getFileName(int bno) {
		log.info("FileName check2");
		return bdao.getFileName(bno);
	}
	@Override
	public List<ProjectBoardVO> mylist(String writer) {
		log.info("mylist check2");
		return bdao.mylist(writer);
	}

}
