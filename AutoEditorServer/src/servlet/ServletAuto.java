package servlet;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.Server;

@WebServlet("/ServletAuto")
public class ServletAuto extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ServletAuto() throws UnknownHostException 
    {
        super();
    }

    //Open a new thread and run the server.
    public void init() throws ServletException 
    {
		super.init();
		
		Thread thread = new Thread(new Runnable() 
		{
			public void run() 
			{
			Server server = new Server(18999);
			server.runServer();
			}
		});
		thread.start();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	}
}
