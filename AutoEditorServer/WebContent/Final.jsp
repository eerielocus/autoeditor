<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//Pull in all necessary information, setup strings for each.
	double totalprice = 0;
	String color = "";
	String transmission = "";
	String brake = "", bag = "";
	String moonroof = "";
	String colorprice = "";
	String transmissionprice = "";
	String brakeprice = "";
	String bagprice = "";
	String moonroofprice = "";
	String model = request.getParameter("model");
	String price = request.getParameter("price");
	//Parse information sent by Editor into appropriate Strings.
	if(price != null && price.trim().length() > 0) 
	{
		System.out.println("Price:" + price);
		totalprice += Double.parseDouble(price);
		
		String[] set;
		String colorpair = request.getParameter("menu_color");
		if(colorpair != null && colorpair.trim().length() > 0) 
		{
			set = colorpair.split("=");
			color = set[0];
			colorprice = set[1];
			totalprice += Double.parseDouble(colorprice);
		}
		
		String transmissionpair = request.getParameter("menu_transmission");
		if (transmissionpair != null && transmissionpair.trim().length() > 0) 
		{
			set = transmissionpair.split("=");
			transmission = set[0];
			transmissionprice = set[1];
			totalprice += Double.parseDouble(transmissionprice);
		}
		
		String brakepair = request.getParameter("menu_brake");
		if (brakepair != null && brakepair.trim().length() > 0) 
		{
			set = brakepair.split("=");
			brake = set[0];
			brakeprice = set[1];
			totalprice += Double.parseDouble(brakeprice);
		}
		
		String bagpair = request.getParameter("menu_bag");
		if (bagpair != null && bagpair.trim().length() > 0) 
		{
			set = bagpair.split("=");
			bag = set[0];
			bagprice = set[1];
			totalprice += Double.parseDouble(bagprice);
		}
		
		String moonroofpair = request.getParameter("menu_moonroof");
		if (moonroofpair != null && moonroofpair.trim().length() > 0) 
		{
			set = moonroofpair.split("=");
			moonroof = set[0];
			moonroofprice = set[1];
			totalprice += Double.parseDouble(moonroofprice);
		}
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Automotive Editor</title>
</head>
<body>
<CENTER>
	<h3>Here is your customized car.</h3>
	<table border="1" cellpadding="10" width="400">
		<tr>
			<td><b><%=model%></b></td>
			<td>Price</td>
			<td><%=price%></td>
		</tr>
		<tr>
			<td><b>Color</b></td>
			<td><%=color%></td>
			<td><%=colorprice%></td>
		</tr>
		<tr>
			<td><b>Transmission</b></td>
			<td><%=transmission%></td>
			<td><%=transmissionprice%></td>
		</tr>
		<tr>
			<td><b>Brakes</b></td>
			<td><%=brake%></td>
			<td><%=brakeprice%></td>
		</tr>
		<tr>
			<td><b>Side Impact Air Bags</b></td>
			<td><%=bag%></td>
			<td><%=bagprice%></td>
		</tr>
		<tr>
			<td><b>Power Moonroof</b></td>
			<td><%=moonroof%></td>
			<td><%=moonroofprice%></td>
		</tr>
		<tr>
			<td><b>Total Price</b></td>
			<td></td>
			<td><%=totalprice%></td>
		</tr>
	</table>
</CENTER>
</body>
</html>