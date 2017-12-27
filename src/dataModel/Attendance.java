package dataModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Attendance implements Serializable{
	private String attendanceDate;
	private boolean present;
	private int id;
	private String deptName;
	private ArrayList<String> deptNames = new ArrayList<String>();
	
	public Attendance() {
		
	}
	
	public Attendance(String attendanceDate, boolean present, int id, String deptName) {
		super();
		this.attendanceDate = attendanceDate;
		this.present = present;
		this.id = id;
		this.deptName = deptName;
	}

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public ArrayList<String> getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(ArrayList<String> deptNames) {
		this.deptNames = deptNames;
	}
	
	
	
	
}
