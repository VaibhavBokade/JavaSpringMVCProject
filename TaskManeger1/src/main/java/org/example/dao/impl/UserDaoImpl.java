package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.entities.User;
import org.example.exceptions.UserNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*In this class we implement UserDao and its method which are related to user*/
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    /*In this method we register the user
     * First we open session and then begin transaction
     * Then save the user*/
    @Override
    public void register(User user) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user by email.", e);
        }
    }

    /*In this method we get user by email
     * For this we can create query*/
    @Override
    public User getByEmail(String email) {
        try {
            Session session = sessionFactory.openSession();
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user by email.", e);
        }
    }

    /*In this method we get user by user id*/
    @Override
    public User getUserById(int userId) {
        try {
            Session session = sessionFactory.openSession();
            return session.get(User.class, userId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new UserNotFoundException("User not found!");
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user by ID.", e);
        }
    }
}
