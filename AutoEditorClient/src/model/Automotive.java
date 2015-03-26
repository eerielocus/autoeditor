/**
 * Automotive class containing all methods to access and create: OptionSets and Options.
 * 
 * @author Michael Kang
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;

import exception.ExceptionHandler;
import model.OptionSet.Option;

public class Automotive implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String make;
	private String model;
	private int price;
	private ArrayList<OptionSet> opset;
	
	public Automotive()
	{
		opset = new ArrayList<OptionSet>();
	}
	
	public Automotive(String make, String model)
	{
		this.make = make;
		this.model = model;
		opset = new ArrayList<OptionSet>();
	}
	
	public Automotive(String make, String model, int price)
	{
		this.make = make;
		this.model = model;
		this.price = price;
		opset = new ArrayList<OptionSet>();
	}
	
	//[GETTERS]//
	
	//Get make of automotive.
	public String getMake() 
	{
		return make;
	}
	//Get model of automotive.
	public String getModel() 
	{
		return model;
	}
	//Get base price of automotive.
	public int getPrice() 
	{
		return price;
	}
	//Get an OptionSet.
	public ArrayList<OptionSet> getOpset() 
	{
		return opset;
	}
	//Get an Option within OptionSet.
	public Option getOp(String set, String op) throws ExceptionHandler
	{
		return getOptionSet(set).findOption(op);
	}
	//Get an Option's price.
	public int getOpPrice(String set, String name) throws ExceptionHandler
	{
		return getOptionSet(set).getOpPrice(name);
	}
	//Get name of automotive.
	public String getName()
	{
		String name = make + " " + model;
		return name;
	}
	//Get an Option choice.
	public String getOpChoice(String name) throws ExceptionHandler
	{
		return getOptionSet(name).getOpChoice().getName();
	}
	//Get an Option choice's price.
	public int getOpChoicePrice(String name) throws ExceptionHandler
	{
		return getOptionSet(name).getOpChoice().getPrice();
	}
	//Iterate through list of Option Set names.
	public ListIterator<String> getOpsetIterator()
	{
		ArrayList<String> opsetName = new ArrayList<String>();
		
		for(OptionSet os : opset) 
		{
			opsetName.add(os.getName());
		}
		return opsetName.listIterator();
	}
	//Provide check for iterator.
	public Boolean hasOptionSet(String name) throws ExceptionHandler
	{
		int i = findOpset(name);
		if(i != -1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Get list of Option Set's options.
	public ListIterator<String> getOpIterator(String set) throws ExceptionHandler
	{
		if(this.hasOptionSet(set))
		{
			return this.getOptionSet(set).getOpIterator();
		}
		else
		{
			throw new ExceptionHandler("Option Set: " + set + " does not exist.", 4);
		}
	}	
	//Get the total price of automotive.
	public int getTotalPrice()
	{
		int total = price;
		for(int i = 0; i < opset.size(); i++)
		{
			total += opset.get(i).getOpChoice().getPrice();
		}
		return total;
	}
	//Get a list of options in Option Set.
	public Map<String, Integer> listOptions(Map<String, Integer> set, String name) throws ExceptionHandler
	{
		OptionSet opset = getOptionSet(name);
		ArrayList<Option> option = opset.getOpset();
		
		for(Option opt : option) 
		{
			set.put(opt.getName(), opt.getPrice());
		}
		
		return set;
	}
	
	//[SETTERS]//
	
	//Set make of automotive.
	public void setMake(String make) 
	{
		this.make = make;
	}
	//Set model of automotive.
	public void setModel(String model) 
	{
		this.model = model;
	}
	//Set base price of automotive.
	public void setPrice(int price) 
	{
		this.price = price;
	}
	//Set an OptionSet.
	public void setOpset(ArrayList<OptionSet> opset) 
	{
		this.opset = opset;
	}
	//Set an Option within OptionSet via name search.
	public void setOp(String set, int i, String name, int price) throws ExceptionHandler
	{
		getOptionSet(set).setOp(i, name, price);
	}
	//Set an Option choice via name search.
	public void setOpChoice(String set, String op) throws ExceptionHandler
	{
		getOptionSet(set).setOpChoice(op);
	}
	
	//[ADDERS]//
	
	//Add an OptionSet.
	public void addOpset(String name)
	{
		opset.add(new OptionSet(name));
	}
	//Add an Option within OptionSet using index.
	public void addOp(int index, String name, int price)
	{
		opset.get(index).addOp(name, price);
	}
	//Add on Option within OptionSet using name.
	public void addOpn(String set, String name, int price) throws ExceptionHandler
	{
		try
		{
			getOptionSet(set).addOp(name, price);
		}
		catch(IndexOutOfBoundsException e) 
		{
			throw new ExceptionHandler("Option Set: " + name + " does not exist.", 4);
		}
	}
	
	//[UPDATERS]//
	
	//Update an OptionSet via name search.
	public void updateOpset(String oldName, String newName) throws ExceptionHandler
	{
		getOptionSet(oldName).setName(newName);
	}
	//Update an OptionSet via index.
	public void updateOpset(int index, String name)
	{
		opset.get(index).setName(name);
	}
	//Update an Option name via name search.
	public void updateOpName(String oldName, String newName, String set) throws ExceptionHandler
	{
		getOptionSet(set).updateOpName(oldName, newName);
	}
	//Update an Option price via name search.
	public void updateOpPrice(String set, String name, int price) throws ExceptionHandler
	{
		getOptionSet(set).updateOpPrice(name, price);
	}
	
	//[DELETERS]//
	
	//Delete an OptionSet via name search.
	public void deleteOpset(String name) throws ExceptionHandler
	{
		try
		{
			opset.remove(findOpset(name));
		}
		catch(IndexOutOfBoundsException e) 
		{
			throw new ExceptionHandler("Option Set: " + name + " does not exist.", 4);
		}
	}
	//Delete an Option via name search.
	public void deleteOp(String op, String opset) throws ExceptionHandler
	{
		try
		{
			getOptionSet(opset).deleteOp(op);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new ExceptionHandler("Option: " + op + " does not exist.", 5);
		}
	}
	
	//[FINDER]//
	
	//Find an OptionSet.
	public OptionSet getOptionSet(String name) throws ExceptionHandler
	{
		try
		{
			return opset.get(findOpset(name));
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new ExceptionHandler("Option Set: " + name + " does not exist.", 4);
		}
	}
	//Method to be used to find an OptionSet.
	public int findOpset(String name) throws ExceptionHandler
	{
		try
		{
			for(int i = 0; i < opset.size(); i++)
			{
				if(opset.get(i).getName().equals(name))
				{
					return i;
				}
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new ExceptionHandler("Option Set: " + name + " does not exist.", 4);
		}
		return -1;
	}
	
	//[PRINTER]//
	
	//Print all object information.
	public String toString()
	{
		StringBuffer print = new StringBuffer();
		print.append("Make: ");
		print.append(getMake());
		print.append("\n");
		print.append("Model: ");
		print.append(getModel());
		print.append("\n");
		print.append("Price: $");
		print.append(getPrice());
		print.append("\n");
		print.append("\n[Options]\n\n");
		
		for(int i = 0; i < getOpset().size(); i++) 
		{
			print.append(getOpset().get(i).print());
			print.append("\n");
		}
		return print.toString();
	}
	//Print final loadout and price based on client choices.
	public String printFinal() throws ExceptionHandler
	{
		StringBuffer print = new StringBuffer();
		print.append("Make: ");
		print.append(getMake());
		print.append("\n");
		print.append("Model: ");
		print.append(getModel());
		print.append("\n");
		print.append("Price: $");
		print.append(getPrice());
		print.append("\n");
		print.append("\n[Options]\n\n");
		
		for(int i = 0; i < getOpset().size(); i++) 
		{
			print.append(getOpset().get(i).getName());
			print.append("\n");
			print.append(getOpChoice(getOpset().get(i).getName()));
			print.append(": $");
			print.append(getOpChoicePrice(getOpset().get(i).getName()));
			print.append("\n\n");
		}
		print.append("Final total: $");
		print.append(getTotalPrice());
		print.append("\n");
		return print.toString();
	}
}
