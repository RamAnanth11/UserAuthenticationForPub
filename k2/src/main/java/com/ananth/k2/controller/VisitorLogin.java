package com.ananth.k2.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ananth.k2.dto.Admin;
import com.ananth.k2.dto.Visitor;
import com.ananth.k2.service.VisitorServiceImplementation;

public class VisitorLogin {
	static BufferedReader scn = new BufferedReader(new InputStreamReader(System.in));
	static VisitorServiceImplementation dao = new VisitorServiceImplementation();

	public static void visitorLogin() {

		try {
			System.out.println("Enter your User Name: ");
			String userName = scn.readLine();
			System.out.println("Enter your Password: ");
			String password = scn.readLine();
			Visitor visitor = dao.visitorLogin(userName, password);
			if (visitor.getId() != 0) {
				System.out.println("Welcome " + visitor.getName() + "!");
				System.out.println("Enjoy your Visit");
			} else {
				System.out.println("No User Found!" + "\nPlease Try with correct details");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void adminLogin()
	{
		try {
			System.out.println("Enter your User Name: ");
			String userName = scn.readLine();
			System.out.println("Enter your Password: ");
			String password = scn.readLine();
			
			Admin a = dao.getAdmin(userName, password);
			if(a.getId()!=0)
			{
				System.out.println("Welcome "+a.getUserName());
			}
			else {
				System.out.println("Please provide proper info");
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
