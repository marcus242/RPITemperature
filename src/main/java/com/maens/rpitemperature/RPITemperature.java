package com.maens.rpitemperature;

import com.google.gson.*;
import com.maens.rpitemperature.entities.EntityTemperatureData;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class RPITemperature {
    public static void main(String[] args){

        final RPITemperature rpit = new RPITemperature();
        rpit.fetchDataByDate();
        //rpit.testInsert();

    }
    private void fetchDataByDate(){
        final Date dateNow = new Date();
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        final TemperatureDAOimpl temperatureDAO = new TemperatureDAOimpl();
        List<EntityTemperatureData> x = temperatureDAO.getTempByDate(dateNow);
        final String data = gson.toJson(x);

    }
    private void testInsert(){
        final String sampleData = "{'timestamp': '2016-09-10 16:56:01','location': 'Pi one', 'temperature': '25.312'}";
        try {
            //Small test to try to create an Entity from a sample JSON data via GSON and then persist it.
            System.out.println(sampleData);
            final Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            final EntityTemperatureData tdata = gson.fromJson(sampleData, EntityTemperatureData.class);
            //DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            //Date date = dtf.parse("2016-08-21 21:45:00");
            //final EntityTemperatureData td = new EntityTemperatureData("Livingroom", 25.5 ,date);

            final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql-db");
            final EntityManager entityManager = entityManagerFactory.createEntityManager();
            //DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            //Date date = dtf.parse("2016-08-21 21:45:00");
            //final EntityTemperatureData td = new EntityTemperatureData("Livingroom", 25.5 ,date);

            entityManager.getTransaction().begin();
            entityManager.persist(tdata);
            entityManager.getTransaction().commit();
            entityManager.close();
            entityManagerFactory.close();

        }catch (Exception err){
            System.out.println("Error: "+ err.toString());
        }
    }
}
