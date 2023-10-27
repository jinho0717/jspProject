package domain;

public class ProjectMemberVO {
	
	private String id;
	private String pwd;
	private String email;
	private int age;
	private String regdate;
	private String lastLogin;
	
	
	public ProjectMemberVO() {
		
	}


	public ProjectMemberVO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}


	public ProjectMemberVO(String id, String pwd, String email, int age) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.age = age;
	}


	public ProjectMemberVO(String id, String pwd, String email, int age, String regdate, String lastLogin) {
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.age = age;
		this.regdate = regdate;
		this.lastLogin = lastLogin;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public String getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}


	@Override
	public String toString() {
		return "ProjectMemberVO [id=" + id + ", pwd=" + pwd + ", email=" + email + ", age=" + age + ", regdate="
				+ regdate + ", lastLogin=" + lastLogin + "]";
	}
	
	
	
	
}
