package domain;

public class ProjectBoardVO {

	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private int readCount;
//	private int like;
	private String regdate;
	private String moddate;
	private String image_File;
	
	
	
	
	public ProjectBoardVO() {}



	public ProjectBoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}



	public ProjectBoardVO(int bno, String title, String content) {
		
		this.bno = bno;
		this.title = title;
		this.content = content;
	}



	public ProjectBoardVO(int bno, String title, String writer, int readCount) {
		
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.readCount = readCount;
	}



	public ProjectBoardVO(int bno, String title, String writer, int readCount, String regdate) {
		
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.readCount = readCount;
		this.regdate = regdate;
	}



	public ProjectBoardVO(int bno, String title, String writer, String content, int readCount, String regdate,
			String moddate, String image_File) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.readCount = readCount;
		this.regdate = regdate;
		this.moddate = moddate;
		this.image_File = image_File;
	}



	public int getBno() {
		return bno;
	}



	public void setBno(int bno) {
		this.bno = bno;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public int getReadCount() {
		return readCount;
	}



	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}



	public String getRegdate() {
		return regdate;
	}



	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}



	public String getModdate() {
		return moddate;
	}



	public void setModdate(String moddate) {
		this.moddate = moddate;
	}



	public String getImage_File() {
		return image_File;
	}



	public void setImage_File(String image_File) {
		this.image_File = image_File;
	}



	@Override
	public String toString() {
		return "ProjectBoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", readCount=" + readCount + ", regdate=" + regdate + ", moddate=" + moddate + ", image_File="
				+ image_File + "]";
	}
	
	
	
	
	
}
