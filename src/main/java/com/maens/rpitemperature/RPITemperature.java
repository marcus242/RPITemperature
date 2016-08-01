package com.maens.rpitemperature;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Marcus on 2016-08-01.
 */
public class RPITemperature {
    public static void main(String[] args){

        RPITemperature rpit = new RPITemperature();
        rpit.test();

    }
    private void test(){

        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("local-H2-database");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.close();
            entityManagerFactory.close();
        }
        catch(Exception exception){
            System.out.println("An error occured:"+exception.getMessage());
        }finally {

        }
    }
}
