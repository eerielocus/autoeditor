/**
 * Reader class to open, read and input file information into appropriate lists.
 * 
 * @author Michael Kang
 */

package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import exception.ExceptionHandler;
import model.Automotive;

public class Reader 
{
	//String array for easier customization of properties file reader.
	private static String[] setValue = {"1", "2", "3", "4", "5"};
	private static String[] opValue = {"a", "b", "c", "e", "f", "g", "h", "i", "j"};
	//Read car attributes through a text file in specified format.
	public Automotive readAutoFile(String filename) throws ExceptionHandler
	{
		Automotive auto = new Automotive();
		int opIndex = 0;
		
		try 
		{
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			
			boolean read = false;
			
			while(!read) 
			{
				String line = buff.readLine();
				//Break loop if no line.
				if(line == null)
					break;
				
				if(line.length() > 1)
				{
					//Line has information on car make, model, and price.
					if(line.indexOf(":") == -1 && line.indexOf(",") != -1)
					{
						opIndex = -1;
						//Line read range set by ",", remove any white space and put into strings.
						String make = line.substring(0, line.indexOf(","));
						String model = line.substring(line.indexOf(",") + 1, line.indexOf(",", line.indexOf(",") + 1) ).trim();
						String pricing = line.substring(line.indexOf(",", line.indexOf(",") + 1) + 1).trim();
						int price = Integer.parseInt(pricing);
							
						//Make, Model, and Price are placed into automotive object.
						auto.setMake(make);
						auto.setModel(model);
						auto.setPrice(price);
					}
					//Line has option set name, create a new OptionSet and add to ArrayList<OptionSet>.
					else
					{
						if(line.indexOf(":") != -1)
						{
							if(line.substring(line.indexOf(":")).trim().equals(":"))
							{
								//Add to index to keep track of location for Options.
								opIndex++;
								String set = line.substring(0, line.indexOf(":"));
								//OptionSet name placed into automotive object.
								auto.addOpset(set);
							}
							//Line has option name and price, create a new Option and add to ArrayList<Option>.
							else
							{
								String name = line.substring(0, line.indexOf(":"));
								int price = Integer.parseInt((line.substring(line.indexOf(":") + 1)).trim());
								//Using index to add appropriate Option information into automotive object.
								auto.addOp(opIndex, name, price);
							}
						}
					}
				}
			}
			buff.close();
		} 
		 
		catch(IOException e) 
		{
			throw new ExceptionHandler("File: " + filename + " does not exist.", 1);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new ExceptionHandler("Basic automotive information missing.", 2);
		}
		catch(NumberFormatException e)
		{
			throw new ExceptionHandler("Missing/Incorrect format for price of automotive.", 3);
		}
		return auto;
	}
	//Read car attributes from a properties file.
	public Automotive UseProperties(String filename) throws ExceptionHandler
	{
		//Instantiate automotive and properties.
		Automotive auto = new Automotive();
		Properties prop = new Properties();
		
		try
		{
			//Load up properties file.
			FileInputStream input = new FileInputStream(filename);
			prop.load(input);
			
			String setName = "";
			String opName = "";
			String CarMake = prop.getProperty("CarMake");
			//Begin setting attributes with properties file.
			if(CarMake != null)
			{
				String CarModel = prop.getProperty("CarModel");
				int CarPrice = Integer.parseInt(prop.getProperty("CarPrice"));
				int opIndex = -1;
				
				auto.setMake(CarMake);
				auto.setModel(CarModel);
				auto.setPrice(CarPrice);
				
				for(int i = 0; i < setValue.length; i++)
				{
					//Set option set name and increase index.
					opIndex++;
					setName = prop.getProperty("Option" + setValue[i]);
					auto.addOpset(setName);
					
					for(int k = 0; k < opValue.length; k++)
					{
						//Set option name based on index.
						opName = prop.getProperty("OptionName" + setValue[i] + opValue[k]);
						//If there is no option, method will break instead of attempting to gather price.
						if(opName == null)
						{
							break;
						}
						//Set option price based on index.
						int opPrice = Integer.parseInt(prop.getProperty("OptionPrice" + setValue[i] + opValue[k]));
						auto.addOp(opIndex, opName, opPrice);
					}
				}
			}
		}
		catch(IOException e) 
		{
			throw new ExceptionHandler("File: " + filename + " does not exist.", 1);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new ExceptionHandler("Basic automotive information missing.", 2);
		}
		catch(NumberFormatException e)
		{
			throw new ExceptionHandler("Missing/Incorrect format for price of automotive.", 3);
		}
		return auto;
	}
}
