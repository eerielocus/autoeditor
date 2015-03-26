package server;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exception.ExceptionHandler;

public interface AutoServer
{
	public void buildAutoProperty(String filename) throws ExceptionHandler;
	public void sendChoice(ObjectOutputStream oos, String name);
	public ArrayList<String> getCars();
	
}
