package com.alex.DAO;


import com.alex.Entity.Contact;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactDaoImpl.class);
    //    @Autowired
    private SessionFactory sessionFactory;


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Contact с").list();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Contact findById(Long id) {
        LOGGER.info("findById "+id);
        return (Contact) sessionFactory.getCurrentSession()
                .getNamedQuery("Contact.findByid")
                .setParameter("id",id).uniqueResult();
    }

    @Override
    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        LOGGER.info("save "+contact);
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        LOGGER.info("Contact deleted with id: " + contact.getId());
    }
    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

}

