package dataModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Attendance implements Serializable{
	private String attendanceDate;
	private boolean present;
	private String deptName;
	private ArrayList<String> deptNames = new ArrayList<String>();
	
	public Attendance() {
		
	}
	
	public Attendance(String attendanceDate, boolean present, String deptName) {
		super();
		this.attendanceDate = attendanceDate;
		this.present = present;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
	
	
	
}