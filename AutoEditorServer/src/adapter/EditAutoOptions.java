/**
 * Interface class containing necessary methods for EditOptions class.
 * 
 * @author Michael Kang
 */

package adapter;

import exception.ExceptionHandler;
import model.Automotive;

public interface EditAutoOptions 
{
	//Interface for Edit Options class:
	//[SETTERS]//
	
	public void setMake(Automotive auto, String name);
	public void setModel(Automotive auto, String name);
	public void setPrice(Automotive auto, int price);
	
	//[ADDERS]//
	
	public void addOpset(Automotive auto, String name);
	public void addOpn(Automotive auto, String set, String name, int price) throws ExceptionHandler;
	
	//[UPDATERS]//
	
	public void updateOpset(Automotive auto, String oldName, String newName) throws ExceptionHandler;
	public void updateOpName(Automotive auto, String oldName, String newName, String set) throws ExceptionHandler;
	public void updateOpPrice(Automotive auto, String set, String name, int price) throws ExceptionHandler;
	
	//[DELETERS]//
	
	public void deleteOpset(Automotive auto, String name) throws ExceptionHandler;
	public void deleteOp(Automotive auto, String op, String opset) throws ExceptionHandler;
}
