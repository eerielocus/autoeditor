/**
 * Protected OptionSet class and Option inner class containing all methods to access each ArrayList.
 * 
 * @author Michael Kang
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import exception.ExceptionHandler;

public class OptionSet implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Option> op;
	private Option choice;
	
	OptionSet()
	{	}
	
	OptionSet(String n)
	{
		name = n;
		op = new ArrayList<Option>();
	}
	
	//[GETTERS]//
	
	//Get name of OptionSet.
	protected String getName() 
	{
		return name;
	}
	//Get ArrayList of Options.
	protected ArrayList<Option> getOpset() 
	{
		return op;
	}
	//Get Option name.
	protected String getOpName(String name) throws ExceptionHandler
	{
		return findOption(name).getName();
	}
	//Get Option price.
	protected int getOpPrice(String name) throws ExceptionHandler
	{
		return findOption(name).getPrice();
	}
	//Get Option choice.
	protected Option getOpChoice()
	{
		return choice;
	}
	//Get Option choice list.
	protected ListIterator<String> getOpIterator() 
	{
		ArrayList<String> names = new ArrayList<String>();
		
		for(Option o : op) 
		{
			names.add(o.getName());
		}
		return names.listIterator();
	}
	
	//[SETTERS]//
	
	//Set name of OptionSet.
	protected void setName(String name) 
	{
		this.name = name;
	}
	//Set ArrayList of Options.
	protected void setOpset(ArrayList<Option> op) 
	{
		this.op = op;
	}
	//Set name and price of Options.
	protected void setOp(int i, String name, int price)
	{
		op.get(i).setName(name);
		op.get(i).setPrice(price);
	}
	//Add name and price of Options.
	protected void addOp(String name, int price)
	{
		Option o = new Option(name, price);
		op.add(o);
	}
	//Set choice of Options.
	protected void setOpChoice(String name) throws ExceptionHandler
	{
		try
		{
			Option o = findOption(name);
			choice = o;
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new ExceptionHandler("Option: " + name + " does not exist.", 5);
		}
	}
	
	//[UPDATERS]//
	
	//Update an Option name.
	protected void updateOpName(String oldName, String newName) throws ExceptionHandler
	{
		findOption(oldName).setName(newName);
	}
	//Update an Option price.
	protected void updateOpPrice(String name, int price) throws ExceptionHandler
	{
		findOption(name).setPrice(price);
	}
	
	//[DELETER]//
	
	//Delete an Option.
	protected void deleteOp(String name) throws ExceptionHandler
	{
		op.remove(findOption(name));
	}
	
	//[FINDER]//
	
	//Find Option through name.
	protected Option findOption(String name) throws ExceptionHandler
	{
		int index = -1;
		for(int i = 0; i < op.size(); i++)
		{
			if(op.get(i).getName().equals(name))
			{
				index = i;
			}
		}
		try
		{
			Option opt = op.get(index);
			return opt;
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new ExceptionHandler("Option: " + name + " does not exist.", 5);
		}
	}
	
	//[PRINTER]//
	
	//Print all object information.
	protected String print()
	{
		StringBuffer print = new StringBuffer();
		print.append(name);
		print.append(":");
		print.append("\n");
		for(int i = 0; i < op.size(); i++)
		{
			print.append(op.get(i).print());
			print.append("\n");
		}
		return print.toString();
	}
	//Inner class Option within OptionSet.
	protected class Option implements Serializable
	{
		private static final long serialVersionUID = 1L;
		private String name;
		private int price;
	
		Option()
		{	}
		
		Option(String n, int p)
		{
			name = n;
			price = p;
		}
		
		protected Option(Option op)
		{
			name = op.getName();
			price = op.getPrice();
		}
		
		//[GETTERS]//
		
		//Get name of Option.
		protected String getName() 
		{
			return name;
		}
		//Get price of Option.
		protected int getPrice() 
		{
			return price;
		}
		
		//[SETTERS]//
		
		//Set name of Option.
		protected void setName(String name) 
		{
			this.name = name;
		}
		//Set price of Option.
		protected void setPrice(int price) 
		{
			this.price = price;
		}
		
		//[PRINTER]//
		
		//Print all object information.
		protected String print()
		{
			StringBuffer print = new StringBuffer();
			print.append(name);
			print.append(": $");
			print.append(price);
			return print.toString();
		}
	}
}
