package com.maens.rpitemperature;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maens.rpitemperature.entities.EntityTemperatureData;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@ApplicationPath("/ws/*")
@Path("/temperature")
public class wsTemperature extends Application{

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public String getServiceStatus(){
        return "{\"status\":\"200\"}";
    }

    @GET
    @Path("/date/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    //Should receive date in yyyy-MM-dd format
    public String getTemperatureByDate(@PathParam("date") String date){
        final DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        final TemperatureDAOimpl tDAO = new TemperatureDAOimpl();
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try{
            List result = tDAO.getTempByDate(dtf.parse(date));
            return gson.toJson(result);
        }catch(ParseException ex){
            return "500\n "+ ex.getMessage();
        }catch(Exception ex){
            return "500" + ex.getMessage();
        }
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
            final BufferedReader dataReader = new BufferedReader(new InputStreamReader(incomingData));
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
