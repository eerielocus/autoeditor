/**
 * Proxy class containing all methods required to create and update the Automotive object.
 * 
 * @author Michael Kang
 */

package adapter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map.Entry;

import exception.ExceptionHandler;
import model.Autofleet;
import model.Automotive;

public abstract class ProxyAutomotive 
{
	private Automotive auto;
	private static LinkedHashMap<String, Automotive> autofleet = new LinkedHashMap<String, Automotive>();
	private ArrayList<String> cars;
	boolean correct = false;
	
	//Quick get method for LinkedHashMap.
		public static LinkedHashMap<String, Automotive> getAutofleet()
		{
			return autofleet;
		}
		
	//Constructor to make new Automotive within Autofleet.
	public LinkedHashMap<String, Automotive> buildAuto(String filename) throws ExceptionHandler
	{
		new Autofleet(filename, autofleet, auto);
		return autofleet;
	}
	
	public void buildAuto(Automotive auto)
	{
		new Autofleet(autofleet, auto);
	}
	
	public void buildAutoProperty(String filename) throws ExceptionHandler
	{
		new Autofleet(filename, autofleet, auto);
	}
	
	public void printAuto(String name)
	{
		auto = autofleet.get(name);
		System.out.println(auto.toString());
	}
	
	public void updateOpSet(String name, String oldset, String newset) throws ExceptionHandler
	{
		auto = autofleet.get(name);
		auto.updateOpset(oldset, newset);
	}
	
	public void updateOpPrice(String name, String opset, String op, int price) throws ExceptionHandler
	{
		auto = autofleet.get(name);
		auto.updateOpPrice(opset, op, price);
	}
	
	//[[SERVER/CLIENT]]//
	
	public ArrayList<String> getCars() 
	{
		cars = new ArrayList<String>();
		
		Iterator<Entry<String, Automotive>> itr = autofleet.entrySet().iterator();
		
		while(itr.hasNext()) 
		{
			Entry<String, Automotive> entry = (Entry<String, Automotive>) itr.next();
			System.out.println("Entry key: " + entry.getKey());
			cars.add(entry.getKey());
		}
		return cars;
	}
	
	public void sendChoice(ObjectOutputStream oos, String name) 
	{
		auto = autofleet.get(name);
		try 
		{
			oos.writeObject(auto);
			oos.flush();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ListIterator<String> getOpsetIterator(String name)
	{
		auto = autofleet.get(name);
		ListIterator<String> list = auto.getOpsetIterator();
		return list;
	}
	
	public ListIterator<String> getOpIterator(String name, String set) throws ExceptionHandler
	{
		auto = autofleet.get(name);
		ListIterator<String> list = auto.getOpIterator(set);
		return list;
	}
	
	public int getOpPrice(String name, String set, String op) throws ExceptionHandler
	{
		auto = autofleet.get(name);
		return auto.getOpPrice(set, op);
	}
	
	public void setOpChoice(String name, String opset, String op) throws ExceptionHandler
	{
		auto = autofleet.get(name);
		auto.setOpChoice(opset, op);
	}
	
	public void printFinal(String name) throws ExceptionHandler
	{
		auto = autofleet.get(name);
		System.out.println(auto.printFinal());
	}
	
	//[EDIT OPTIONS METHODS]//
	
	public synchronized void setMake(Automotive auto, String name)
	{
		auto.setMake(name);
		System.out.println(Thread.currentThread().getName() + ":\nAuto Make: " + name + " updated successfully.\n");
	}
	
	public synchronized void setModel(Automotive auto, String name)
	{
		auto.setModel(name);
		System.out.println(Thread.currentThread().getName() + ":\nAuto Model: " + name + " updated successfully.\n");
	}
	
	public synchronized void setPrice(Automotive auto, int price)
	{
		auto.setPrice(price);
		System.out.println(Thread.currentThread().getName() + ":\nAuto Price: " + price + " updated successfully.\n");
	}
	
	public synchronized void addOpset(Automotive auto, String name)
	{
		auto.addOpset(name);
		System.out.println(Thread.currentThread().getName() + ":\nOption Set: " + name + " added successfully.\n");
	}
	
	public synchronized void addOpn(Automotive auto, String set, String name, int price) throws ExceptionHandler
	{
		while(correct == false)
		{
			try
			{
				auto.addOpn(set, name, price);
				System.out.println(Thread.currentThread().getName() + ":\nOption: " + name + " added successfully to " + set + ".\n");
				correct = true;
			}
			catch(ExceptionHandler e)
			{
				if(e.getErrorNumber() == 4)
				{
					set = e.fixInputName("Option Set");
				}
			}
		}
	}
	
	public synchronized void updateOpset(Automotive auto, String oldName, String newName) throws ExceptionHandler
	{
		while(correct == false)
		{
			try
			{
				auto.updateOpset(oldName, newName);
				System.out.println(Thread.currentThread().getName() + ":\nOption Set: " + oldName + " updated successfully to: " + newName + ".\n");
				correct = true;
			}
			catch(ExceptionHandler e) 
			{
				if(e.getErrorNumber() == 4)
				{
					oldName = e.fixInputName("Option Set");
				}
			}
		}
	}
	
	public synchronized void updateOpName(Automotive auto, String oldName, String newName, String set) throws ExceptionHandler
	{
		while(correct == false)
		{
			try
			{
				auto.updateOpName(oldName, newName, set);
				System.out.println(Thread.currentThread().getName() + ":\nOption: " + oldName + " updated successfully to: " + newName + ".\n");
				correct = true;
			}
			catch(ExceptionHandler e)
			{
				if(e.getErrorNumber() == 4)
				{
					set = e.fixInputName("Option Set");
				}
				if(e.getErrorNumber() == 5)
				{
					oldName = e.fixInputName("Option");
				}
			}
		}
	}
	
	public synchronized void updateOpPrice(Automotive auto, String set, String name, int price) throws ExceptionHandler
	{
		while(correct == false)
		{
			try
			{
				auto.updateOpPrice(set, name, price);
				System.out.println(Thread.currentThread().getName() + ":\nOption: " + name + " price updated successfully to: $" + price + ".\n");
				correct = true;
			}
			catch(ExceptionHandler e)
			{
				if(e.getErrorNumber() == 4)
				{
					set = e.fixInputName("Option Set");
				}
				if(e.getErrorNumber() == 5)
				{
					name = e.fixInputName("Option");
				}
			}
		}
	}
	
	public synchronized void deleteOpset(Automotive auto, String name) throws ExceptionHandler
	{
		while(correct == false)
		{
			try
			{
				auto.deleteOpset(name);
				System.out.println(Thread.currentThread().getName() + ":\nOption Set: " + name + " successfully deleted.\n");
				correct = true;
			}
			catch(ExceptionHandler e) 
			{
				if(e.getErrorNumber() == 4)
				{
					name = e.fixInputName("Option Set");
				}
			}
		}
	}
	
	public synchronized void deleteOp(Automotive auto, String op, String opset) throws ExceptionHandler
	{
		while(correct == false)
		{
			try
			{
				auto.deleteOp(op, opset);
				System.out.println(Thread.currentThread().getName() + ":\nOption: " + op + " successfully deleted.\n");
				correct = true;
			}
			catch(ExceptionHandler e)
			{
				if(e.getErrorNumber() == 4)
				{
					opset = e.fixInputName("Option Set");
				}
				if(e.getErrorNumber() == 5)
				{
					op = e.fixInputName("Option");
				}
			}
		}
	}
}
