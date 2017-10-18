/*
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
 */
package helper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ValidateInput")
public class ValidateInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ValidateInput() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//to find if the inputed value exists or not
	public static boolean isMissing(String data){
		if(data=="" || data==null){
			return true;
		}
		else{
			return false;
		}	
	}
	
	
	//check if the inputed value is all alphabet
	public static boolean isAlphabet (String data){
		/*validate isMissing() first otherwise empty value returns true.
		 * or enable following code
		 * 
		if(!isMissing(data)){
			return false;
		}
		*/
		char[] chars = data.toCharArray();
		for (char c : chars) {
			if(!Character.isLetter(c)) {
				return false;
			}
		}
	    return true;
	}
	//Refeence: https://stackoverflow.com/questions/5238491/check-if-string-contains-only-letters
	
	
	//validate email address
	public static boolean isValidEmail(String email){
		/*validate isMissing() first otherwise empty value returns true.
		 * or enable following code
		 * 
		if(!isMissing(data)){
			return false;
		}
		*/
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		//reference: https://howtodoinjava.com/regex/java-regex-validate-email-address/
        
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
        	return false;
        }else{
        	return true;
        }
	}
	
	
	//check if the inputed value is valid employee number
	//employee number is 9 digits
	public static boolean isEmployeeNumber(String data){
		if(data.matches("[0-9]+") && data.length() == 9){
			return true;
		}
		
		return false;
	}

}
