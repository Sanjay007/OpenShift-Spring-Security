package com.mkyong.users.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mkyong.log.RunInvLogger;
import com.mkyong.users.model.User;

@Component
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();
try{
		users = getSessionFactory().openSession().createQuery("from User where username=?")
				.setParameter(0, username).list();
}catch(Exception e){
	
	
}
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> loadAll() {
		List<User> users=new ArrayList<User>();
		try{
		users = getSessionFactory().openSession().createQuery("from User").list();
		}catch(Exception e){
			// RunInvLogger.getbaseLogger().debug("Unable To get List OF All User");
		}
		return users;
	}

	@Override
	public void updateUser(User inUser) {
		
		Session session = getSessionFactory().openSession();
        org.hibernate.Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            session.saveOrUpdate(inUser);
            session.flush();
            transaction.commit();
            
            System.out.println("User records updated!");
        } catch (HibernateException e) {
 
            transaction.rollback();
 
            e.printStackTrace();
 
        } finally {
        	
            session.close();
 
        }
		
	}
	
	public void deleteUser(User inUser) {
		 
        Session session = getSessionFactory().openSession();
       org.hibernate.Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            
            session.delete(inUser);
            session.flush();
            transaction.commit();
            
            System.out.println("User records deleted!");
 
        } catch (HibernateException e) {
 
            transaction.rollback();
 
            e.printStackTrace();
 
        } finally {
 
            session.close();
 
        }
    }

}