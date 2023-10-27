package service;

import java.util.List;

import domain.ProjectMemberVO;

public interface MemberService {

	int register(ProjectMemberVO mvo);

	ProjectMemberVO login(ProjectMemberVO mvo);

	int lastLogin(String id);

	List<ProjectMemberVO> list();

	int update(ProjectMemberVO mvo);

	int remove(String id);

	

}
