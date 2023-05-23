package com.ananth.k2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ananth.k2.dto.Admin;
import com.ananth.k2.util.ConnectionUtil;

public class AdminDAOImplementation implements AdminDAO {
	Connection connection = null;

	public String saveAdmin(Admin admin) {
		String query = "INSERT INTO admin VALUES (?,?,?);";
		connection = ConnectionUtil.getVisitorConnection();
		System.out.println("Connected!");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, admin.getId());
			ps.setString(2, admin.getUserName());
			ps.setString(3, admin.getPassWord());

			int res = ps.executeUpdate();
			connection.close();
			System.out.println(res + " number of rows are updated");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Admin adminLogin(String userName, String passWord) {
		String query = "SELECT * FROM admin WHERE user_name = ? and password = ?;";
		connection = ConnectionUtil.getVisitorConnection();
		System.out.println("Connected!");
		Admin a = null;
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, passWord);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				a = new Admin();
				a.setId(set.getInt(1));
				a.setUserName(set.getString(2));
				a.setPassWord(set.getString(3));
			}

		} catch (SQLException e) {
//			e.printStackTrace();
//			a.setId(0);
			return a;
		}
		return a;
	}
	
//	public static void main(String[] args)
//	{
//		AdminDAOImplementation dao = new AdminDAOImplementation();
//		Admin admin = dao.adminLogin("ananthram119@gmail.com", "Ananth0305?");
//		System.out.println(admin);
//		System.out.println("Welcome "+admin.getUserName());
//		
//	}
}