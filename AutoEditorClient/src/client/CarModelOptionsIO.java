package client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import adapter.BuildAuto;
import adapter.CreateAuto;
import model.Automotive;

public class CarModelOptionsIO extends Thread
{
	private Socket socket;
	private int sock;
	
	private ObjectOutputStream oos = null;
	private BufferedOutputStream bos = null;
	private FileInputStream fis = null;
	
	public CarModelOptionsIO(int sock) 
	{
		this.sock = sock;
	}
	
	public void run() 
	{
		while(true) 
		{
			if(openConnection()) 
			{
				handleSession();
				closeSession();
			}
		}
	}
	//Controls socket connection.
	public boolean openConnection() 
	{
		try 
		{
			socket = new Socket("localhost", sock);
			oos = new ObjectOutputStream(socket.getOutputStream());
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public void handleSession() 
	{
		try 
		{
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			//Simplified selection to numbers.
			//But client will still send string commands for easier management.
			System.out.println("\nHello! Please enter corresponding number:"
					+ "\n1.) Upload car properties."
					+ "\n2.) Edit car options.");
			
			int choice = Integer.parseInt(input.readLine());
			oos.flush();
			//Upload properties/text file.
			if(choice == 1) 
			{
				//Client sends "Upload" command to server.
				oos.writeObject("Upload");
				oos.flush();

				//Enter text file with data. (ex: fordztw.txt, toyotacam.txt)
				System.out.println("Please enter filename: ");
				String location = input.readLine();
				createFile(location);
				sendFile();

				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
				//Receive confirmation server communication.
				String confirmation = (String) ois.readObject();
				System.out.println("Server confirmation: " + confirmation);
			} 
			//Edit uploaded car profile.
			else if(choice == 2) 
			{
				oos.writeObject("Edit");
				oos.flush();
				
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
				//Receive list of uploaded automotives.
				ArrayList<String> cars = (ArrayList<String>) ois.readObject();
				System.out.println("Server auto list: ");
				System.out.println(cars);

				System.out.println("Enter the model name to edit: ");
				String edit = input.readLine();
				String carchoice = edit;
				
				oos.writeObject(edit);
				oos.flush();
				
				//Create Automotive object for editing.
				CreateAuto auto = new BuildAuto();
				auto.buildAuto((Automotive) ois.readObject());
				
				if(auto != null) 
				{
					System.out.println("Retrieved model from server: \n");
					
					//Start choose options and calculate final price.
					SelectCarOption chooser = new SelectCarOption();
					chooser.chooseOptions(auto, carchoice);
				}
			} 
			else 
			{
				System.err.println("Unable to retrieve.");
			}

		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void createFile(String name) throws IOException
	{
		File file = new File(name);
		OutputStream os = socket.getOutputStream();
		bos = new BufferedOutputStream(os, 1024);
		fis = new FileInputStream(file);
	}
	
	public void sendFile() throws IOException
	{
		//Send data and count it.
		byte[] bit = new byte[1024];
		int i = 0;
		int bitcount = 1024;
		
		while((i = fis.read(bit, 0, 1024)) != -1) 
		{
			bitcount = bitcount + 1024;
			bos.write(bit, 0, i);
			bos.flush();
		}
		//Display amount of data sent.
		socket.shutdownOutput();
		System.out.println("Bytes sent: " + bitcount);
	}

	public void closeSession() 
	{
		try 
		{
			oos.close();
			bos.close();
			fis.close();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String arg[]) 
	{
		CarModelOptionsIO client;
		client = new CarModelOptionsIO(18999);
		client.start();
	}
}
