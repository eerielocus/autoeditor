package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ListIterator;

import adapter.CreateAuto;
import exception.ExceptionHandler;

public class SelectCarOption
{
	public void chooseOptions(CreateAuto auto, String name) throws ExceptionHandler
	{
		System.out.println("Choose options for your car:\n");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		//Begin listing Option Sets and requesting Option to be chosen.
		ListIterator<String> setlist = auto.getOpsetIterator(name);
		
		while(setlist.hasNext())
		{
			String opset = setlist.next().toString();
			ListIterator<String> oplist = auto.getOpIterator(name, opset);
			
			System.out.println(opset + ":");
			
			while(oplist.hasNext())
			{
				String op = oplist.next().toString();
				int price = auto.getOpPrice(name, opset, op);
				System.out.println(op + ": $" + price);
			}
			
			String choice = "";
			//Client inputs option choice.
			try 
			{
				choice = input.readLine();
				auto.setOpChoice(name, opset, choice);
			} 
			catch(IOException e) 
			{
				e.printStackTrace();
			}
		}
		//Print final loadout and price based on client choices.
		System.out.println();
		auto.printFinal(name);
	}
}
