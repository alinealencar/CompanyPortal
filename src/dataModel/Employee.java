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

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable{
	private String firstName, lastName, empNo, email, hireYear, jobPosition;
	private int empId, deptId;
	private List<Integer> present;
	
	// Constructors
	public Employee(String firstName, String lastName, String empNo, String email, String hireYear, String jobPosition) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.empNo = empNo;
		this.email = email;
		this.hireYear = hireYear;
		this.jobPosition = jobPosition;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	// Properties
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	
	public String getEmpNo() {
		return empNo;
	}
	
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHireYear() {
		return hireYear;
	}
	
	public void setHireYear(String hireYear) {
		this.hireYear = hireYear;
	}
	
	public String getJobPosition() {
		return jobPosition;
	}
	
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	
	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public int getDeptId() {
		return deptId;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public List<Integer> getPresent() {
		return present;
	}

	public void setPresent(List<Integer> present) {
		this.present = present;
	}

	
}
