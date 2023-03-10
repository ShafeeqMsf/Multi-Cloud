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
public class PersistFileDetails {

    private static final LoggerUtil log = new LoggerUtil();

    public void persistFileDetails(com.nura.db.entity.FileDetails fileDtls) {
        log.addLog("Entry updateFileDtls method");
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(fileDtls);
            tx.commit();
            session.flush();
        } catch (Exception ex) {
            session.close();
            ex.printStackTrace();
        }
        log.addLog("Exit updateFileDtls method");
    }
}
