package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ExceptionHandler;
import adapter.ProxyAutomotive;
import model.Automotive;

@WebServlet("/ServletAutoOptions")
public class ServletAutoOptions extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ServletAutoOptions() 
	{
		super();
	}
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String path = getServletContext().getRealPath("car.Properties");
		out.println(path);
		doPost(request, response);
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		LinkedHashMap<String, Automotive> cars = ProxyAutomotive.getAutofleet();
		String choice = request.getParameter("model");
		//Build the automotive and then send information to JSP.
		Automotive auto = cars.get(choice);
		if (auto != null) 
		{
			AutoBean autobean = new AutoBean();
			try 
			{
				autobean.setAuto(auto);
			} 
			catch (ExceptionHandler e) 
			{
				e.printStackTrace();
			}		
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			out.println(autobean.getSerializedModel()
					+ autobean.getSerializedPrice()
					+ autobean.getSerializedColor()
					+ autobean.getSerializedTransmission()
					+ autobean.getSerializedBrake() 
					+ autobean.getSerializedBags()
					+ autobean.getSerializedMoonroof());
		}
	}
}
