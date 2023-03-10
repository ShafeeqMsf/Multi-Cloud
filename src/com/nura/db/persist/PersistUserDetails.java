/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.db.persist;

import com.nura.db.entity.UserDetails;
import hibernateutil.HibernateUtil;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Arun kumar
 */
public class PersistUserDetails {

    public static void persistData(Map<String, String> datas) {
        
        String userName = datas.get("userName");
        String pwd = datas.get("pwd");
        String address = datas.get("addr");
        String mailid = datas.get("mailid");
        String phno = datas.get("phno");
        String privateKey = datas.get("pvtKey");
        String publicKey = datas.get("pubKey");
        String groupKey = datas.get("groupId");

        Session session;
        Transaction tx;
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        UserDetails ud = new UserDetails();
        ud.setUserName(userName);
        ud.setPwd(pwd);
        ud.setPhoneNumber(phno);
        ud.setAddress(address);
        ud.setEmailid(mailid);
        ud.setPrivateKey(privateKey);
        ud.setPublicKey(groupKey);
        ud.setGroupId(groupKey);

        session.save(ud);
        tx.commit();
        session.flush();
        session.close();

    }
}
