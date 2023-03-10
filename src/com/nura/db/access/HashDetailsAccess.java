/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.db.access;

import com.nura.db.entity.FileDetails;
import com.nura.db.entity.HashDetails;
import hibernateutil.HibernateUtil;
import static java.lang.Math.log;
import java.util.List;
import logger.LoggerUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Arun Kumar
 */
public class HashDetailsAccess {

    private LoggerUtil log = new LoggerUtil();

    public List<HashDetails> getHashDtlsLst() {
        log.addLog("Entry get hash details method");
        java.util.List hashDtlsLst = null;
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String hql = "From HashDetails";
            Query query = session.createQuery(hql);
            hashDtlsLst = query.list();
        } catch (Exception ex) {
            session.close();
            ex.printStackTrace();
        }
        log.addLog("Exit hash details method");
        return hashDtlsLst;
    }
}
