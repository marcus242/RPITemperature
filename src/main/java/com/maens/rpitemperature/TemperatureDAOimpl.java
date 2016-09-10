package com.maens.rpitemperature;

import com.maens.rpitemperature.entities.EntityTemperatureData;

import com.mysql.jdbc.Driver;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


class TemperatureDAOimpl implements TemperatureDAO {

    public List<EntityTemperatureData> getTempByDate(Date date) {
        ArrayList<EntityTemperatureData> data = new ArrayList<EntityTemperatureData>();
        return data;
    }

    public List<EntityTemperatureData> getTempByMonth(int month) {
        ArrayList<EntityTemperatureData> data = new ArrayList<EntityTemperatureData>();
        return data;
    }

    public void addTemperatureData(EntityTemperatureData temperatureData) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

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

