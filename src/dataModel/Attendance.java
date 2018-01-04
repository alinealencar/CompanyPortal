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
* Description: This class reflects the model of the Attendance table in the database.
*********************************************************************************/

package dataModel;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Attendance implements Serializable{
	private Date attendanceDate;
	private String deptName;
	
	public Attendance() {
		
	}
	
	public Attendance(Date attendanceDate, String deptName) {
		super();
		this.attendanceDate = attendanceDate;
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
