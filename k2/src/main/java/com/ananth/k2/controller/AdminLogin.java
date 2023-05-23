package com.ananth.k2.controller;

import java.util.List;
import java.util.Scanner;

import com.ananth.k2.dto.Visitor;
import com.ananth.k2.service.VisitorServiceImplementation;

public class AdminLogin {
	static VisitorServiceImplementation service = new VisitorServiceImplementation();
	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
		AdminLogin login = new AdminLogin();
		login.choices();
	}

	public void choices() {
		System.out.println("Welcome Admin!");
		System.out.println("Enter what you want to do?");
		System.out.println("1. Validate V2isitor" + "\n2. Sort by Name" + "\n3. Sort by Email" + "\n4. Sort by Age");
		int i = scn.nextInt();
		switch (i) {
		case 1:
			System.out.println("Moving Contents from Registered Visitors to Validate Visitors");
			moveToValid();
			break;
		case 2:
			System.out.println();
			List<Visitor> list = service.sortValidVisitorByName();
			display(list);

			break;
		case 3:
			System.out.println();
			List<Visitor> list2 = service.sortValidVisitorByEmail();
			display(list2);

			break;
		case 4:
			System.out.println();
			List<Visitor> list3 = service.sortValidVisitorByAge();
			display(list3);
			break;
		}
	}

	public void moveToValid() {
		List<Visitor> registeredVisitor = service.getAllRegisteredVisitor();
		for (Visitor v : registeredVisitor) {
			service.saveValidVisitor(v);
			service.deleteRegisteredVisitor(v.getId());
		}
	}

	public void display(List<Visitor> list) {
		for (Visitor v : list)
			System.out.println(v);
	}

}
