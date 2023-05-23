package com.ananth.k2.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import com.ananth.k2.dto.Admin;
import com.ananth.k2.dto.Visitor;
import com.ananth.k2.repository.AdminDAO;
import com.ananth.k2.repository.AdminDAOImplementation;
import com.ananth.k2.service.VisitorServiceImplementation;

public class Controller {
	static BufferedReader scn = new BufferedReader(new InputStreamReader(System.in));
	static VisitorServiceImplementation dao = new VisitorServiceImplementation();

	public static void main(String[] args) {

		System.out.println("1. Admin Login" + "\n2. Visitor Login" + "\n3. Register Visitor" + "\n4. Exit");

		int c;
		try {
			c = Integer.parseInt(scn.readLine());
			switch (c) {
			case 1:
				adminLogin();
				break;
			case 2:
				visitorLogin();
				break;
			case 3:

				Visitor v = getVisitor(dao.getidforRegisteredVisitor());
				if (v.getAge() >= 18) {
					dao.saveVisitor(v);
					System.out.println("Successfully Done!");
				} else {
					System.out.println("Underage are  Not allowed inside please comeback later!");
				}

				break;
			case 4:
				break;

			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

	private static Visitor getVisitor(int id) {
		Visitor v = new Visitor();
		id++;
		v.setId(id);

		System.out.println(id);
		try {
			System.out.println("Enter Your Name :");// 1
			v.setName(scn.readLine());
			System.out.println("Enter Your EMail :");// 2
			v.setEmail(scn.readLine());
			System.out.println("Enter Your PhoneNo. :");// 3
			v.setPhoneNo(scn.readLine());
			System.out.println("Enter Your Gender :"); // 7
			v.setGender(scn.readLine());
			System.out.println("Enter Your Address :");// 8
			v.setAddress(scn.readLine());
			System.out.println("Enter Your Password :"); // 6
			v.setPassWord(scn.readLine());
			System.out.println("Enter Your Date of Birth in (dd/mm/yyyy):");// 4
			System.out.println("Enter the Year (yyyy)");
			int y = Integer.parseInt(scn.readLine());
			System.out.println("Enter the month (mm)");
			int m = Integer.parseInt(scn.readLine());
			System.out.println("Enter the Day (dd)");
			int d = Integer.parseInt(scn.readLine());
			String dates = y + "-" + m + "-" + d;
			Date date2 = Date.valueOf(dates);
			LocalDate date = LocalDate.of(y, m, d);
			LocalDate date1 = LocalDate.now();
			Period p = Period.between(date, date1);
			int age = p.getYears(); // 5
			v.setAge(age);
			v.setDob(date2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return v;
	}

	public static void visitorLogin() {

		try {
			System.out.println("Enter your User Name: ");
			String userName = scn.readLine();
			System.out.println("Enter your Password: ");
			String password = scn.readLine();
			Visitor visitor = dao.visitorLogin(userName, password);
			if (visitor.getName()

					!= null) {
				System.out.println(visitor);
				System.out.println("Welcome " + visitor.getName() + "!");
				System.out.println("Enjoy your Visit");
			} else {
				System.out.println("No User Found!" + "\nPlease Try with correct details");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void adminLogin() {
		try {
			System.out.println("Enter your User Name: ");
			String userName = scn.readLine();
			System.out.println("Enter your Password: ");
			String password = scn.readLine();

			Admin a = dao.getAdmin(userName, password);
			if (a != null) {
				System.out.println("Welcome " + a.getUserName());
			} else {
				System.out.println("Please provide proper info");
			}
			
			AdminLogin login = new AdminLogin();
			login.choices();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
