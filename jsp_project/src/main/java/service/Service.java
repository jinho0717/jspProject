package service;

import java.util.List;

import domain.ProjectBoardVO;
import domain.ProjectPagingVO;

public interface Service {

	int register(ProjectBoardVO bvo);

	List<ProjectBoardVO> list();

	ProjectBoardVO detail(int bno);

	int readCount(int bno);

	int modify(ProjectBoardVO bvo);

	int remove(int bno);

	int getTotalCount(ProjectPagingVO pgvo);

	List<ProjectBoardVO> getPageList(ProjectPagingVO pgvo);

	String getFileName(int bno);

	List<ProjectBoardVO> mylist(String writer);

}
