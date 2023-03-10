/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.db.access;

import com.nura.db.entity.UserDetails;
import hibernateutil.HibernateUtil;
import logger.LoggerUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Arun Kumar
 */
public class UserDetailsAccess {
    private static final LoggerUtil log = new LoggerUtil();

    public UserDetails getUsrDtls(String userName) {
        log.addLog("Entry get user details method");
        UserDetails usrDtls = null;
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String hql = "From UserDetails where userName = '" + userName + "'";
            Query query = session.createQuery(hql);
            java.util.List usrDtlsLst = query.list();
            if(usrDtlsLst.size() > 0){
                usrDtls = (UserDetails) usrDtlsLst.get(0);
            }
        } catch (Exception ex) {
            session.close();
            ex.printStackTrace();
        }
        log.addLog("Exit userd details method");
        return usrDtls;
    }
    
     public boolean alreadyExist(String userName) {
        log.addLog("Entry get user details method");
        boolean usrDtls = false;
        Session session = null;
        Transaction tx;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String hql = "From UserDetails where userName = '" + userName + "'";
            Query query = session.createQuery(hql);
            java.util.List usrDtlsLst = query.list();
            if(usrDtlsLst.size() > 0){
                usrDtls = true;
            }
        } catch (Exception ex) {
            session.close();
            ex.printStackTrace();
        }
        log.addLog("Exit userd details method");
        return usrDtls;
    }
   
    public static void main(String[] args) {
        new UserDetailsAccess().getUsrDtls("arun");
    }
}
