/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.db.persist;

import hibernateutil.HibernateUtil;
import logger.LoggerUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Arun Kumar
 */
public class PersistHashDtls {

    private static final LoggerUtil log = new LoggerUtil();

    public void persistFileDetails(com.nura.db.entity.HashDetails hashDtls) {
        log.addLog("Entry updatehashDtls method");
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(hashDtls);
            tx.commit();
            session.flush();
        } catch (Exception ex) {
            session.close();
            ex.printStackTrace();
        }
        log.addLog("Exit updatehashDtls method");
    }
}
