package com.ananth.k2.repository;

import java.util.List;

import com.ananth.k2.dto.Visitor;

public interface VisitorDAO {
	
	public String registerVisitor(Visitor visitor);
	
	public String saveVisitor(Visitor visitor);

	public String updateVisitor(Visitor visitor);

	public Visitor getVisitorById(int id);

	public Visitor visitorLogin(String email, String password);
	
	public boolean deleteVisitor(int id);
	
	public boolean deleteRegisteredVisitor(int id);
	
	public Visitor getValidVisitorByid(int id);
	
	public List<Visitor> getAllRegisteredVisitor();
	
	public List<Visitor> getAllValidVisitor();
	
}
