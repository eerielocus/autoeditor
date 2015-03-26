package servlet;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import exception.ExceptionHandler;
import model.Automotive;

public class AutoBean 
{
	private String model;
	private double price;
	private Map<String, Integer> color;
	private Map<String, Integer> transmission;
	private Map<String, Integer> brake;
	private Map<String, Integer> bags;
	private Map<String, Integer> moonroof;

	//[GETTERS]//
	
	public String getModel()
	{
	    return this.model;
	}

	public double getPrice()
	{
	    return this.price;
	}
	
	public Map<String, Integer> getColor()
	{
		return this.color;
	}

	public Map<String, Integer> getTransmission()
	{
		return this.transmission;
	}
	
	public Map<String, Integer> getBrake()
	{
	    return this.brake;
	}

	public Map<String, Integer> getBags()
	{
	    return this.bags;
	}

	public Map<String, Integer> getMoonroof()
	{
	    return this.moonroof;
	}

	//[SETTERS]//
	
	public void setModel(String model)
	{
	    this.model = model;
	}

	public void setBasePrice(double basePrice)
	{
	    this.price = basePrice;
	}
	
	public void setColor(Map<String, Integer> color) 
	{
		this.color = color;
	}
	
	public void setTransmission(Map<String, Integer> transmission)
	{
		this.transmission = transmission;
	}

	public void setBrake(Map<String, Integer> brakes)
	{
	    this.brake = brakes;
	}

	public void setBags(Map<String, Integer> bags)
	{
	    this.bags = bags;
	}

	public void setMoonroof(Map<String, Integer> moonroof)
	{
	    this.moonroof = moonroof;
	}
	
	//[MAIN]//
	
	public void setAuto(Automotive auto) throws ExceptionHandler 
	{
		model = auto.getModel();
		price = auto.getPrice();
		
		color = new LinkedHashMap<String, Integer>();
		auto.listOptions(color, "Color");
			
		transmission = new LinkedHashMap<String, Integer>();
		auto.listOptions(transmission, "Transmission");
			
		brake = new LinkedHashMap<String, Integer>();
		auto.listOptions(brake, "Brakes");
			
		bags = new LinkedHashMap<String, Integer>();
		auto.listOptions(bags, "Side Impact Air Bags");
			
		moonroof = new LinkedHashMap<String, Integer>();
		auto.listOptions(moonroof, "Power Moonroof");
	}
	
	//[GETTERS FOR SERIALIZED]//

	public String getSerializedModel()
	{
		String ret = "model~" + this.model;
	    ret = ret + ";";
	    return ret;
	}

	public String getSerializedPrice() 
	{
	    String ret = "price~" + this.price;
	    ret = ret + ";";
	    return ret;
	}

	public String getSerializedColor()
	{
	    String ret = "color~";
	    Iterator<?> itr = this.color.entrySet().iterator();
	    while (itr.hasNext()) 
	    {
	      ret = ret + itr.next() + "%";
	    }
	    ret = ret + ";";
	    return ret;
	}
	
	public String getSerializedTransmission() 
	{
		String ret = "transmission~";
		Iterator<?> itr = this.transmission.entrySet().iterator();
	    while (itr.hasNext()) 
	    {
	    	ret = ret + itr.next() + "%";
	    }
	    ret = ret + ";";
	    return ret;
	}

	public String getSerializedBrake()
	{
		String ret = "brake~";
	    Iterator<?> itr = this.brake.entrySet().iterator();
	    while (itr.hasNext()) 
	    {
	    	ret = ret + itr.next() + "%";
	    }
	    ret = ret + ";";
	    return ret;
	}

	public String getSerializedBags() 
	{
	    String ret = "bags~";
	    Iterator<?> itr = this.bags.entrySet().iterator();
	    while (itr.hasNext()) 
	    {
	    	ret = ret + itr.next() + "%";
	    }
	    ret = ret + ";";
	    return ret;
	}

	public String getSerializedMoonroof() 
	{
	    String ret = "moonroof~";
	    Iterator<?> itr = this.moonroof.entrySet().iterator();
	    while (itr.hasNext()) 
	    {
	    	ret = ret + itr.next() + "%";
	    }
	    ret = ret + ";";
	    return ret;
	}
}
