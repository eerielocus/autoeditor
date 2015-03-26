package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Automotive;

public class Serialize 
{
	//Simple serialization method.
	public static void serialize(String filename, Automotive auto) 
	{
		try 
		{
			FileOutputStream f = new FileOutputStream(filename);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(auto);
			o.close();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	//Simple deserializiation method.
	public static Automotive deserialize(String filename)
	{
		try 
		{
			FileInputStream f = new FileInputStream(filename);
			ObjectInputStream o = new ObjectInputStream(f);
			
			try 
			{
				Automotive auto = (Automotive) o.readObject();
				o.close();
				return auto;
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				o.close();
				return null;
			}
		} 
		catch(IOException e) 
		{
			System.out.println("Unable to find.");
			return null;
		}
	}
}
