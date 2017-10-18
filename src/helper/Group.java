package helper;

public class Group {

	
	private String deptName;
    private String groupName;

    public Group(String deptName, String groupName) {
        this.deptName = deptName;
        this.groupName = groupName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String getDeptName) {
        this.deptName = getDeptName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupNamae(String groupName) {
        this.groupName = groupName;
    } 
}
