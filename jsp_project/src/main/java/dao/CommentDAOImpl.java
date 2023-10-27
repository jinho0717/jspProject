package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ProjectCommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {

	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private final String NS = "CommentMapper.";
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	
	
	@Override
	public int insert(ProjectCommentVO cvo) {
		log.info("comment insert check3");
		int isOk = sql.insert(NS+"add",cvo);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}



	@Override
	public List<ProjectCommentVO> getList(int bno) {
		log.info("comment list check3");
		
		return sql.selectList(NS+"list", bno);
	}



	@Override
	public int modify(ProjectCommentVO cvo) {
		log.info("comment modify check3");
		isOk = sql.update(NS+"update", cvo);
		if (isOk>0)	 sql.commit();
		return isOk;
	}



	@Override
	public int remove(int cno) {
		log.info("comment remove check3");
		int isOk = sql.delete(NS+"delete", cno);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}



	@Override
	public int removeAll(int bno) {
		isOk = sql.delete(NS+"deleteAll", bno);
		if (isOk>0)	 {
			sql.commit();
		}
		return isOk;
	}



	@Override
	public int commentCount(int bno) {
		
		return sql.selectOne(NS+"cnt",bno);
	}

}
