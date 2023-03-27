package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.DBConnection;
import Model.Customer;

public class CustomerDao {
	public static void InsertSeller(Customer c) {
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "insert into customer (Name, Contact, Address, Email, Password) values (?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, c.getName());
			pst.setLong(2, c.getContact());
			pst.setString(3, c.getAddress());
			pst.setString(4, c.getEmail());
			pst.setString(5, c.getPassword());
			pst.executeUpdate();

			System.out.println("Data Inserted Succesfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Customer LoginSeller(Customer c) {
		Customer c1 = null;
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "select * from customer where Email=? and Password=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, c.getEmail());
			pst.setString(2, c.getPassword());

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				c1 = new Customer();
				c1.setID(rs.getInt("ID"));
				c1.setName(rs.getString("Name"));
				c1.setContact(rs.getLong("Contact"));
				c1.setAddress(rs.getString("Address"));
				c1.setEmail(rs.getString("Email"));
				c1.setPassword(rs.getString("Password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
	}

	public static void updateProfile(Customer c) {
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "update customer set Name=?, Contact=?, Address=?, Email=? where ID=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, c.getName());
			pst.setLong(2, c.getContact());
			pst.setString(3, c.getAddress());
			pst.setString(4, c.getEmail());
			pst.setInt(5, c.getID());
			pst.executeUpdate();

			System.out.println("Data Updated Succesfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkOldPassword(int ID, String OP) {
		boolean flag = false;
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "select * from customer where ID=? and Password=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setInt(1, ID);
			pst.setString(2, OP);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void changePassword(int ID, String NP) {
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "update customer set Password=? where ID=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, NP);
			pst.setInt(2, ID);
			pst.executeUpdate();
			System.out.println("password Changed.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkEmail(String Email) {
		boolean flag = false;
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "select * from customer where Email=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, Email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void changeNewPassword(String Email, String NP) {
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "update customer set Password=? where Email=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, NP);
			pst.setString(2, Email);
			pst.executeUpdate();
			System.out.println("Password Changed Succesfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}