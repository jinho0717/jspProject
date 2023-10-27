package dao;

import java.util.List;

import domain.ProjectMemberVO;

public interface MemberDAO {

	int register(ProjectMemberVO mvo);

	ProjectMemberVO login(ProjectMemberVO mvo);

	int logout(String id);

	List<ProjectMemberVO> list();

	int update(ProjectMemberVO mvo);

	int remove(String id);

}
