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
		return (data.equals("") || data == null);
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
	
	public static boolean isEmployeeDuplicate(String emp1, String emp2, String emp3, String emp4, String emp5, String emp6) {
		String[] employeeInput = new String[]{emp2, emp3, emp4, emp5, emp6};
		String[] testingArray = new String[]{emp1, null, null, null, null, null};
		int next = 1;
		for(int i = 0; i < 5; i++){
			if(employeeInput[i] == null)
				break;
			
			for(int j = 0; j < 6; j ++){
				if(testingArray[j] == null)
					break;
				
				if(employeeInput[i].equals(testingArray[j])){
					return true;
				}
				else{
					testingArray[next]=employeeInput[i];
					next++;
				}
			}
			
			System.out.println(i + ": " + Arrays.toString(testingArray));
			System.out.println(i + ": " + Arrays.toString(employeeInput));
		}
		
		return false;
		
	}

}
