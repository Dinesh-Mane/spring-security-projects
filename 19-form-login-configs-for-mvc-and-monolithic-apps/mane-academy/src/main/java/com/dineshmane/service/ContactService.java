package com.dineshmane.service;

import com.dineshmane.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
@Service
// @RequestScope
// @SessionScope
@ApplicationScope
public class ContactService {

    private int counter = 0;
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);


    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        //TODO - Need to persist the data into the DB table
        log.info(contact.toString());
        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
