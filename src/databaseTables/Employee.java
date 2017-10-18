package databaseTables;

public class Employee {

	private String fName, lName, employeeNo, email, role;
	private int hireYear;
	
	public Employee(String fName, String lName, String employeeNo, String email, String role, int hireYear) {
        this.fName = fName;
        this.lName = lName;
        this.employeeNo = employeeNo;
        this.email = email;
        this.role = role;
        this.hireYear = hireYear;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
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

    public void setRole(String jobPos) {
        this.role = jobPos;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }
	
}
