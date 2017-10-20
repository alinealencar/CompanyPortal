/*********************************************************************************
* Project: COMP3095_TechGirls
* Assignment: Assignment 1
* Author(s): Aline Neves Alencar,
* 				Kie Ogiya,
* 				Maria Alyssa Villacete,
* 				Princess Ilasin
* Student Number: 101036808,
* 					100984638
* 					100923181
* 					100879176
* Date: October 17, 2017.
* Description: This class reflects the model of the Employee table in the database.
*********************************************************************************/

package dataModel;

public class Employee {
	private String firstname, lastname, emp_no, hire_year, job_position;
	private int emp_id, dept_id;
	
	// Constructors
	public Employee(String firstname, String lastname, String emp_no, String hire_year, String job_position, int emp_id,
			int dept_id) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.emp_no = emp_no;
		this.hire_year = hire_year;
		this.job_position = job_position;
		this.emp_id = emp_id;
		this.dept_id = dept_id;
	}
	
	public Employee(String firstname, String lastname, int dept_id) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dept_id = dept_id;
	}

	// Properties
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmp_no() {
		return emp_no;
	}
	
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	
	public String getHire_year() {
		return hire_year;
	}
	
	public void setHire_year(String hire_year) {
		this.hire_year = hire_year;
	}
	
	public String getJob_position() {
		return job_position;
	}
	
	public void setJob_position(String job_position) {
		this.job_position = job_position;
	}
	
	public int getEmp_id() {
		return emp_id;
	}
	
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	
	public int getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	
}
