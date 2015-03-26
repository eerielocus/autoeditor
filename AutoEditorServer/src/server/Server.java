package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	private ServerSocket socket;
	
	//Default server setup.
	public Server(int port) 
	{
		try 
		{
			socket = new ServerSocket(port);
			System.out.println("Server is listening on Port " + port);
		} 
		catch(IOException e) 
		{
			System.err.println("Could not listen on Port " + port);
		}
	}
	
	public void runServer() 
	{
		DefaultSocketServer defaultClient = null;
		try 
		{
			while(true) 
			{
				Socket sock = socket.accept();
				defaultClient = new DefaultSocketServer(sock);
	            defaultClient.start();
			}
        } 
		catch(IOException e) 
		{
        	e.printStackTrace();
        }
	}
	
	public static void main(String[] args) 
	{
		Server server = new Server(18999);
		server.runServer();
	}
}
