package service;

import java.util.List;

import domain.ProjectCommentVO;

public interface CommentService {

	int post(ProjectCommentVO cvo);

	List<ProjectCommentVO> getList(int bno);

	int modify(ProjectCommentVO cvo);

	int remove(int cno);

}
