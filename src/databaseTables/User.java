package databaseTables;

public class User {

	private String firstName, lastName, email, role, username, password;
	private int id;
	
	public User(){
		super();
	}
	public User(String firstname, String lastname, String email, String role, String username, String password) {
		super();
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.role = role;
		this.username = username;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastName;
	}
	public void setLastname(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
