package dataModel;

import java.sql.Date;

public class Report {
	private String reportTitle;
	private Date reportDate;
	private String reportType;
	
	//Evaluation for each criteria
	private int s1Crit1, s1Crit2, s1Crit3, s1Crit4, s1Crit5;
	private int s2Crit1, s2Crit2, s2Crit3;
	private int s3Crit1, s3Crit2, s3Crit3;
	
	//Comments for each section
	private String comment1, comment2, comment3;
	
}
