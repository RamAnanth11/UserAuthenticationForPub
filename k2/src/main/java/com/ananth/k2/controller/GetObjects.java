package com.ananth.k2.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import com.ananth.k2.dto.Visitor;

public class GetObjects {
//	static Scanner scn = new Scanner(System.in);
	static BufferedReader scn = new BufferedReader(new InputStreamReader(System.in));
	static int id = 1;

	public static void main(String[] args) {
		Visitor vis = getVisitor();
		System.out.println(vis);
	}

	private static Visitor getVisitor()
	{
		Visitor v = new Visitor();
		v.setId(id);
		id++;
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
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return v;
	}
}
