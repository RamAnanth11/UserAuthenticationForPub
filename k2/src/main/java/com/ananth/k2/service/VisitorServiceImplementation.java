package com.ananth.k2.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ananth.k2.dto.Admin;
import com.ananth.k2.dto.Visitor;
import com.ananth.k2.repository.AdminDAO;
import com.ananth.k2.repository.AdminDAOImplementation;
import com.ananth.k2.repository.VisitorDAO;
import com.ananth.k2.repository.VisitorDAOImplementation;
import com.ananth.k2.util.AES;
import com.ananth.k2.util.AppConstants;

public class VisitorServiceImplementation implements VisitorService {

	private VisitorDAO dao;
	private AdminDAO aDao;
	{
		dao = new VisitorDAOImplementation();
		aDao = new AdminDAOImplementation();
	}

	@Override
	public Admin getAdmin(String userName, String passWord) {
		Admin admin = null;
		try {
			admin = aDao.adminLogin(userName, passWord);
			return admin;
		} catch (NullPointerException e) {
//			e.printStackTrace();
		}
		System.out.println("returning null");
		return admin;
	}

	@Override
	public String saveVisitor(Visitor visitor) {
		String encEmail = AES.encrypt(visitor.getEmail(), AppConstants.SECRET_KEY);
//		String encPhone = AES.encrypt(visitor.getPhoneNo(), AppConstants.SECRET_KEY);
		String encAddress = AES.encrypt(visitor.getAddress(), AppConstants.SECRET_KEY);
		String encPassword = AES.encrypt(visitor.getPassWord(), AppConstants.SECRET_KEY);
		visitor.setEmail(encEmail);
		visitor.setPassWord(encPassword);
		visitor.setAddress(encAddress);
		visitor.setPhoneNo(AES.encrypt(visitor.getPhoneNo(), AppConstants.SECRET_KEY));

		return dao.registerVisitor(visitor);
	}

	public String saveValidVisitor(Visitor visitor) {
		visitor.setEmail(AES.encrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
		visitor.setAddress(AES.encrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
		visitor.setPhoneNo(AES.encrypt(visitor.getPhoneNo(), AppConstants.SECRET_KEY));
		visitor.setPassWord(AES.encrypt(visitor.getPassWord(), AppConstants.SECRET_KEY));
		return dao.saveVisitor(visitor);

	}

	@Override
	public String updateVisitor(Visitor visitor) {
		visitor.setEmail(AES.encrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
		visitor.setAddress(AES.encrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
		visitor.setPhoneNo(AES.encrypt(visitor.getPhoneNo(), AppConstants.SECRET_KEY));
		visitor.setPassWord(AES.encrypt(visitor.getPassWord(), AppConstants.SECRET_KEY));
		return dao.updateVisitor(visitor);
	}

	@Override
	public Visitor getVisitorById(int id) {
		Visitor visitor = dao.getVisitorById(id);
		visitor.setEmail(AES.decrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
		visitor.setPhoneNo(AES.decrypt(visitor.getPhoneNo(), AppConstants.SECRET_KEY));
		visitor.setAddress(AES.decrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
		visitor.setPassWord(AES.decrypt(visitor.getPassWord(), AppConstants.SECRET_KEY));
		return visitor;
	}

	@Override
	public Visitor visitorLogin(String email, String password) {
		return dao.visitorLogin(AES.encrypt(email, AppConstants.SECRET_KEY),
				AES.encrypt(password, AppConstants.SECRET_KEY));
	}

	@Override
	public boolean deleteVisitor(int id) {
		return dao.deleteVisitor(id);
	}

	public boolean deleteRegisteredVisitor(int id) {
		return dao.deleteRegisteredVisitor(id);
	}

	@Override
	public Visitor getValidVisitorByid(int id) {
		Visitor visitor = dao.getValidVisitorByid(id);
		visitor.setEmail(AES.decrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
		visitor.setPhoneNo(AES.decrypt(visitor.getPhoneNo(), AppConstants.SECRET_KEY));
		visitor.setAddress(AES.decrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
		visitor.setPassWord(AES.decrypt(visitor.getPassWord(), AppConstants.SECRET_KEY));
		return visitor;
	}

	@Override
	public List<Visitor> getAllRegisteredVisitor() {
		List<Visitor> list = dao.getAllRegisteredVisitor();
		List<Visitor> list2 = new ArrayList<>();
		for (Visitor visitor : list) {
			visitor.setEmail(AES.decrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
			visitor.setPhoneNo(AES.decrypt(visitor.getPhoneNo(), AppConstants.SECRET_KEY));
			visitor.setAddress(AES.decrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
			visitor.setPassWord(AES.decrypt(visitor.getPassWord(), AppConstants.SECRET_KEY));
			list2.add(visitor);
		}
		return list2;
	}

	public int getidforRegisteredVisitor() {
		try {
			List<Visitor> list = dao.getAllValidVisitor();
			return list.size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Visitor> getAllValidVisitor() {
		List<Visitor> list = dao.getAllValidVisitor();
		List<Visitor> list2 = new ArrayList<>();
		for (Visitor visitor : list) {
			visitor.setEmail(AES.decrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
			visitor.setPhoneNo(AES.decrypt(visitor.getPhoneNo(), AppConstants.SECRET_KEY));
			visitor.setAddress(AES.decrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
			visitor.setPassWord(AES.decrypt(visitor.getPassWord(), AppConstants.SECRET_KEY));
			list2.add(visitor);
		}
		return list2;
	}

	@Override
	public List<Visitor> sortVisitorByName() {
		List<Visitor> list = getAllRegisteredVisitor();
		Comparator<Visitor> com = (e1, e2) -> {
			return e1.getName().compareToIgnoreCase(e2.getName());
		};
		Collections.sort(list, com);
		return list;
	}

	@Override
	public List<Visitor> sortVisitorByEmail() {
		List<Visitor> list = getAllRegisteredVisitor();
		Comparator<Visitor> com = (e1, e2) -> {
			return e1.getEmail().compareToIgnoreCase(e2.getEmail());
		};
		Collections.sort(list, com);
		return list;
	}

	@Override
	public List<Visitor> sortVisitorByAge() {
		List<Visitor> list = getAllRegisteredVisitor();
		Comparator<Visitor> com = (e1, e2) -> {
			return e1.getAge() - e2.getAge();
		};
		Collections.sort(list, com);
		return list;
	}

	@Override
	public List<Visitor> sortValidVisitorByName() {
		List<Visitor> list = getAllValidVisitor();
		Comparator<Visitor> com = (e1, e2) -> {
			return e1.getName().compareToIgnoreCase(e2.getName());
		};
		Collections.sort(list, com);
		return list;
	}

	@Override
	public List<Visitor> sortValidVisitorByEmail() {
		List<Visitor> list = getAllValidVisitor();
		Comparator<Visitor> com = (e1, e2) -> {
			return e1.getEmail().compareToIgnoreCase(e2.getEmail());
		};
		Collections.sort(list, com);
		return list;
	}

	@Override
	public List<Visitor> sortValidVisitorByAge() {
		List<Visitor> list = getAllValidVisitor();
		Comparator<Visitor> com = (e1, e2) -> {
			return e1.getAge() - e2.getAge();
		};
		Collections.sort(list, com);
		return list;
	}

}
