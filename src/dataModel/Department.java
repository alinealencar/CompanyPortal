package dataModel;

public class Department {

	private String deptName;
	private String deptLoc;
	private int id;
	
	private Department (String deptName, String deptLoc, int id){
		this.deptName = deptName;
		this.deptLoc = deptLoc;
		this.id = id;
	}
<<<<<<< HEAD
	
	private Department (String deptName, int id){
		this.deptName = deptName;
		this.deptLoc = deptLoc;
		this.id = id;
	}
	
=======
	private Department (String deptName, String deptLoc){
		this.deptName = deptName;
		this.deptLoc = deptLoc;
	}
>>>>>>> 995a804f883ce2f2bca40a468a0dec775d6f926b

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
