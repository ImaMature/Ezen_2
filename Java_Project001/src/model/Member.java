package model;

public class Member {

	//필드
	private String id;
		//private :
	
	//생성자 : 사용하는 이유 : 다른 클래스에서 자유롭게 객체에 해당하는 정보를 저장 및 출력할 수 있게 하기 위하여.
	private String password;
	private String name;
	private String email;
	private int point;
	
	
	public Member(String id, String password, String name, String email, int point) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.point = point;
	}
	
	//메소드 [get, set?]
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
}
