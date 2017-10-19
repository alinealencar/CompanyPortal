package helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeHelper {
	public static String getDepartment(String position, Connection conn) 
			throws Exception {
			String result = "";
			Statement statement = conn.createStatement();
			String query = "select dept_name from department "
					+ "join employee on department.id = employee.dept_id_fk "
					+ "where job_position='" + position + "'";
			ResultSet rs = statement.executeQuery(query);
			
			if(rs != null){
				while(rs.next()){
					result += rs.getString("dept_name");
					}
				}
				else
					result += "No data retrieved. Table is empty.";
				
				return result;
		}
}				



