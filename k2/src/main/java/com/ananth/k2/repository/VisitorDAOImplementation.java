package com.ananth.k2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ananth.k2.dto.Visitor;
import com.ananth.k2.util.ConnectionUtil;

public class VisitorDAOImplementation implements VisitorDAO {

	Connection connection = null;

	@Override
	public String registerVisitor(Visitor visitor) {
		String query = "INSERT INTO registered_visitor VALUES(?,?,?,?,?,?,?,?,?)";

		connection = ConnectionUtil.getVisitorConnection();

		System.out.println("Connected!");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, visitor.getId());
			ps.setString(2, visitor.getName());
			ps.setString(3, visitor.getEmail());
			ps.setString(4, visitor.getPhoneNo());
			ps.setDate(5, visitor.getDob());
			ps.setString(6, visitor.getPassWord());
			ps.setInt(7, visitor.getAge());
			ps.setString(8, visitor.getGender());
			ps.setString(9, visitor.getAddress());

			int i = ps.executeUpdate();

			connection.close();

			return i + " no of lines executed";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	@Override
	public String saveVisitor(Visitor visitor) {
		String query = "INSERT INTO valid_visitor VALUES(?,?,?,?,?,?,?,?,?)";

		connection = ConnectionUtil.getVisitorConnection();

		System.out.println("Connected!");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, visitor.getId());
			ps.setString(2, visitor.getName());
			ps.setString(3, visitor.getEmail());
			ps.setString(4, visitor.getPhoneNo());
			ps.setDate(5, visitor.getDob());
			ps.setString(6, visitor.getPassWord());
			ps.setInt(7, visitor.getAge());
			ps.setString(8, visitor.getGender());
			ps.setString(9, visitor.getAddress());

			int i = ps.executeUpdate();

			connection.close();

			return i + " no of lines executed";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

			}

	@Override
	public String updateVisitor(Visitor visitor) {
		String query = "UPDATE registered_visitor SET name = ?, email = ?, phone = ?, dob = ?, age = ?, gender = ?, address = ?  WHERE id = ?;";
		connection = ConnectionUtil.getVisitorConnection();
		System.out.println("Connected!");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, visitor.getName());
			ps.setString(2, visitor.getEmail());
			ps.setString(3, visitor.getPhoneNo());
			ps.setDate(4, visitor.getDob());
			ps.setInt(5, visitor.getAge());
			ps.setString(6, visitor.getGender());
			ps.setString(7, visitor.getAddress());
			ps.setInt(8, visitor.getId());

			int res = ps.executeUpdate();

			connection.close();
			System.out.println(res + " number of rows are updated");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Visitor getVisitorById(int id) {
		String query = "SELECT * FROM registered_visitor WHERE id = ?;";
		Visitor v = null;
		connection = ConnectionUtil.getVisitorConnection();
		System.out.println("Connected!");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			v = new Visitor();
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				v.setId(set.getInt(1));
				v.setName(set.getString(2));
				v.setEmail(set.getString(3));
				v.setPhoneNo(set.getString(4));
				v.setDob(set.getDate(5));
				v.setPassWord(set.getString(6));
				v.setAge(set.getInt(7));
				v.setGender(set.getString(8));
				v.setAddress(set.getString(9));
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return v;
	}

	@Override
	public Visitor visitorLogin(String email, String password) {
		String query = "Select * from valid_visitor where email = ? and password = ?;";
		Visitor v = null;
		connection = ConnectionUtil.getVisitorConnection();
		System.out.println("Connected");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			v = new Visitor();
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				v.setId(set.getInt(1));
				v.setName(set.getString(2));
				v.setEmail(set.getString(3));
				v.setPhoneNo(set.getString(4));
				v.setDob(set.getDate(5));
				v.setPassWord(set.getString(6));
				v.setAge(set.getInt(7));
				v.setGender(set.getString(8));
				v.setAddress(set.getString(9));
			}

		} catch (SQLException e) {
//			e.printStackTrace();

			return v;
		}
		return v;
	}

	@Override
	public boolean deleteVisitor(int id) {
		String query = "DELETE FROM valid_visitor WHERE id = ?;";
		connection = ConnectionUtil.getVisitorConnection();
		System.out.println("Connected");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			connection.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteRegisteredVisitor(int id)
	{
		String query = "DELETE FROM registered_visitor WHERE id = ?;";
		connection = ConnectionUtil.getVisitorConnection();
		System.out.println("Connected");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			int update = ps.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Visitor getValidVisitorByid(int id) {
		String query = "SELECT * FROM valid_visitor  WHERE id = ?;";
		Visitor v = null;
		connection = ConnectionUtil.getVisitorConnection();
		System.out.println("Connected!");

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			v = new Visitor();
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				v.setId(set.getInt(1));
				v.setName(set.getString(2));
				v.setEmail(set.getString(3));
				v.setPhoneNo(set.getString(4));
				v.setDob(set.getDate(5));
				v.setPassWord(set.getString(6));
				v.setAge(set.getInt(7));
				v.setGender(set.getString(8));
				v.setAddress(set.getString(9));
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return v;
	}

	@Override
	public List<Visitor> getAllRegisteredVisitor() {
		String query = "SELECT * FROM registered_visitor;";
		connection = ConnectionUtil.getVisitorConnection();
		List<Visitor> list = null;

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet set = ps.executeQuery();

			if (set.isBeforeFirst()) {
				list = new ArrayList<>();
				while (set.next()) {
					Visitor v = new Visitor();
					v.setId(set.getInt(1));
					v.setName(set.getString(2));
					v.setEmail(set.getString(3));
					v.setPhoneNo(set.getString(4));
					v.setDob(set.getDate(5));
					v.setPassWord(set.getString(6));
					v.setAge(set.getInt(7));
					v.setGender(set.getString(8));
					v.setAddress(set.getString(9));
					list.add(v);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Visitor> getAllValidVisitor() {
		String query = "SELECT * FROM valid_visitor;";
		connection = ConnectionUtil.getVisitorConnection();
		List<Visitor> list = null;

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet set = ps.executeQuery();

			if (set.isBeforeFirst()) {
				list = new ArrayList<>();
				while (set.next()) {
					Visitor v = new Visitor();
					v.setId(set.getInt(1));
					v.setName(set.getString(2));
					v.setEmail(set.getString(3));
					v.setPhoneNo(set.getString(4));
					v.setDob(set.getDate(5));
					v.setPassWord(set.getString(6));
					v.setAge(set.getInt(7));
					v.setGender(set.getString(8));
					v.setAddress(set.getString(9));
					list.add(v);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	

}
