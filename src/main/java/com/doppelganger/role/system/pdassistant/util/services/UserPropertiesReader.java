/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doppelganger.role.system.pdassistant.util.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SaulRC1
 */
public class UserPropertiesReader {

    private String currentRelativePath;

    private String lastOpenedAnimaExcelFile;

    public static final String USER_PROPERTIES_PATH = "\\userproperties.txt";

    public static final String LAST_ANIMA_EXCEL_FILE_PROPERTY_NAME
            = "LAST_ANIMA_EXCEL_FILE=";

    public UserPropertiesReader() {

        Path programRelativePath = Paths.get("");

        this.currentRelativePath = programRelativePath.toAbsolutePath().toString();

        this.lastOpenedAnimaExcelFile = "";

    }

    public UserPropertiesReader(String lastOpenedAnimaExcelFile) {

        Path programRelativePath = Paths.get("");

        this.currentRelativePath = programRelativePath.toAbsolutePath().toString();

        this.lastOpenedAnimaExcelFile = lastOpenedAnimaExcelFile;
    }

    public String getCurrentRelativePath() {
        return currentRelativePath;
    }

    public void setCurrentRelativePath(String currentRelativePath) {
        this.currentRelativePath = currentRelativePath;
    }

    public String getLastOpenedAnimaExcelFile() {
        return lastOpenedAnimaExcelFile;
    }

    public void setLastOpenedAnimaExcelFile(String lastOpenedAnimaExcelFile) {
        this.lastOpenedAnimaExcelFile = lastOpenedAnimaExcelFile;
    }

    /**
     * Writes all user properties into user properties file
     */
    public void writeUserProperties() {

        FileWriter writer = null;

        try {
            File userPropertiesFile = new File(currentRelativePath + USER_PROPERTIES_PATH);

            userPropertiesFile.createNewFile();

            writer = new FileWriter(userPropertiesFile);

            if (lastOpenedAnimaExcelFile != null) {

                writer.write(LAST_ANIMA_EXCEL_FILE_PROPERTY_NAME + lastOpenedAnimaExcelFile);

            }

        } catch (IOException ex) {

            Logger.getLogger(UserPropertiesReader.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {

                writer.close();

            } catch (IOException ex) {

                Logger.getLogger(UserPropertiesReader.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

    }

    /**
     * Retrieves the last Anima excel file stored in user properties
     *
     * @return Path to Last Anima Excel File<br>
     * null - In case that there is no property in file
     */
    public String readLastAnimaExcelFileUserProperty() {

        BufferedReader reader = null;

        String lastAnimaExcelFile = null;

        try {

            File userPropertiesFile = new File(currentRelativePath + USER_PROPERTIES_PATH);

            if (userPropertiesFile.exists()) {
                reader = new BufferedReader(new FileReader(userPropertiesFile));

                String line;

                while ((line = reader.readLine()) != null) {

                    if (line.contains(LAST_ANIMA_EXCEL_FILE_PROPERTY_NAME)) {

                        lastAnimaExcelFile = line.split("=")[1];

                    }

                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserPropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserPropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(UserPropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return lastAnimaExcelFile;
    }

    public static void main(String[] args) {
        UserPropertiesReader userPropertiesReader = new UserPropertiesReader();

        userPropertiesReader.writeUserProperties();

        System.out.println(userPropertiesReader.readLastAnimaExcelFileUserProperty());
    }

}
