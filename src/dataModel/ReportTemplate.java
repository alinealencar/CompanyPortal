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
* Description: This class reflects the model of the ReportTemplate table in the database.
*********************************************************************************/

package dataModel;

import java.sql.Date;

public class ReportTemplate {
	private int templateId;
	private String templateName;
	private Date templateDate;
	private String section1, section2, section3;
	
	//Criteria for each section
	private String s1Criteria1, s1Criteria2, s1Criteria3, s1Criteria4, s1Criteria5;
	private String s2Criteria1, s2Criteria2, s2Criteria3;
	private String s3Criteria1, s3Criteria2, s3Criteria3;
	
	//Maximum for each criteria
	private int s1Crit1Max, s1Crit2Max, s1Crit3Max, s1Crit4Max, s1Crit5Max;
	private int s2Crit1Max, s2Crit2Max, s2Crit3Max;
	private int s3Crit1Max, s3Crit2Max, s3Crit3Max;
	
	//Department ID to which this template is associated to
	private int deptId;

	public ReportTemplate() {

	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Date getTemplateDate() {
		return templateDate;
	}

	public void setTemplateDate(Date templateDate) {
		this.templateDate = templateDate;
	}

	public String getSection1() {
		return section1;
	}

	public void setSection1(String section1) {
		this.section1 = section1;
	}

	public String getSection2() {
		return section2;
	}

	public void setSection2(String section2) {
		this.section2 = section2;
	}

	public String getSection3() {
		return section3;
	}

	public void setSection3(String section3) {
		this.section3 = section3;
	}

	public String getS1Criteria1() {
		return s1Criteria1;
	}

	public void setS1Criteria1(String s1Criteria1) {
		this.s1Criteria1 = s1Criteria1;
	}

	public String getS1Criteria2() {
		return s1Criteria2;
	}

	public void setS1Criteria2(String s1Criteria2) {
		this.s1Criteria2 = s1Criteria2;
	}

	public String getS1Criteria3() {
		return s1Criteria3;
	}

	public void setS1Criteria3(String s1Criteria3) {
		this.s1Criteria3 = s1Criteria3;
	}

	public String getS1Criteria4() {
		return s1Criteria4;
	}

	public void setS1Criteria4(String s1Criteria4) {
		this.s1Criteria4 = s1Criteria4;
	}

	public String getS1Criteria5() {
		return s1Criteria5;
	}

	public void setS1Criteria5(String s1Criteria5) {
		this.s1Criteria5 = s1Criteria5;
	}

	public String getS2Criteria1() {
		return s2Criteria1;
	}

	public void setS2Criteria1(String s2Criteria1) {
		this.s2Criteria1 = s2Criteria1;
	}

	public String getS2Criteria2() {
		return s2Criteria2;
	}

	public void setS2Criteria2(String s2Criteria2) {
		this.s2Criteria2 = s2Criteria2;
	}

	public String getS2Criteria3() {
		return s2Criteria3;
	}

	public void setS2Criteria3(String s2Criteria3) {
		this.s2Criteria3 = s2Criteria3;
	}

	public String getS3Criteria1() {
		return s3Criteria1;
	}

	public void setS3Criteria1(String s3Criteria1) {
		this.s3Criteria1 = s3Criteria1;
	}

	public String getS3Criteria2() {
		return s3Criteria2;
	}

	public void setS3Criteria2(String s3Criteria2) {
		this.s3Criteria2 = s3Criteria2;
	}

	public String getS3Criteria3() {
		return s3Criteria3;
	}

	public void setS3Criteria3(String s3Criteria3) {
		this.s3Criteria3 = s3Criteria3;
	}

	public int getS1Crit1Max() {
		return s1Crit1Max;
	}

	public void setS1Crit1Max(int s1Crit1Max) {
		this.s1Crit1Max = s1Crit1Max;
	}

	public int getS1Crit2Max() {
		return s1Crit2Max;
	}

	public void setS1Crit2Max(int s1Crit2Max) {
		this.s1Crit2Max = s1Crit2Max;
	}

	public int getS1Crit3Max() {
		return s1Crit3Max;
	}

	public void setS1Crit3Max(int s1Crit3Max) {
		this.s1Crit3Max = s1Crit3Max;
	}

	public int getS1Crit4Max() {
		return s1Crit4Max;
	}

	public void setS1Crit4Max(int s1Crit4Max) {
		this.s1Crit4Max = s1Crit4Max;
	}

	public int getS1Crit5Max() {
		return s1Crit5Max;
	}

	public void setS1Crit5Max(int s1Crit5Max) {
		this.s1Crit5Max = s1Crit5Max;
	}

	public int getS2Crit1Max() {
		return s2Crit1Max;
	}

	public void setS2Crit1Max(int s2Crit1Max) {
		this.s2Crit1Max = s2Crit1Max;
	}

	public int getS2Crit2Max() {
		return s2Crit2Max;
	}

	public void setS2Crit2Max(int s2Crit2Max) {
		this.s2Crit2Max = s2Crit2Max;
	}

	public int getS2Crit3Max() {
		return s2Crit3Max;
	}

	public void setS2Crit3Max(int s2Crit3Max) {
		this.s2Crit3Max = s2Crit3Max;
	}

	public int getS3Crit1Max() {
		return s3Crit1Max;
	}

	public void setS3Crit1Max(int s3Crit1Max) {
		this.s3Crit1Max = s3Crit1Max;
	}

	public int getS3Crit2Max() {
		return s3Crit2Max;
	}

	public void setS3Crit2Max(int s3Crit2Max) {
		this.s3Crit2Max = s3Crit2Max;
	}

	public int getS3Crit3Max() {
		return s3Crit3Max;
	}

	public void setS3Crit3Max(int s3Crit3Max) {
		this.s3Crit3Max = s3Crit3Max;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	public int getTotal(){
		return s1Crit1Max + s1Crit2Max + s1Crit3Max + s1Crit4Max + s1Crit5Max +
				s2Crit1Max + s2Crit2Max + s2Crit3Max +
				s3Crit1Max + s3Crit2Max + s3Crit3Max;
	}

	@Override
	public String toString() {
		return "ReportTemplate [templateId=" + templateId + ", templateName=" + templateName + ", templateDate="
				+ templateDate + ", section1=" + section1 + ", section2=" + section2 + ", section3=" + section3
				+ ", s1Criteria1=" + s1Criteria1 + ", s1Criteria2=" + s1Criteria2 + ", s1Criteria3=" + s1Criteria3
				+ ", s1Criteria4=" + s1Criteria4 + ", s1Criteria5=" + s1Criteria5 + ", s2Criteria1=" + s2Criteria1
				+ ", s2Criteria2=" + s2Criteria2 + ", s2Criteria3=" + s2Criteria3 + ", s3Criteria1=" + s3Criteria1
				+ ", s3Criteria2=" + s3Criteria2 + ", s3Criteria3=" + s3Criteria3 + ", s1Crit1Max=" + s1Crit1Max
				+ ", s1Crit2Max=" + s1Crit2Max + ", s1Crit3Max=" + s1Crit3Max + ", s1Crit4Max=" + s1Crit4Max
				+ ", s1Crit5Max=" + s1Crit5Max + ", s2Crit1Max=" + s2Crit1Max + ", s2Crit2Max=" + s2Crit2Max
				+ ", s2Crit3Max=" + s2Crit3Max + ", s3Crit1Max=" + s3Crit1Max + ", s3Crit2Max=" + s3Crit2Max
				+ ", s3Crit3Max=" + s3Crit3Max + ", deptId=" + deptId + "]";
	}	
}
