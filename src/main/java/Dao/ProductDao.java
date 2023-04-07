package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.DBConnection;
import Model.Product;

public class ProductDao {
	public static void uploadProduct(Product p) {
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "insert into product(SID,Image,PName,PPrice,PCategory,PDesc) values (?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setInt(1, p.getSID());
			pst.setString(2, p.getImage());
			pst.setString(3, p.getPName());
			pst.setInt(4, p.getPPrice());
			pst.setString(5, p.getPCategory());
			pst.setString(6, p.getPDesc());
			pst.executeUpdate();

			System.out.println("Product Data Upload Succesfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Product> getProductBySID(int SID) {
		List<Product> list = new ArrayList<>();
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "select * from product where SID =?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setInt(1, SID);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product p = new Product();
				p.setPID(rs.getInt("PID"));
				p.setSID(rs.getInt("SID"));
				p.setImage(rs.getString("Image"));
				p.setPName(rs.getString("PName"));
				p.setPPrice(rs.getInt("PPrice"));
				p.setPCategory(rs.getString("PCategory"));
				p.setPDesc(rs.getString("PDesc"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Product> getAllProduct() {
		List<Product> list = new ArrayList<Product>();
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "select * from product";
			PreparedStatement pst = connection.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product p = new Product();
				p.setPID(rs.getInt("PID"));
				p.setSID(rs.getInt("SID"));
				p.setImage(rs.getString("Image"));
				p.setPName(rs.getString("PName"));
				p.setPPrice(rs.getInt("PPrice"));
				p.setPCategory(rs.getString("PCategory"));
				p.setPDesc(rs.getString("PDesc"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Product getProductByPID(int ID) {
		Product p = null;
		try {
			Connection connection = DBConnection.createConnection();
			String sql = "select * from product where PID=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setInt(1, ID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p = new Product();
				p.setPID(rs.getInt("PID"));
				p.setSID(rs.getInt("SID"));
				p.setImage(rs.getString("Image"));
				p.setPName(rs.getString("PName"));
				p.setPPrice(rs.getInt("PPrice"));
				p.setPCategory(rs.getString("PCategory"));
				p.setPDesc(rs.getString("PDesc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public static void removeProductBySeller(int PID) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "delete from product where PID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, PID);
			pst.executeUpdate();
			System.out.println("Product Delete by Seller Succesfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}