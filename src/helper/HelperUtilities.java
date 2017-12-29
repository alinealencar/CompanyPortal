package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelperUtilities {
	static public String[] getStringFromResultSet(ResultSet rs, String column) throws SQLException{
		List<String> resultList = new ArrayList<String>();

		while(rs.next())
			resultList.add(rs.getString(column));
		
		String[] strList = new String[resultList.size()];
		return resultList.toArray(strList);
	}
	
	static public String[] getFullNameFromResultSet(ResultSet rs, String firstName, String lastName) throws SQLException{
		List<String> resultList = new ArrayList<String>();

		while(rs.next())
			resultList.add(rs.getString(firstName) + " " + rs.getString(lastName));
		
		String[] strList = new String[resultList.size()];
		return resultList.toArray(strList);
	}
}
