package com.maens.rpitemperature;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Marcus on 2016-08-20.
 */
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
        try{
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = dataReader.readLine())!=null){
                receivedData.append(line);
            }
        }
        catch(Exception err){
            //Return error data
            return "{\"status\":\"" + err.toString() +"\"}";
        }
        return "200";
    }
}






/*
 StringBuilder receivedData = new StringBuilder();
        try{
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(incomingJsonData));
            String line = null;
            while ((line = dataReader.readLine()) != null){
               receivedData.append(line);
            }
        }catch(Exception error){
            return "An error occurred" + error.toString();
        }

        Gson jsonData = new Gson();

        //System.out.println(jsonData.toString(4));
        return "ok, i received this data" + receivedData.toString();

*/
