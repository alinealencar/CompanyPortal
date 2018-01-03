package dataModel;

import java.sql.Date;

public class Report {
	private int reportId;
	private String reportTitle;
	private Date reportDate;
	private String reportType;
	
	//Evaluation for each criteria
	private int s1Crit1, s1Crit2, s1Crit3, s1Crit4, s1Crit5;
	private int s2Crit1, s2Crit2, s2Crit3;
	private int s3Crit1, s3Crit2, s3Crit3;
	
	//Comments for each section
	private String comment1, comment2, comment3;
	
	private int templateId;

	public Report() {
	}

	
	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public int getS1Crit1() {
		return s1Crit1;
	}

	public void setS1Crit1(int s1Crit1) {
		this.s1Crit1 = s1Crit1;
	}

	public int getS1Crit2() {
		return s1Crit2;
	}

	public void setS1Crit2(int s1Crit2) {
		this.s1Crit2 = s1Crit2;
	}

	public int getS1Crit3() {
		return s1Crit3;
	}

	public void setS1Crit3(int s1Crit3) {
		this.s1Crit3 = s1Crit3;
	}

	public int getS1Crit4() {
		return s1Crit4;
	}

	public void setS1Crit4(int s1Crit4) {
		this.s1Crit4 = s1Crit4;
	}

	public int getS1Crit5() {
		return s1Crit5;
	}

	public void setS1Crit5(int s1Crit5) {
		this.s1Crit5 = s1Crit5;
	}

	public int getS2Crit1() {
		return s2Crit1;
	}

	public void setS2Crit1(int s2Crit1) {
		this.s2Crit1 = s2Crit1;
	}

	public int getS2Crit2() {
		return s2Crit2;
	}

	public void setS2Crit2(int s2Crit2) {
		this.s2Crit2 = s2Crit2;
	}

	public int getS2Crit3() {
		return s2Crit3;
	}

	public void setS2Crit3(int s2Crit3) {
		this.s2Crit3 = s2Crit3;
	}

	public int getS3Crit1() {
		return s3Crit1;
	}

	public void setS3Crit1(int s3Crit1) {
		this.s3Crit1 = s3Crit1;
	}

	public int getS3Crit2() {
		return s3Crit2;
	}

	public void setS3Crit2(int s3Crit2) {
		this.s3Crit2 = s3Crit2;
	}

	public int getS3Crit3() {
		return s3Crit3;
	}

	public void setS3Crit3(int s3Crit3) {
		this.s3Crit3 = s3Crit3;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getComment2() {
		return comment2;
	}

	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}

	public String getComment3() {
		return comment3;
	}

	public void setComment3(String comment3) {
		this.comment3 = comment3;
	}

	public int getTemplateId() {
		return templateId;
	}


	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	
	public int getTotal(){
		return s1Crit1 + s1Crit2 + s1Crit3 + s1Crit4 + s1Crit5 +
				s2Crit1 + s2Crit2 + s2Crit3 +
				s3Crit1 + s3Crit2 + s3Crit3;
	}


	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", reportTitle=" + reportTitle + ", reportDate=" + reportDate
				+ ", reportType=" + reportType + ", s1Crit1=" + s1Crit1 + ", s1Crit2=" + s1Crit2 + ", s1Crit3="
				+ s1Crit3 + ", s1Crit4=" + s1Crit4 + ", s1Crit5=" + s1Crit5 + ", s2Crit1=" + s2Crit1 + ", s2Crit2="
				+ s2Crit2 + ", s2Crit3=" + s2Crit3 + ", s3Crit1=" + s3Crit1 + ", s3Crit2=" + s3Crit2 + ", s3Crit3="
				+ s3Crit3 + ", comment1=" + comment1 + ", comment2=" + comment2 + ", comment3=" + comment3
				+ ", templateId=" + templateId + ", getReportId()=" + getReportId() + ", getReportTitle()="
				+ getReportTitle() + ", getReportDate()=" + getReportDate() + ", getReportType()=" + getReportType()
				+ ", getS1Crit1()=" + getS1Crit1() + ", getS1Crit2()=" + getS1Crit2() + ", getS1Crit3()=" + getS1Crit3()
				+ ", getS1Crit4()=" + getS1Crit4() + ", getS1Crit5()=" + getS1Crit5() + ", getS2Crit1()=" + getS2Crit1()
				+ ", getS2Crit2()=" + getS2Crit2() + ", getS2Crit3()=" + getS2Crit3() + ", getS3Crit1()=" + getS3Crit1()
				+ ", getS3Crit2()=" + getS3Crit2() + ", getS3Crit3()=" + getS3Crit3() + ", getComment1()="
				+ getComment1() + ", getComment2()=" + getComment2() + ", getComment3()=" + getComment3()
				+ ", getTemplateId()=" + getTemplateId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}	
}
