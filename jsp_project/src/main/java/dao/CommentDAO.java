package dao;

import java.util.List;

import domain.ProjectCommentVO;

public interface CommentDAO {

	int insert(ProjectCommentVO cvo);

	List<ProjectCommentVO> getList(int bno);

	int modify(ProjectCommentVO cvo);

	int remove(int cno);

	int removeAll(int bno);

	int commentCount(int bno);

}
