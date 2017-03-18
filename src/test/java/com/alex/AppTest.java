package com.alex;

import com.alex.DAO.ContactDao;
import com.alex.Entity.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springMysql.xml")
//@ContextConfiguration("classpath:app-context-annotation.xml")
@Transactional
public class AppTest
{
    @Autowired
    private ContactDao contactDao;

    @Test
    //@Rollback(false)
    public void testAddContact(){

        Contact contact=new Contact();
        contact.setBirthDate(new Date());
        contact.setFirstName("Alex1");
        contact.setLastName("Alex1");

        Contact newContact = contactDao.save(contact);

        Contact insertedContact=contactDao.findById(newContact.getId());

        Assert.assertEquals(contact,insertedContact);
        System.out.println(contact);
    }

    @Test
    //@Rollback(false)
    public void testDeleteAccount(){

        for (Contact contact : contactDao.findAll()) {
            contactDao.delete(contact);
        }
        Assert.assertEquals(0,contactDao.findAll().size());
    }

    @Test
    public void testFindAll(){
        contactDao.findAll();
    }
}
