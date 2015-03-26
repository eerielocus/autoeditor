package server;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

import adapter.BuildAuto;
import exception.ExceptionHandler;

public class BuildCarModelOptions implements AutoServer
{
	private static BuildAuto auto;
	//Build automotive object using property file.
	public void buildAutoProperty(String filename) throws ExceptionHandler
	{
		auto = new BuildAuto();
		auto.buildAutoProperty(filename);
	}
	//Send to client the object via name.
	public void sendChoice(ObjectOutputStream oos, String name)
	{
		auto.sendChoice(oos, name);
	}
	//Retrieve full list of automotive objects.
	public ArrayList<String> getCars()
	{
		ArrayList<String> cars = new ArrayList<String>();
		if(auto != null) 
		{
			cars.addAll(auto.getCars());
		}
		return cars;
	}
	
}