/***********************************************************************
* Project: COMP3095_TechGirls
* Assignment: Assignment 2
* Author(s): Aline Neves Alencar,
* 				Kie Ogiya,
* 				Maria Alyssa Villacete,
* 				Princess Ilasin
* Student Number: 101036808,
* 					100984638
* 					100923181
* 					100879176
* Date: October 17, 2017.
* Description: This class contains methods to help interpret ResultSets.
*********************************************************************************/

package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelperUtilities {
	/**
	 * This method returns an array of Strings. The array contains all data in a provided column
	 * and a provided ResultSet.
	 * 
	 * @param rs
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public static String[] getStringFromResultSet(ResultSet rs, String column) throws SQLException{
		List<String> resultList = new ArrayList<String>();

		while(rs.next())
			resultList.add(rs.getString(column));
		
		String[] strList = new String[resultList.size()];
		return resultList.toArray(strList);
	}
	
	/**
	 * This method returns an array of Strings with the full names from a provided ResultSet.
	 * 
	 * @param rs
	 * @param firstName
	 * @param lastName
	 * @return
	 * @throws SQLException
	 */
	public static String[] getFullNameFromResultSet(ResultSet rs, String firstName, String lastName) throws SQLException{
		List<String> resultList = new ArrayList<String>();

		while(rs.next())
			resultList.add(rs.getString(firstName) + " " + rs.getString(lastName));
		
		String[] strList = new String[resultList.size()];
		return resultList.toArray(strList);
	}
}
