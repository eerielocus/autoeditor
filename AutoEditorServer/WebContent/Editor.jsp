<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="adapter.*" %>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Automotive Editor</title>
<LINK REL=STYLESHEET HREF="JSP-Styles.css" TYPE="text/css">
</head>
<script type="text/javascript" src='http://code.jquery.com/jquery-latest.min.js'></script>
<script type="text/javascript">
//Unfortunately, I was not able to do what I wanted in the time alotted. Fortunately, I had found
//a simple Javascript code able to retrieve the PrintWriter object and parse the information the way
//I wanted. Most likely, I'll have to rethink the way the servlet handles and sends the information.
$(document).ready(function() 
{
	$('#menu_model option:first-child').attr("selected", "selected");
    $('#menu_model').change(function()
    {
    	//AJAX: should make it possible to update information without reload, however, does not seem to be working.
        $.ajax(
        {
        	//Grab the information sent by servlet.
            url: '/AutoEditorServer/servletoptions',
            type: 'POST',
            data: "model=" + $('#menu_model').val(),
            success: function(data) 
            {
            	//Split the information into appropriate sets.
                var set = data.split(";");
                var modelset = set[0];
                var priceset = set[1];
                var colorset = set[2];
                var transmissionset = set[3];
                var brakeset = set[4];
                var bagset = set[5];
                var moonroofset = set[6];
                //Set parameter for each Option Set, then later each Option.
                var modelpoint = modelset.split("~");
                var model = modelpoint[1];
                $("#model").val(model);
                
                var pricepoint = priceset.split("~");
                var price = pricepoint[1];
                $("#price").val(price);
                
                var colorpoint = colorset.split("~");
                var colorpairs = colorpoint[1].split("%");
                var colors = [];
                $("#menu_color").empty();
                for(var i = 0; i < colorpairs.length; i++) 
                {
                	var val = colorpairs[i].split("=")[0];
                	if(val.trim().length > 0)
                	{
                		colors.push(val);
                    	$("#menu_color").append( $("<option>").val(colorpairs[i]).html(val));
                	}
                }
                
                var transmissionpoint = transmissionset.split("~");
                var transmissionpairs = transmissionpoint[1].split("%");
                var transmissions = [];
                $("#menu_transmission").empty();
                for(var i = 0; i < transmissionpairs.length; i++) 
                {
                	var val = transmissionpairs[i].split("=")[0];
                	if(val.trim().length > 0)
                	{
                		transmissions.push(val);
                		$("#menu_transmission").append( $("<option>").val(transmissionpairs[i]).html(val));
                	}
                }
                
                var brakepoint = brakeset.split("~");
                var brakepairs = brakepoint[1].split("%");
                var brakes = [];
                $("#menu_brake").empty();
                for(var i = 0; i < brakepairs.length; i++) 
                {
                	var val = brakepairs[i].split("=")[0];
                	if(val.trim().length > 0)
                	{
                		brakes.push(val);
                		$("#menu_brake").append( $("<option>").val(brakepairs[i]).html(val));
                	}
                }
                
                var bagpoint = bagset.split("~");
                var bagpairs = bagpoint[1].split("%");
                var bags = [];
                $("#menu_bag").empty();
                for(var i = 0; i < bagpairs.length; i++) 
                {
                	var val = bagpairs[i].split("=")[0];
                	if(val.trim().length > 0)
                	{
                		bags.push(val);
                		$("#menu_bag").append( $("<option>").val(bagpairs[i]).html(val));
                	}
                }
                var moonroofpoint = moonroofset.split("~");
                var moonroofpairs = moonroofpoint[1].split("%");
                var moonroofs = [];
                $("#menu_moonroof").empty();
                for(var i = 0; i < moonroofpairs.length; i++) 
                {
                	var val = moonroofpairs[i].split("=")[0];
                	if(val.trim().length > 0)
                	{
                		moonroofs.push(val);
                		$("#menu_moonroof").append( $("<option>").val(moonroofpairs[i]).html(val));
                	}
                }
            },
        });
    });
});
</script>
<body>
<CENTER>
	<H1>Automotive Editor</H1>
	<H4>by Michael Kang</H4>

    <jsp:useBean id="autobean" class="servlet.AutoBean" scope="page" />
	<jsp:useBean id="buildbean" class="server.BuildCarModelOptions" scope="page" />
	
	<% //Build the list of all the cars.
	    ArrayList<String> cars = new ArrayList<String>();
	    if(buildbean != null) 
	    {
			cars = buildbean.getCars(); 
		}
	    if(cars == null)
			cars.add("");
	%>
	
	<form action="Final.jsp" method="POST">
		<input type="hidden" id="model" name="model" value="${autobean.model}"/>
		<input type="hidden" id="price" name="price" value="${autobean.price}"/>
		<table border="1" cellpadding="10" width="400">
			<tr>
				<td><b>Make/Model:</b></td>
				<td><select id="menu_model" name="menu_model">
						<c:forEach var="item_model" items="<%=cars%>">
							<option value="${item_model}">${item_model}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><b>Color:</b></td>
				<td><select id="menu_color" name="menu_color">
						<c:forEach var="item_color" items="${autobean.color}">
							<option value="${item_color}">${item_color.key}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><b>Transmission:</b></td>
				<td><select id="menu_transmission" name="menu_transmission">
						<c:forEach var="item_transmission" items="${autobean.transmission}">
							<option value="${item_transmission}">${item_transmission.key}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><b>Brakes:</b></td>
				<td><select id="menu_brake" name="menu_brake">
						<c:forEach var="item_brake" items="${autobean.brake}">
							<option value="${item_brake}">${item_brake.key}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><b>Side Impact Air Bags:</b></td>
				<td><select id="menu_bag" name="menu_bag">
						<c:forEach var="item_bags" items="${autobean.bags}">
							<option value="${item_bag}">${item_bag.key}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><b>Power Moonroof:</b></td>
				<td><select id="menu_moonroof" name="menu_moonroof">
						<c:forEach var="item_moonroof" items="${autobean.moonroof}">
							<option value="${item_moonroof}">${item_moonroof.key}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input type="Submit" value="Done" />
	</form>
	</CENTER>
</body>
</html>