package dataModel;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Attendance implements Serializable{
	private Date attendanceDate;
	//private boolean present;
	private String deptName;
	private ArrayList<String> deptNames = new ArrayList<String>();
	
	public Attendance() {
		
	}
	
	public Attendance(Date attendanceDate, String deptName) {
		super();
		this.attendanceDate = attendanceDate;
		//this.present = present;
		this.deptName = deptName;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
	
	
	
}
