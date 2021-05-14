package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Creator_IT19234526 {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost/gadgetbadget", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String displayCreator()

	{
		String output = "";
		try

		{
			Connection con = connect();

			if (con == null) {
				return "Connection Error";
			}

			output = "<table border='2'><tr><th>Name</th><th>City</th>" + "<th>Contact Number</th>" + "<th>Email</th>"
					+ "<th>Field Of Interest</th>" + "<th>Current Budget</th>" + "<th>Update</th><th>Delete</th></tr>";

			String query = "select * from creator";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next())

			{

				String creatorid = Integer.toString(rs.getInt("creatorid"));
				String name = rs.getString("fullname");
				String city = rs.getString("city");
				String contactnum = Integer.toString(rs.getInt("contactnum"));
				String email = rs.getString("email");
				String fieldofinterest = rs.getString("fieldofinterest");
				String budget = Double.toString(rs.getDouble("currentbudget"));

				output += "<tr><td><input id='hidcreatoridUpdate' name='hidcreatoridUpdate' type='hidden' value='"
						+ creatorid + "'>" + name + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + contactnum + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + fieldofinterest + "</td>";
				output += "<td>" + budget + "</td>";

				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-creatorid='"
						+ creatorid + "'>" + "</td></tr>";

			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while Retreiving data";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String insertCreator(String name, String city, String contactnum, String email, String fieldofinterest,
			String budget) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Connection Error";
			}

			String query = " insert into creator(`creatorid`,`fullname`,`city`,`contactnum`,`email`,`fieldofinterest`,`currentbudget`)"
					+ " values (?, ?, ?, ?, ?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, city);
			preparedStmt.setInt(4, Integer.parseInt(contactnum));
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, fieldofinterest);
			preparedStmt.setDouble(7, Double.parseDouble(budget));

			preparedStmt.execute();
			con.close();

			String newCreator = displayCreator();
			output = "{\"status\":\"success\", \"data\": \"" + newCreator + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCreator(String creatorid, String name, String city, String contactnum, String email,
			String fieldofinterst, String budget) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error accessing the database";
			}

			String query = "UPDATE creator SET fullname=?,city=?,contactnum=?,email=?,fieldofinterest=?,currentbudget=?  WHERE creatorid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, name);
			preparedStmt.setString(2, city);
			preparedStmt.setInt(3, Integer.parseInt(contactnum));
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, fieldofinterst);
			preparedStmt.setDouble(6, Double.parseDouble(budget));
			preparedStmt.setInt(7, Integer.parseInt(creatorid));

			preparedStmt.execute();
			con.close();
			String newCreator = displayCreator();
			output = "{\"status\":\"success\", \"data\": \"" + newCreator + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Retriving data.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCreator(String creatorid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error accessing the database";
			}

			String query = "delete from creator where creatorid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(creatorid));

			preparedStmt.execute();
			con.close();

			// create JSON Object

			String newCreator = displayCreator();
			output = "{\"status\":\"success\", \"data\": \"" + newCreator + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
