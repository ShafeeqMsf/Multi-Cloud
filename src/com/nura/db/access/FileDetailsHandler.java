/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.db.access;

import com.nura.db.entity.FileDetails;
import hibernateutil.HibernateUtil;
import java.util.Iterator;
import logger.LoggerUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Arun Kumar
 */
public class FileDetailsHandler {

    private static final LoggerUtil log = new LoggerUtil();

    public FileDetails getFileDetails(String fileName) {
        log.addLog("Entry get fildetails method");
        FileDetails fileDtls = null;
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String hql = "From FileDetails where fileName = '" + fileName + "'";
            Query query = session.createQuery(hql);
            java.util.List fileDtlsLst = query.list();
            fileDtls = (FileDetails) fileDtlsLst.get(0);
        } catch (Exception ex) {
            session.close();
            ex.printStackTrace();
        }
        log.addLog("Exit file details method");
        return fileDtls;
    }
}
