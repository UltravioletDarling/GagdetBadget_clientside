package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class researchProject {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost/gadgetbadget", "root", "");

			// For testing
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readResearch() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Research Name</th>"
					+ "<th>Research Description</th><th>Research Price</th>" + "<th>Research Date</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from research";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String researchID = Integer.toString(rs.getInt("researchID"));
				String researchName = rs.getString("researchName");
				String researchDescription = rs.getString("researchDescription");
				String researchPrice = Double.toString(rs.getDouble("researchPrice"));
				String researchDate = rs.getString("researchDate");

				// Add into the html table

				output += "<tr><td><input id='hidresearchIDUpdate' name='hidresearchIDUpdate' type='hidden' value='"
						+ researchID + "'>" + researchName + "</td>";

				output += "<td>" + researchDescription + "</td>";
				output += "<td>" + researchPrice + "</td>";
				output += "<td>" + researchDate + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-researchID='"
						+ researchID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the research.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert appointment
	public String insertResearch(String researchName, String researchDescription, String researchPrice,
			String researchDate) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into research (`researchID`,`researchName`,`researchDescription`,`researchPrice`,`researchDate`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, researchName);
			preparedStmt.setString(3, researchDescription);
			preparedStmt.setDouble(4, Double.parseDouble(researchPrice));
			preparedStmt.setString(5, researchDate);

			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Appointment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update appointment
	public String updateResearch(String researchID, String researchName, String researchDescription,
			String researchPrice, String researchDate) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE research SET researchName=?,researchDescription=?,researchPrice=?,researchDate=? WHERE researchID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, researchName);
			preparedStmt.setString(2, researchDescription);
			preparedStmt.setDouble(3, Double.parseDouble(researchPrice));
			preparedStmt.setString(4, researchDate);
			preparedStmt.setInt(5, Integer.parseInt(researchID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Research Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteResearch(String researchID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM research WHERE researchID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(researchID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON Object
			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
		} catch (Exception e) {
			// Create JSON object
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Appointment.\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}
}
