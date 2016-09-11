package com.maens.rpitemperature;

import com.maens.rpitemperature.entities.EntityTemperatureData;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



class TemperatureDAOimpl implements TemperatureDAO {
    TemperatureDAOimpl(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException cnf){
            System.out.println("Class not found exception"+cnf.toString());
        }
    }
    public List<EntityTemperatureData> getTempByDate(Date date) {
        try {

            final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql-db");
            final EntityManager entityManager = entityManagerFactory.createEntityManager();
            final String queryString = "FROM EntityTemperatureData where timestamp < :ltdate and timestamp > :gtdate";
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE,1);
            final Date ltDate = cal.getTime();

            final Query query = entityManager.createQuery(queryString);
            query.setParameter("gtdate",date);
            query.setParameter("ltdate",ltDate);

            final List result = query.getResultList();
            entityManager.close();
            entityManagerFactory.close();

            return result;
        }catch(Exception err ){
            return null;
        }
    }

    public List<EntityTemperatureData> getTempByMonth(int month) {
        ArrayList<EntityTemperatureData> data = new ArrayList<EntityTemperatureData>();
        return data;
    }

    public void addTemperatureData(EntityTemperatureData temperatureData) {
        try {
            final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql-db");
            final EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(temperatureData);
            entityManager.getTransaction().commit();
            entityManager.close();
            entityManagerFactory.close();

        }catch(Exception err){
            StringWriter sw = new StringWriter();
            err.printStackTrace(new PrintWriter(sw));
            System.out.println("Error in TemperatureDAOimpl.addTemperatureData(): "+sw.toString());
        }
    }
}

