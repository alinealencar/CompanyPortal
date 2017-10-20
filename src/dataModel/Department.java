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
* Description: This class reflects the model of the Department table in the database.
*********************************************************************************/


package dataModel;

public class Department {
	private String deptName;
	private String deptLoc;
	private int id;
	
	// Constructors
	private Department (String deptName, String deptLoc, int id){
		this.deptName = deptName;
		this.deptLoc = deptLoc;
		this.id = id;
	}

	
	private Department (String deptName, int id){
		this.deptName = deptName;
		this.id = id;
	}
	

	private Department (String deptName, String deptLoc){
		this.deptName = deptName;
		this.deptLoc = deptLoc;
	}

	// Properties
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
}
