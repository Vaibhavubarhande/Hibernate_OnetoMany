package com.carnation;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.carnation.entity.Document;
import com.carnation.entity.User;

public class App {
	public static void main(String[] args) {

		Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class)
														   .addAnnotatedClass(Document.class); // Config

		SessionFactory sf = cfg.buildSessionFactory(); // Session build

		Session session = sf.openSession(); // session open

		session.beginTransaction(); // session start
		
		User u1 = new User();
		u1.setUserid(101);
		u1.setName("Vaibhav");
		u1.setAddress("Pune");
		
		User u2 = new User();
		u2.setUserid(102);
		u2.setName("Aliya");
		u2.setAddress("Mumbai");
		
		User u3 = new User();
		u3.setUserid(103);
		u3.setName("Ranbir");
		u3.setAddress("Banglore");
		
		session.save(u1);
		session.save(u2);
		session.save(u3);
		
		Document d1 = new Document();
		
		d1.setId(501);
		d1.setType("PAN");
		d1.setNumber("QWERTY");
		
		Document d2 = new Document();
		
		d2.setId(502);
		d2.setType("AADHAR");
		d2.setNumber("1234");
		
		session.save(d1);
		session.save(d2);
		
		
		List<Document> temp = Arrays.asList(d1,d2);
		
		u2.setDocuments(temp);
		
		session.save(u3);
		
		User u = session.get(User.class, 102);
		
		System.out.println(u.toString());
		
		
		session.getTransaction().commit();
		session.close(); // session close
	}
}
