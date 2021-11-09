package com.library.utilities;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DB_util {

    private static Connection connection;
    private static Statement statemnet;
    private static ResultSet resultSet;
    private static ResultSetMetaData resultSetMetaData;

    public static void createConnection(String url, String username, String password){
        try {
            connection=DriverManager.getConnection(url,username,password);
            System.out.println("Connection is successful");
        } catch (Exception e) {
            System.out.println("Connection failed "+e.getMessage());;
        }
    }

    public static void createConnection(){
        String url = ConfigReader.read("hr.database.url");
        String username = ConfigReader.read("hr.database.username");
        String password = ConfigReader.read("hr.database.password");
    }

    public static ResultSet runQuery(String sql){
        try {
            statemnet=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet=statemnet.executeQuery(sql);
            resultSetMetaData=resultSet.getMetaData();
        } catch (Exception e) {
            System.out.println("Error occured while running Query "+e.getMessage());
        }
        return resultSet;

    }


    public static void destroy(){
        try {
            if (resultSet != null) resultSet.close();
            if (statemnet != null) statemnet.close();
            if (connection != null) connection.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error occured while closing Query "+e.getMessage());
        }
    }

    private static void resetCursor(){
        try {
            resultSet.beforeFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getRowCount(){
        int rowCount = 0;

        try {
            resultSet.last();
            rowCount=resultSet.getRow();
        } catch (Exception e) {
            System.out.println("Error occured while getting row count "+e.getMessage());
        }finally {
            resetCursor();
        }
        return rowCount;
    }

    public static int getColumnCount(){
        int columnCount=0;
        try {
            columnCount=resultSetMetaData.getColumnCount();
        } catch (Exception e) {
            System.out.println("Error occured while getting row count "+e.getMessage());
        }
        return columnCount;
    }

    public static List<String> getRowDataAsList(int rowNum){
        List<String> rowDataList = new ArrayList<>();
        int columnCount = getColumnCount();

        try {
            resultSet.absolute(rowNum);
            for (int i = 1; i < columnCount; i++) {
                rowDataList.add(resultSet.getString(i));
            }
        } catch (Exception e) {
            System.out.println("Error occurred while getRowDataAsList "+e.getMessage());
        }finally {
            resetCursor();
        }
    return rowDataList;
    }

    public static String getCellValue(int rowNum, int columnIndex){
        String cellValue = "";

        try {
            resultSet.absolute(rowNum);
            cellValue = resultSet.getString(columnIndex);
        } catch (Exception e) {
            System.out.println("Error occurred while getCellValue " + e.getMessage());
        }finally {
            resetCursor();
        }
        return cellValue;
    }

    public static String getCellValue(int rowNum, String columnName){
        String cellValue = "";
        try {
            resultSet.absolute(rowNum);
            cellValue=resultSet.getString(columnName);
        } catch (Exception e) {
            System.out.println("Error occurred while getCellValue " + e.getMessage());
        }finally {
            resetCursor();
        }
return cellValue;
    }

    public static String getFirstRowFirstColumn(){
        return getCellValue(1,1);
    }

    public static List<String> getColumnDataAsList(int columnNum){
        List <String> columnDataList = new ArrayList<>();
       try {
           resultSet.beforeFirst();
           while (resultSet.next()) {
               columnDataList.add(resultSet.getString(columnNum));
           }
       }catch (Exception e){
           System.out.println("Error occurred while getColumnDataAsList " + e.getMessage());
       }finally {
           resetCursor();
       }
       return columnDataList;
    }

    public static void displayAllData(){
        int conumnCount = getColumnCount();
        resetCursor();
        try{
            while(resultSet.next()){
                for (int i = 1; i < conumnCount; i++) {
                    System.out.printf("%-25s",resultSet.getString(i));
                }
                System.out.println();
            }
        }catch(Exception e){
            System.out.println("Error occurred while displayAllData " + e.getMessage());

        }finally {
            resetCursor();
        }
    }


    public static Map<String, String> getRowMap(int rowNum){
        Map<String, String> rowMap = new LinkedHashMap<>();
        int columnCount = getColumnCount();

        try {
            resultSet.absolute(rowNum);
            for (int i = 0; i <=columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                String cellValue = resultSet.getString(i);
                rowMap.put(columnName,cellValue);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getRowMap " + e.getMessage());
        }finally{
            resetCursor();
        }
        return rowMap;
    }


    public static List<Map<String, String>> getAllRowAsListOfMap(){
        List<Map<String, String>> allRowAsListOfMap = new ArrayList<>();

        int rowCount = getRowCount();

        for (int i = 1; i <=rowCount ; i++) {
            Map <String, String> rowMap = getRowMap(i);
            allRowAsListOfMap.add(rowMap);
        }
        resetCursor();
        return allRowAsListOfMap;
    }












}
