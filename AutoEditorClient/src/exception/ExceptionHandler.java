/**
 * Exception Handler class extending Exceptions with custom fix methods.
 * 
 * @author Michael Kang
 */

package exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("serial")
public class ExceptionHandler extends Exception
{
	//File not found = 1, Model not found = 2, , Price not found = 3, OptionSet not found = 4, Option not found = 5.
	private int number;
	private String message;
	
	public ExceptionHandler()
	{
		super();
		print();
	}
	
	public ExceptionHandler(String message)
	{
		super();
		this.message = message;
		print();
	}
	
	public ExceptionHandler(String message, int number)
	{
		super();
		this.message = message;
		this.number = number;
		print();
	}
	
	//[GETTERS]//
	
	public int getErrorNumber()
	{
		return number;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	//[SETTERS]//

	public void setErrorNumber(int number)
	{
		this.number = number;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	//[PRINTER]//
	
	public void print()
	{
		System.out.println("Issue: [Error message = " + message + " Error number = " + number +"]");
	}
	
	//[FIXERS]//
	
	public String fixInputName(String type)
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String fix = "";
		boolean correct = false;
		
		while(correct == false)
		{
			try
			{
				System.out.println("Please enter correct " + type + " name: ");
				fix = input.readLine();
				correct = true;
			}
			catch(IOException e)
			{
				System.out.println("Please enter correct " + type + " name again: ");
			}
		}
		return fix;
	}
	
	public void fixPrice()
	{
		System.out.println("Please correct format of price before continuing.");
	}
}
