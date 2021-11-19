package org.studyeasy.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.studyeasy.hibernate.entity.Users;

public class App {

	public static void main(String[] args)throws Exception {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).buildSessionFactory();
		//creating sessionfactory object
		
		Session session = factory.getCurrentSession(); //creating session object with the help of sessionfactory object
		//session object establishes connection to database 
		
		try {
			
			//create object of entity class type
			Users user = new Users("username","password","firstName","lastName");
			//Users user = new Users(); //Create object of entity class while retrieving data from db
			
			//Start transaction
			session.beginTransaction();
			
			//Perform operation
			session.save(user);
			/*user = session.get(Users.class, 2); //Retrieving a record from the db
			user.setUsername("Testname"); //performing update operation in the db by updating session object
			session.delete(user); // deleting session object
			*/
			//Commit the transaction
			session.getTransaction().commit();
			System.out.println("Row added!!!");
			/*System.out.println(user);
			*/
			
			//HQL implementation starts
			
			// Listing records from the db
			//note: we are not using commit since we are only retrieving and listing records from the db
			/*session.beginTransaction();
			List<Users> users = session.createQuery("from testdb.users").getResultList();
			for (Users i : users) {
				System.out.println(i);
			}*/
			
			//Where clause in HQL
			/*session.beginTransaction();
			List<Users> users = session.createQuery("from testdb.users where first_name = 'Rahul' OR last_name like '_i%'").getResultList();
			for (Users i : users) {
				System.out.println(i);
			}*/
			
			//Update query in HQL
			/*session.beginTransaction();
			session.createQuery("update testdb.users set password = 'Rahul#123' where first_name = 'Rahul'").executeUpdate();
			session.getTransaction().commit();*/
			
			//Delete query in HQL
			/*session.beginTransaction();
			session.createQuery("delete from testdb.users where password = 'password'").executeUpdate();
			session.getTransaction().commit();*/

			
		} finally {
			session.close();
			factory.close();
		}
	}
	
}