package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;
import exception.ExceptionHandler;

public class Tester 
{
	public static void main(String[] args) throws ExceptionHandler
	{
		//Build Automotive object from file.
		//Filename is incorrect, Exception handler will put correct filename in.
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("autoprop.txt");
		
		
		//Print data.
		a1.printAuto("Ford ZTW");
	}
}
