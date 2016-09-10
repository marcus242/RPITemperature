package com.maens.rpitemperature;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maens.rpitemperature.entities.EntityTemperatureData;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


@ApplicationPath("/ws/*")
@Path("/temperature")
public class wsTemperature extends Application{

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServiceStatus(){
        return "{\"status\":\"200\"}";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addTemperatureData(InputStream incomingData) throws Exception {
        final StringBuilder receivedData = new StringBuilder();
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        final TemperatureDAOimpl temperatureDAO = new TemperatureDAOimpl();
        try{
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(incomingData));
            String line;
            while ((line = dataReader.readLine())!=null){
                receivedData.append(line);
            }
            System.out.println("Data Received="+receivedData.toString());
            final EntityTemperatureData tdata = gson.fromJson(receivedData.toString(), EntityTemperatureData.class);
            temperatureDAO.addTemperatureData(tdata);
        }
        catch(Exception err){
            return "{\"status\":\"" + err.toString() +"\"}";

        }
        return "200";
    }
}
