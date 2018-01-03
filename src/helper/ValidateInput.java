/*********************************************************************
* Project: COMP3095_TechGirls
* Assignment: Assignment 1
* Author(s): Aline Neves Alencar,
* 			 Kie Ogiya,
* 			 Maria Alyssa Villacete,
* 			 Princess Ilasin
* Student Number: 101036808,
* 				  100984638
* 				  100923181
* 				  100879176
* Date: October 17, 2017.
* Description: This class provides the the validation of user's input.
***********************************************************************/
package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {
	/**
	 * This method validates whether a certain value is either empty or null
	 * @param data	String that holds the data that will be validated
	 * @return boolean 	It returns true if the data satisfies the conditions, false otherwise.
	 */
	public static boolean isMissing(String data){
		return (data == null || data.trim().equals(""));
	}
	
	/**
	 * This method checks if the parameter contains alphabetic characters only.
	 * @param data	String that holds the data that will be validated.
	 * @return	boolean It returns true if the parameter is valid, false otherwise.
	 */
	public static boolean isAlphabet (String data){
		char[] chars = data.toCharArray();
		for (char c : chars) {
			if(!Character.isLetter(c)) {
				return false;
			}
		}
	    return true;
	}
	
	/**
	 * This method checks if the parameter is a valid email address.
	 * @param email	String that holds the data that will be validated.
	 * @return	boolean	It returns true if the parameter is a valid email, false otherwise.
	 */
	public static boolean isValidEmail(String email){
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
       
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        
        return (matcher.matches());
	}
	
	/**
	 * This method checks if the parameter is a valid employee number.
	 * Valid employee numbers are 9-digits long.
	 * @param data	String that holds the data that will be validated.
	 * @return	boolean It returns true if the parameter is a valid employee number,
	 * 					false otherwise.
	 */
	public static boolean isEmployeeNumber(String data){
		return (data.matches("[0-9]+") && data.length() == 9);
	}
	
	/**
	 * This method checks if the user added a same employee twice when creating a group.
	 * This checking is done by checking if an employee was added to a temporary arraylist. If the
	 * employee that will be added to it is already there, it means that employee was added twice.
	 * @param emp1	String that holds the full name of the first employee
	 * @param emp2	String that holds the full name of the second employee
	 * @param emp3	String that holds the full name of the third employee
	 * @param emp4	String that holds the full name of the fourth employee
	 * @param emp5	String that holds the full name of the fifth employee
	 * @param emp6	String that holds the full name of the sixth employee
	 * @return boolean It returns true if there's a duplicate selection for one employee, false if
	 * 					each employee was selected once only.
	 */
	public static boolean isEmployeeDuplicate(String emp1, String emp2, String emp3, String emp4, String emp5, String emp6) {
		ArrayList<String> tempList = new ArrayList<String>();
		ArrayList<String> employeeInput = new ArrayList<String>();
		
		tempList.add(emp1);
		employeeInput.add(emp2);
		employeeInput.add(emp3);
		employeeInput.add(emp4);
		employeeInput.add(emp5);
		employeeInput.add(emp6);
		
		for(int i = 0; i < employeeInput.size(); i++){
			if(tempList.contains(employeeInput.get(i)) && !employeeInput.get(i).equals("")) {
				return true;
			}
			else
				tempList.add(employeeInput.get(i));
		}
		return false;
	}

	
	
}
