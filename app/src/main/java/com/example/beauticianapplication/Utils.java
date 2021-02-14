package com.example.beauticianapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Utils {

    public static String removeFirstLast(String sample) {
        sample = sample.substring(1);
        sample = sample.substring(0, sample.length() - 1);
        return sample;
    }

    public static ArrayList<String> parseMyJson(String sample) {
        sample = removeFirstLast(sample);
        ArrayList<String> resultSet = new ArrayList<String>();

        String[] arrOfStr = sample.split(", ");

        for (String a : arrOfStr) {
            a = removeFirstLast(a);
            resultSet.add(a);
        }

        return resultSet;
    }

    public static String userIDJsonInput(){
        String jsonRequest = "{\n" +
                "  \"beauticians\": " + DataHolder.getInstance().getUserID()  +"\n" +
                "}";

        return jsonRequest;
    }

    public static String userIDJsonInputForAli(){
        String jsonRequest = "{\n" +
                "  \"beautician_id\": " + DataHolder.getInstance().getUserID()  +"\n" +
                "}";

        return jsonRequest;
    }

    public static String userServiceHourEditJson(String day, String startHour, String startMinute, String endHour, String endMinute){
        String jsonRequest = "{\n" +
                "  \"day\": \"" + day  +"\",\n" +
                "  \"beauticians\": " + DataHolder.getInstance().getUserID()  +",\n" +
                "  \"startHour\": \"" + startHour  +"\",\n" +
                "  \"startMinute\": \"" + startMinute  +"\",\n" +
                "  \"endHour\": \"" + endHour  +"\",\n" +
                "  \"endMinute\": \"" + endMinute  +"\"\n" +
                "}";

        return jsonRequest;
    }


    public static ArrayList<ArrayList<String>> parseMyJsonList(String sample) {
        sample = removeFirstLast(sample);
        ArrayList<ArrayList<String>> resultSet = new ArrayList<ArrayList<String>>();

        if(sample.contentEquals(""))
            return resultSet;

        String[] arrOfStr = sample.split("], ");

        arrOfStr[arrOfStr.length - 1] = arrOfStr[arrOfStr.length - 1].substring(0,  arrOfStr[arrOfStr.length - 1].length() - 1);

        for (String a : arrOfStr) {

            String[] dataStr = a.split(", ");

            ArrayList<String> dateHolder = new ArrayList<String>();

            dateHolder.add(removeFirstLast(dataStr[0]));

            for (String b : dataStr) {
                if(b.contentEquals(dataStr[0]))
                    continue;
                while(b.charAt(0) == '[')
                    b = b.substring(1);
                while(b.charAt(b.length()-1) == ']')
                    b = b.substring(0, b.length() - 1);
                b = removeFirstLast(b);
                dateHolder.add(b);
            }

            resultSet.add(dateHolder);
        }

        return resultSet;
    }

    public static ArrayList<ArrayList<String>> servicesParse(String hello){
        hello = removeFirstLast(hello);
        ArrayList<ArrayList<String>> resultSet = new ArrayList<ArrayList<String>>();
        if(hello.contentEquals(""))
            return resultSet;
        hello = removeFirstLast(hello);
        String[] arrOfStr = hello.split("\\], \\[");

        for(String a: arrOfStr){
            String[] arrOfStr2 = a.split(", ");
            ArrayList<String> dateHolder = new ArrayList<String>();
            for(String b: arrOfStr2){
                b = removeFirstLast(b);
                dateHolder.add(b);
            }
            resultSet.add(dateHolder);
        }

        return resultSet;
    }

    public static Long dateToEpoch(String year, String month, String day) throws ParseException {
        String str = year + " " + month + " " + day;
        SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd");
        Date date = df.parse(str);
        long epoch = date.getTime();
        return epoch;
    }

    public static String serviceDeleteJson(String serviceName){
        String jsonRequest = "{\n" +
                "  \"beauticians\": " + DataHolder.getInstance().getUserID()  +",\n" +
                "  \"service\": \"" + serviceName  +"\",\n" +
                "  \"durum\": \"" + "delete"  +"\"\n" +
                "}";

        return jsonRequest;
    }

    public static String serviceAddJson(String serviceName){
        String jsonRequest = "{\n" +
                "  \"beauticians\": " + DataHolder.getInstance().getUserID()  +",\n" +
                "  \"service\": \"" + serviceName  +"\",\n" +
                "  \"durum\": \"" + "insert"  +"\"\n" +
                "}";

        return jsonRequest;
    }

    public static ArrayList<ArrayList<String>> parseMyHour(String sample){
        sample = removeFirstLast(sample);

        String[] arrOfStr = sample.split(", ");

        ArrayList<ArrayList<String>> resultSet = new ArrayList<ArrayList<String>>(Collections.nCopies(7, new ArrayList<String>()));


        for (String a : arrOfStr) {
            ArrayList<String> innerSet = new ArrayList<>();
            a = removeFirstLast(a);
            String[] arrOfStr2 = a.split(" ");
            String[] arrOfStr3 = arrOfStr2[1].split("\\.");
            innerSet.add(arrOfStr2[0]);
            innerSet.add(arrOfStr3[0]);
            innerSet.add(arrOfStr3[1]);
            arrOfStr3 = arrOfStr2[2].split("\\.");
            innerSet.add(arrOfStr3[0]);
            innerSet.add(arrOfStr3[1]);
            if((innerSet.get(0)).contentEquals("Pazartesi")){
                resultSet.set(0, innerSet);
            }else if((innerSet.get(0)).contentEquals("Cuma")){
                resultSet.set(4, innerSet);
            }else if((innerSet.get(0)).contentEquals("Cumartesi")){
                resultSet.set(5, innerSet);
            }else if((innerSet.get(0)).contentEquals("Pazar")) {
                resultSet.set(6, innerSet);
            }else if((innerSet.get(0)).contains("Sal")){
                resultSet.set(1, innerSet);
            }else if((innerSet.get(0)).contains("mba")){
                resultSet.set(2, innerSet);
            }else if((innerSet.get(0)).contains("mbe")){
                resultSet.set(3, innerSet);
            }
        }

        return resultSet;
    }

    public static String getCityListJson(){
        String jsonRequest = "{\n" +
                "  \"beautician_id\": " + DataHolder.getInstance().getUserID()  +",\n" +
                "  \"type\": " + 1  +"\n" +
                "}";

        return jsonRequest;
    }

    public static String getDistrictListJson(int cityKey){
        String jsonRequest = "{\n" +
                "  \"beautician_id\": " + DataHolder.getInstance().getUserID()  +",\n" +
                "  \"type\": " + 2  +",\n" +
                "  \"city_id\": " + cityKey  +"\n" +
                "}";

        return jsonRequest;
    }

    public static String deleteServiceLocationJson(int cityID, int districtTKey){
        String jsonRequest = "{\n" +
                "  \"beautician_id\": " + DataHolder.getInstance().getUserID()  +",\n" +
                "  \"type\": " + 3  +",\n" +
                "  \"city_id\": " + cityID  +",\n" +
                "  \"district_id\": " + districtTKey  +"\n" +
                "}";

        return jsonRequest;
    }

    public static String addServiceLocationJson(int cityID, int districtTKey,int day, int month, int year){
        String jsonRequest = "{\n" +
                "  \"beautician_id\": " + DataHolder.getInstance().getUserID()  +",\n" +
                "  \"type\": " + 4  +",\n" +
                "  \"city_id\": " + cityID  +",\n" +
                "  \"district_id\": " + districtTKey  +",\n" +
                "  \"day\": " + day  +",\n" +
                "  \"month\": " + month  +",\n" +
                "  \"year\": " + year  +"\n" +
                "}";

        return jsonRequest;
    }

    public static ArrayList<ArrayList<String>> allServicesParse(String hello){
        hello = removeFirstLast(hello);
        ArrayList<ArrayList<String>> resultSet = new ArrayList<ArrayList<String>>();
        if(hello.contentEquals(""))
            return resultSet;
        hello = removeFirstLast(hello);
        String[] arrOfStr = hello.split("\\], \\[");

        for(String a: arrOfStr){
            String[] arrOfStr2 = a.split(", ");
            ArrayList<String> serviceHolder = new ArrayList<String>();
            serviceHolder.add(removeFirstLast(arrOfStr2[0]));
            serviceHolder.add(arrOfStr2[1]);
            resultSet.add(serviceHolder);
        }

        return resultSet;
    }

    public static ArrayList<City> cityParser(String sample){
        String hello = removeFirstLast(sample);
        ArrayList<City> resultSet = new ArrayList<>();
        String[] arrOfStr = hello.split("[\\(\\)]");
        for(String a: arrOfStr){
            if(a.contains("\\n")){
                continue;
            }
            String[] arrOfStr2 = a.split(", ");
            City newOne = new City();
            newOne.setCityName(removeFirstLast(arrOfStr2[0]));
            newOne.setId(Integer.valueOf(arrOfStr2[1]));
            newOne.setKey(Integer.valueOf(arrOfStr2[2]));
            resultSet.add(newOne);
        }

        return resultSet;
    }

    public static ArrayList<District> districtParser(String hello){
        hello = removeFirstLast(hello);
        ArrayList<District> resultSet = new ArrayList<>();
        if(hello.contentEquals(""))
            return resultSet;
        hello = removeFirstLast(hello);
        String[] arrOfStr = hello.split("\\], \\[");

        for(String a: arrOfStr){
            String[] arrOfStr2 = a.split(", ");
            District newOne = new District();
            newOne.setDistrictName(removeFirstLast(arrOfStr2[0]));
            newOne.setId(Integer.valueOf(arrOfStr2[1]));
            resultSet.add(newOne);
        }

        return resultSet;
    }
}
