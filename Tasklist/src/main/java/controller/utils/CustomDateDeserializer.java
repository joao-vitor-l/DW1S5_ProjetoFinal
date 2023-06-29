package controller.utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class CustomDateDeserializer implements JsonDeserializer<Date>{
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
        DateFormat dateFormat = new SimpleDateFormat("MMM. dd, yyyy");
        try{
            String dateString = json.getAsString().replace(".", "");
            return dateFormat.parse(dateString);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
