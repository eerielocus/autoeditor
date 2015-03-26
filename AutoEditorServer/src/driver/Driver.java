package driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.UpdateAuto;
import exception.ExceptionHandler;

public class Driver 
{
	public static void main(String[] args) throws ExceptionHandler
	{
		//Build Automotive object from file.
		//Filename is incorrect, Exception handler will put correct filename in.
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("autoprop.txt");
		
		//Edit some options.
		UpdateAuto a2 = new BuildAuto();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String optionSet = "";
		String option = "";
		boolean correct = false;
		
		//Print data.
		a1.printAuto("Ford ZTW");
		
		System.out.println("\nx-------------x-------------x\n");
		
		//Testing exception handling:
		//Input OptionSet name to search. (Color)
		//Input Option name to apply new price. (Ford Knox Gold CM)
		//Exception handler will post error message, then request correct OptionSet or Option name.
		try 
		{
			System.out.println("Please enter the Option Set name: ");
			optionSet = input.readLine();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			System.out.println("Please enter the Option name: ");
			option = input.readLine();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		while(correct == false)
		{
			try
			{
				a2.updateOpPrice("Ford Wagon ZTW", optionSet, option, 350);
				correct = true;
			}
			catch(ExceptionHandler e)
			{
				if(e.getErrorNumber() == 4)
				{
					optionSet = e.fixInputName("Option Set");
				}
				if(e.getErrorNumber() == 5)
				{
					option = e.fixInputName("Option");
				}
			}
		}
		System.out.println("\nx-------------x-------------x\n");
		
		//Confirm changed data.
		a1.printAuto("Ford Wagon ZTW");
	}
}
