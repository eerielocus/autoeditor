/**
 * Autofleet class contains constructor and methods to create an Automotive object within a LinkedHashMap
 * with the ability to add more objects into the collection.
 * 
 * @author Michael Kang
 */

package model;

import java.io.Serializable;
import java.util.LinkedHashMap;

import exception.ExceptionHandler;
import util.Reader;

public class Autofleet implements Serializable
{
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, Automotive> autofleet;
	private Automotive auto;
	
	public Autofleet()
	{
		autofleet = new LinkedHashMap<String, Automotive>();
		auto = new Automotive();
	}
	
	public Autofleet(LinkedHashMap<String, Automotive> autofleet, Automotive auto)
	{
		this.autofleet = autofleet;
		this.auto = auto;
		
		addAuto(auto.getName(), auto);
	}
	
	public Autofleet(String filename, LinkedHashMap<String, Automotive> autofleet, Automotive auto)
	{
		this.autofleet = autofleet;
		this.auto = auto;
		
		boolean correct = false;
		while(correct == false)
		{
			try
			{
				Reader build = new Reader();
				auto = build.UseProperties(filename);
				addAuto(auto.getName(), auto);
				correct = true;
			}
			catch(ExceptionHandler e)
			{
				if(e.getErrorNumber() == 1)
				{
					filename = e.fixInputName("file");
				}
				if(e.getErrorNumber() == 3)
				{
					e.fixPrice();
					correct = true;
				}
			}
		}
	}
	
	//[GETTERS]//
	
	public LinkedHashMap<String, Automotive> getAutofleet()
	{
		return autofleet;
	}
	
	public Automotive getAuto(String name)
	{
		auto = autofleet.get(name);
		return auto;
	}
	
	//[ADDERS]//
	
	public void addAuto(String name, Automotive auto)
	{
		autofleet.put(name, auto);
	}
	
	//[DELETERS]//
	
	public void deleteAuto(String name)
	{
		autofleet.remove(name);
	}
}