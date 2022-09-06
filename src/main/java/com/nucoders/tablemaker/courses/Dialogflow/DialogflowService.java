package com.nucoders.tablemaker.courses.Dialogflow;

import com.nucoders.tablemaker.courses.Course;
import com.nucoders.tablemaker.courses.CourseService;

import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DialogflowService {
    private final CourseService courseService;

    public DialogflowService(CourseService courseService) {
        this.courseService = courseService;
    }


    public String fullfilmentResponse(String req){
        JSONObject requestBody = new JSONObject(req);
        JSONArray courseCodesJson = requestBody.getJSONObject("queryResult").getJSONObject("parameters").getJSONArray("course");
        ArrayList<String> coursesList = new ArrayList<String>();     
        
        if (courseCodesJson != null) { 
            for (int i=0;i<courseCodesJson.length();i++){ 
                coursesList.add(courseCodesJson.getString(i));
            } 
        }
        
        ArrayList<ArrayList<ArrayList<Course>>> tables = courseService.createTables(coursesList);

        return formatTextResponse(createTablesResponse(tables));
    }


    public ArrayList<String> createTablesResponse(ArrayList<ArrayList<ArrayList<Course>>> tablesList){
        System.out.println("createTablesResponse");
        ArrayList<String> response = new ArrayList<String>();
        Integer tablesLength = tablesList.size();
        for (int i=0;i<tablesLength;i++){
            ArrayList<ArrayList<Course>> table = tablesList.get(i);
            String tableResponse = "";
            for(int j = 0; j<table.size();j++){
                ArrayList<Course> courseFull = table.get(j);
                String courseResponse = "";
                for(int k = 0; k<courseFull.size();k++){
                    
                    Course coursePart = courseFull.get(k);
                    Integer type = coursePart.getType();
                    Integer subSection = coursePart.getSubSection();
                    Integer day = coursePart.getDay();
                    ArrayList<String> typesArr = new ArrayList<>(Arrays.asList("Lecture", "Lab", "Tutorial"));
                    ArrayList<String> daysArr = new ArrayList<>(Arrays.asList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"));
                    ArrayList<String> subSectionArr = new ArrayList<>(Arrays.asList(coursePart.getSection().toString(), "A", "B"));
                    String typeString = "";
                    String dayString=  "";
                    String subSectionString = "";
                    typeString = typesArr.get(type);
                    dayString = daysArr.get(day);
                    subSectionString = subSectionArr.get(subSection);

                    courseResponse = courseResponse + coursePart.getName() + " " + coursePart.getCode() + "\n" + typeString + " "+ subSectionString + 
                        "\n" + dayString + " " + coursePart.getStartTime() + " - " + coursePart.getEndTime() + "\n" ;
                    
                }
                tableResponse = tableResponse + courseResponse+"----------\n";
            }
            response.add("Table "+Integer.toString(i+1)+"\n"+tableResponse);
        }
        
        return response;
    }

    
    public String formatTextResponse( ArrayList<String> stringResponse) {
        Integer responsesLength = stringResponse.size();
        String fullfillmentBegining = "{\"fulfillmentMessages\":[";
        String textBegining = "{\"text\":{\"text\":[\"";
        String textEnding = " \"]}}";
        String textComa = ",";
        String fullfillmentEnding = "]}";
        String newStringResponse;
        if (responsesLength.equals(1)){
            System.out.println("1");
            newStringResponse = fullfillmentBegining + textBegining + stringResponse.get(0) + textEnding + fullfillmentEnding;

        }else {
            System.out.println("more");
            newStringResponse = fullfillmentBegining;
            for(int i =0; i<stringResponse.size(); i++){
                newStringResponse =  newStringResponse+ textBegining + stringResponse.get(i) + textEnding;
                if (i != stringResponse.size()-1){
                    newStringResponse = newStringResponse + textComa;
                }
            }
            newStringResponse = newStringResponse +fullfillmentEnding;
        }
        
        System.out.println(newStringResponse);
        return newStringResponse;
    }
}
    

