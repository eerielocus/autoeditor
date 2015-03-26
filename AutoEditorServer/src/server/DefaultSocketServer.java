package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class DefaultSocketServer extends Thread
{
	private Socket socket;

	private ObjectInputStream ois = null;
	private BufferedInputStream bis = null;
	private FileOutputStream input = null;

	AutoServer server = new BuildCarModelOptions();

	public DefaultSocketServer(Socket socket) 
	{
		this.socket = socket;
	}

	public void run() 
	{
		if(openConnection()) 
		{
			handleSession();
		} 
		else 
		{
			System.out.println("Failed to open a connection.");
		}
	}
	//Control socket server.
	public boolean openConnection() 
	{
		try 
		{
			ois = new ObjectInputStream(socket.getInputStream());
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void handleSession() 
	{
		try 
		{
			String option = (String) ois.readObject();
			System.out.println("From client: " + option);
			//Client sends "Upload" command.
			if(option.equals("Upload")) 
			{
				//Create file properties and send.
				createFile("car.Properties");
				sendFile();

				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.flush();
				oos.writeObject("Ok");
				System.out.println("Message sent to the client.");
				
				//Create LinkedHashMap object using properties file.
				server.buildAutoProperty("car.Properties");
			} 
			//Client sends "Edit" command.
			else if(option.equals("Edit")) 
			{
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.flush();
				
				//Retrieve all uploaded automotives and print out.
				ArrayList<String> cars = server.getCars();
				System.out.println(cars.toString());
				oos.writeObject(cars);
				oos.flush();
				
				//Confirm server sent information to client.
				System.out.println("Message sent to the client.");
				
				//Confirm client request.
				String ecars = (String) ois.readObject();
				System.out.println("Client want to edit: " + ecars);
				
				//Send choice to be retrieved from LinkedHashMap.
				server.sendChoice(oos, ecars);
			} 
			else 
			{
				System.err.println("Invalid input from client.");
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void createFile(String name) throws IOException
	{
		//Create properties file from client side text file.
		File file = new File(name);
		input = new FileOutputStream(file);
		InputStream stream = socket.getInputStream();
		bis = new BufferedInputStream(stream, 1024);
	}
	
	public void sendFile() throws IOException
	{
		//Receive data and count it.
		byte[] bit = new byte[1024];
		int i = 0;
		int bitcount = 1024;
		
		while((i = bis.read(bit, 0, 1024)) != -1)
		{
			bitcount = bitcount + 1024;
			input.write(bit, 0, i);
		}
		//Display amount of data received.
		System.out.println("Bytes written: " + bitcount);
	}

	public void closeSession() 
	{
		try 
		{
			bis.close();
			input.close();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
}
