package com;
import java.sql.*;
public class Project
{ //A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3309/projects","root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	//=======================ADD PROJECT ===========================
	public String insertProject(String pcode, String pname, String rcode, String rname, String eprice,String pdesc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into projects(`projectID`,`projectCode`,`projectName`,`researcherCode`,`researcherName`,`estimatedProjectPrice`,`projectDesc`)"+ " values (?, ?, ?, ?, ? , ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pcode);
			preparedStmt.setString(3, pname);
			preparedStmt.setString(4, rcode);
			preparedStmt.setString(5, rname);
			preparedStmt.setDouble(6, Double.parseDouble(eprice));
			preparedStmt.setString(7, pdesc); 
			preparedStmt.execute();
			con.close();
			
			String newItems = readProjects(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";		}
		catch (Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//=======================ADD Schedule ===========================
	public String insertSchedule(String startdate, String enddate, String pid)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into project_scheduling(`scheduleID`,`startDate`,`estimatedEndDate`,`projectID`)"+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, startdate);
			preparedStmt.setString(3, enddate);
			preparedStmt.setString(4, pid); 
			preparedStmt.execute();
			con.close();
			String newItems = readSchedules(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";	
			 }
		catch (Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//========================= View all Projects ====================================
	
	public String readProjects()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Project Code</th><th>Project Name</th>" +
					"<th>Researcher Code</th>" +
					"<th>Researcher Name</th>" +
					"<th>Estimated Project Price</th>" +
					"<th>Project Description</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from projects";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String projectID = Integer.toString(rs.getInt("projectID"));
				String projectCode = rs.getString("projectCode");
				String projectName = rs.getString("projectName");
				String researcherCode = rs.getString("researcherCode");
				String researcherName = rs.getString("researcherName");
				String estimatedProjectPrice = Double.toString(rs.getDouble("estimatedProjectPrice"));
				String projectDesc = rs.getString("projectDesc");
				// Add into the html table
				output += "<tr><td>" + projectCode + "</td>";
				output += "<td>" + projectName + "</td>";
				output += "<td>" + researcherCode + "</td>";
				output += "<td>" + researcherName + "</td>";
				output += "<td>" + estimatedProjectPrice + "</td>";
				output += "<td>" + projectDesc + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-projectid='" + projectID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-projectid='" + projectID + "'></td></tr>"; 
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	//========================View all Schedules =================================
	
	public String readSchedules()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Start Date</th><th>Estimated End Date</th>" +
					"<th>Project ID</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from project_scheduling";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String scheduleID = Integer.toString(rs.getInt("scheduleID"));
				String startDate = rs.getString("startDate");
				String estimatedEndDate = rs.getString("estimatedEndDate");
				String projectID = rs.getString("projectID");

				// Add into the html table
				output += "<tr><td>" + startDate + "</td>";
				output += "<td>" + estimatedEndDate + "</td>";
				output += "<td>" + projectID + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-scheduleid='" + scheduleID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-scheduleid='" + scheduleID + "'></td></tr>"; 
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	//================= Update Projects ================================================
	
	public String updateProject(String pID, String pcode, String pname,String rcode, String rname, String eprice, String pdesc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE projects SET projectCode=?,projectName=?,researcherCode=?,researcherName=?,estimatedProjectPrice=?,projectDesc=?WHERE projectID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, pcode);
			preparedStmt.setString(2, pname);
			preparedStmt.setString(3, rcode);
			preparedStmt.setString(4, rname);
			preparedStmt.setDouble(5, Double.parseDouble(eprice));
			preparedStmt.setString(6, pdesc);
			preparedStmt.setInt(7, Integer.parseInt(pID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readProjects(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";		}
		catch (Exception e)
		{
			output = "Error while updating the project.";
			System.err.println(e.getMessage());
		}
		return output;
	}


	
	
	
	//======================= update schedules =================================
	
	public String updateSchedule(String sID, String startDate, String eDate,String pID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE project_scheduling SET startDate=?,estimatedEndDate=?,projectID=?WHERE scheduleID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, startDate);
			preparedStmt.setString(2, eDate);
			preparedStmt.setString(3, pID);
			preparedStmt.setInt(4, Integer.parseInt(sID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readSchedules(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";			}
		catch (Exception e)
		{
			output = "Error while updating the schedule.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//==============================Delete Projects =============================

	public String deleteProject(String projectID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from projects where projectID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(projectID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readProjects(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";			}
		catch (Exception e)
		{
			output = "Error while deleting the project.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//============================Delete Schedules =======================================
	
	public String deleteSchedule(String scheduleID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from project_scheduling where scheduleID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(scheduleID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readSchedules(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";			}
		catch (Exception e)
		{
			output = "Error while deleting the schedule.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
} 
