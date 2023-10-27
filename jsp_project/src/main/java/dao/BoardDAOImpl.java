package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ProjectBoardVO;
import domain.ProjectPagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {

	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	private SqlSession sql;
	private final String NS = "BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql= DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(ProjectBoardVO bvo) {
		log.info("insert check3");
		int isOk = sql.insert(NS+"add", bvo);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<ProjectBoardVO> list() {
		log.info("list check3");
		return sql.selectList(NS+"list");
	}

	@Override
	public ProjectBoardVO detail(int bno) {
		log.info("detail check3");
		return sql.selectOne(NS+"detail", bno);
	}

	@Override
	public int readcount(int bno) {
		log.info("readcount check3");
		int isOk = sql.update(NS+"read", bno);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int modify(ProjectBoardVO bvo) {
		log.info("modify check3");
		int isOk = sql.update(NS+"modify", bvo);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int remove(int bno) {
		log.info("remove check3");
		int isOk = sql.delete(NS+"del", bno);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int getTotalCount(ProjectPagingVO pgvo) {
		log.info("totalcount check3");
		return sql.selectOne(NS+"cnt", pgvo);
	}

	@Override
	public List<ProjectBoardVO> getPageList(ProjectPagingVO pgvo) {
		log.info("pageList check3");
		return sql.selectList(NS+"page", pgvo);
	}

	@Override
	public String getFileName(int bno) {
		
		return sql.selectOne(NS+"fileName",bno);
	}

	@Override
	public List<ProjectBoardVO> mylist(String writer) {
		log.info("mylist check3");
		
		return sql.selectList(NS+"mylist", writer);
	}

}
