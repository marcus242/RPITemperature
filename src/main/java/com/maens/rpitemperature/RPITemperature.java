package com.maens.rpitemperature;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RPITemperature {
    public static void main(String[] args){

        final RPITemperature rpit = new RPITemperature();
        rpit.test();

    }
    private void test(){

        try {
            final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("local-H2-database");
            final EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.close();
            entityManagerFactory.close();
        }
        catch(Exception exception){
            System.out.println("An error occured:"+exception.getMessage());
        }
    }
}
