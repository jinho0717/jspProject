package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ProjectMemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private final String NS = "memberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	@Override
	public int register(ProjectMemberVO mvo) {
		log.info("register check3");
		int isOk = sql.insert(NS+"add", mvo);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}
	@Override
	public ProjectMemberVO login(ProjectMemberVO mvo) {
		log.info("login check3");
		
		return sql.selectOne(NS+"login", mvo);
	}
	@Override
	public int logout(String id) {
		log.info("logout check3");
		int isOk = sql.update(NS+"last", id);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}
	@Override
	public List<ProjectMemberVO> list() {
		log.info("list check3");
		return sql.selectList(NS+"list");
	}
	@Override
	public int update(ProjectMemberVO mvo) {
		log.info("update check3");
		int isOk = sql.update(NS+"update", mvo);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}
	@Override
	public int remove(String id) {
		log.info("remove check3");
		int isOk = sql.delete(NS+"del", id);
		if (isOk>0) {
			sql.commit();
		}
		return isOk;
	}

}
