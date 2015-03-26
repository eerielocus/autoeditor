/**
 * Test driver for multithreading.
 * 
 * @author Michael Kang
 */

package driver;

import java.util.LinkedHashMap;

import adapter.BuildAuto;
import adapter.CreateAuto;
import exception.ExceptionHandler;
import model.Automotive;
import scale.EditOption;

public class EditOptionDriver
{
	public static void main(String[] args) throws ExceptionHandler
	{
		LinkedHashMap<String, Automotive> autofleet;
		
		//Build Automotive object from file.
		CreateAuto a1 = new BuildAuto();
		autofleet = a1.buildAuto("auto.txt");
		
		//Start multithread test.
		EditOption edit = new EditOption(autofleet, "Ford Wagon ZTW");
		Thread t1 = new Thread(edit);
		Thread t2 = new Thread(edit);
		
		t1.start();
		t2.start();
	}
}
