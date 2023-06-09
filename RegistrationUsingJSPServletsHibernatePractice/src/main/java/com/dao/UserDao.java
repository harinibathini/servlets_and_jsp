package com.dao;

import com.model.User;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {
    public void saveUser(User user){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
