/**
 * Edit Options class contains methods to select from a menu different Option Sets and Options for a specified
 * Automotive object. Extends ProxyAutomotive to call methods.
 * 
 * @author Michael Kang
 */

package scale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import adapter.ProxyAutomotive;
import exception.ExceptionHandler;
import model.Automotive;

public class EditOption extends ProxyAutomotive implements Runnable
{
	private static Automotive auto;
	boolean correct = false;
	boolean running = true;
	
	public EditOption(Automotive auto)
	{
		EditOption.auto = auto;
	}
	
	public EditOption(LinkedHashMap<String,Automotive> auto, String name)
	{
		super();
		EditOption.auto = auto.get(name);
	}

	public synchronized void run()
	{
		while(running)
		{
			int selection = 0;
			while(selection != 5)
			{
				String carName = auto.getName();
				selection = Integer.parseInt(readConsole("Choose which to edit: " + carName
						+ "\n1.) Automotive"
						+ "\n2.) Option Set"
						+ "\n3.) Option"
						+ "\n4.) Print"
						+ "\n5.) Exit"));
				
				switch(selection) 
				{
				case 1:
					//Edit Make, Model, Price.
					int automotive = Integer.parseInt(readConsole("Edit Auto:"
							+ "\n1.) Edit Make"
							+ "\n2.) Edit Model"
							+ "\n3.) Edit Price"));
					
					switch(automotive)
					{
					case 1:
						//Edit Make.
						String make = readConsole("Enter new Auto Make name:");
						setMake(auto, make);
						break;
					case 2:
						//Edit Model.
						String model = readConsole("Enter new Auto Model name:");
						setModel(auto, model);
						break;
					case 3:
						//Edit Price.
						int price = Integer.parseInt(readConsole("Enter new Auto Price:"));
						setPrice(auto, price);
						break;
					}
					break;
				case 2:	
					//Edit Option Set.
					int optionSet = Integer.parseInt(readConsole("Edit Option Set:"
							+ "\n1.) Add new Option Set"
							+ "\n2.) Update existing Option Set"
							+ "\n3.) Delete existing Option Set"));
					
					switch(optionSet) 
					{
					case 1:
						//Add Option Set.
						String name = readConsole("Enter new Option Set name:");
						addOpset(auto, name);
						break;
					case 2:
						//Update Option Set.
						String oldName = readConsole("Enter existing Option Set name:");
						String newName = readConsole("Enter new Option Set name:");
						try 
						{
							updateOpset(auto, oldName, newName);
						} 
						catch(ExceptionHandler e) 
						{
							e.printStackTrace();
						}
						break;
					case 3:
						//Delete Option Set
						String optionSetName = readConsole("Enter existing Option Set name:");
						try 
						{
							deleteOpset(auto, optionSetName);
						} 
						catch(ExceptionHandler e) 
						{
							e.printStackTrace();
						}
						break;
					}
					break;
				case 3:
					//Edit Options.
					int option = Integer.parseInt(readConsole("Edit Option:"
							+ "\n1.) Add new Option"
							+ "\n2.) Update existing Option"
							+ "\n3.) Delete existing Option"));
					
					switch(option)
					{
					case 1:
						//Add new Option.
						String set = readConsole("Enter Option Set name:");
						String name = readConsole("Enter new Option name:");
						int price = Integer.parseInt(readConsole("Enter new Option's price:"));
					
						try 
						{
							addOpn(auto, set, name, price);
						} 
						catch(ExceptionHandler e) 
						{
							e.printStackTrace();
						}
						break;
					case 2:
						//Update Option.
						String setName = readConsole("Enter Option Set name:");
						String oldName = readConsole("Enter existing Option name:");
						String newName = readConsole("Enter new Option name:");
					
						try 
						{
							updateOpName(auto, oldName, newName, setName);
						} 
						catch(ExceptionHandler e) 
						{
							e.printStackTrace();
						}
						break;
					case 3:
						//Delete Option.
						String opset = readConsole("Enter Option Set name:");
						String op = readConsole("Enter Option name to delete:");
					
						try 
						{
							deleteOp(auto, op, opset);
						} 
						catch(ExceptionHandler e) 
						{
							e.printStackTrace();
						}
						break;
					}
					break;
				case 4:
					//Print auto information.
					printAuto();
					break;
				case 5:
					//Exit program.
					running = false;
					break;
				}
				break;
			}
		}
	}
	
	//[PRINTER AND READER FOR INPUT]//
	
	public void printAuto()
	{
		System.out.println("\n" + auto.toString());
	}
	
	public synchronized String readConsole(String choice) 
	{
		String message = "\n" + Thread.currentThread().getName() + "\n";
		System.out.println(message + choice);
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String read = "";
		
		try 
		{
			read = input.readLine();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		return read;
	}
}
