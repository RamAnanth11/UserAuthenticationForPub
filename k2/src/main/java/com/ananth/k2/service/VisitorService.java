package com.ananth.k2.service;

import java.util.List;

import com.ananth.k2.dto.Admin;
import com.ananth.k2.dto.Visitor;

public interface VisitorService {
	
	public Admin getAdmin(String userName, String passWord);
	
	public String saveVisitor(Visitor visitor);

	public String updateVisitor(Visitor visitor);

	public Visitor getVisitorById(int id);

	public Visitor visitorLogin(String email, String password);

	public boolean deleteVisitor(int id);

	public Visitor getValidVisitorByid(int id);

	public List<Visitor> getAllRegisteredVisitor();

	public List<Visitor> getAllValidVisitor();

	public List<Visitor> sortVisitorByName();

	public List<Visitor> sortVisitorByEmail();

	public List<Visitor> sortVisitorByAge();

	public List<Visitor> sortValidVisitorByName();

	public List<Visitor> sortValidVisitorByEmail();

	public List<Visitor> sortValidVisitorByAge();

}
