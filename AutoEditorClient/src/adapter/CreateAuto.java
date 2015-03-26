package adapter;

import java.util.LinkedHashMap;
import java.util.ListIterator;

import exception.ExceptionHandler;
import model.Automotive;

public interface CreateAuto
{
	public LinkedHashMap<String, Automotive> buildAuto(String filename) throws ExceptionHandler;
	public void buildAuto(Automotive auto);
	public ListIterator<String> getOpsetIterator(String name);
	public ListIterator<String> getOpIterator(String name, String set) throws ExceptionHandler;
	public int getOpPrice(String name, String set, String op) throws ExceptionHandler;
	public void setOpChoice(String name, String opset, String op) throws ExceptionHandler;
	public void printAuto(String name);
	public void printFinal(String name) throws ExceptionHandler;
}
