/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doppelganger.role.system.pdassistant.controller;

import com.doppelganger.role.system.pdassistant.model.SecondaryAbilities;
import com.doppelganger.role.system.pdassistant.model.spinner.TiradaSpinnerModel;
import com.doppelganger.role.system.pdassistant.model.table.ImportantSecondaryAbilitiesTableModel;
import com.doppelganger.role.system.pdassistant.model.table.SecondaryAbilitiesTableModel;
import com.doppelganger.role.system.pdassistant.ui.PDAssistantMainWindow;
import com.doppelganger.role.system.pdassistant.util.services.UserPropertiesReader;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author SaulRC1
 */
public class PDAssistantMainWindowController {

    private PDAssistantMainWindow pdAssistantMainWindow = new PDAssistantMainWindow();

    private SecondaryAbilities secondaryAbilities;

    private SecondaryAbilitiesTableModel secondaryAbilitiesTableModel;
    
    private ImportantSecondaryAbilitiesTableModel importantSecondaryAbilitiesTableModel;
    
    private UserPropertiesReader userPropertiesReader = new UserPropertiesReader();

    public PDAssistantMainWindowController() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icono.png"));
        pdAssistantMainWindow.setIconImage(icon.getImage());

        String[] columnNames = {"Habilidad", "Valor", "Resultado De La Tirada"};

        pdAssistantMainWindow.tableMostImportantAbilities
                .setModel(new DefaultTableModel(columnNames, 0));

        pdAssistantMainWindow.tableSecondaryAbilities
                .setModel(new DefaultTableModel(columnNames, 0));

        initializeSpinner();

        initializeButtons();
        
        initializeSearchListener();
        
        initializeAnimaExcelData();

        pdAssistantMainWindow.setVisible(true);

    }
    
    private void initializeAnimaExcelData() {
        
        String lastAnimaExcelFile = userPropertiesReader.readLastAnimaExcelFileUserProperty();
        
        System.out.println("CurrentPath: " + userPropertiesReader.getCurrentRelativePath());
        
        if(lastAnimaExcelFile != null) {
            Runnable readTask = readExcelFileTask(lastAnimaExcelFile);

            Thread readTaskThread = new Thread(readTask);
            
            readTaskThread.start();
        }
        
    }
    
    private void initializeSearchListener() {
        pdAssistantMainWindow.textFieldSearchAbility.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                
                String searchFilter = pdAssistantMainWindow.textFieldSearchAbility.getText().toLowerCase();
                
                Integer tiradaResult = (Integer) pdAssistantMainWindow.spinnerTirada.getValue();
                
                if(secondaryAbilitiesTableModel != null && searchFilter != null && !searchFilter.isEmpty()) {
                    
                    secondaryAbilitiesTableModel.refreshDatUsingFilter(searchFilter, tiradaResult);   
                    
                } else if(secondaryAbilitiesTableModel != null && searchFilter != null && searchFilter.isEmpty()){
                    secondaryAbilitiesTableModel.refreshDatUsingFilter("", tiradaResult);
                }
                
            }
            
        });
    }

    private void initializeSpinner() {
        TiradaSpinnerModel tiradaSpinnerModel = new TiradaSpinnerModel();

        pdAssistantMainWindow.spinnerTirada.setModel(tiradaSpinnerModel);
    }

    private void initializeButtons() {

        pdAssistantMainWindow.botonCalcularResultado.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                Integer result = (Integer) pdAssistantMainWindow.spinnerTirada.getValue();

                System.out.println("Resultado Tirada = " + result);

                secondaryAbilitiesTableModel.refreshData(true, result);
                
                importantSecondaryAbilitiesTableModel.refreshData(true, result);

            }

        });

        pdAssistantMainWindow.botonAbrirFicha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirFichaButtonAction();
            }

        });

    }

    private void initializeTablesData() {
        pdAssistantMainWindow.tableSecondaryAbilities.setModel(secondaryAbilitiesTableModel);

        secondaryAbilitiesTableModel.refreshData(false, null);
        
        pdAssistantMainWindow.tableMostImportantAbilities.setModel(importantSecondaryAbilitiesTableModel);
        
        importantSecondaryAbilitiesTableModel.refreshData(false, null);
    }

    private Runnable readExcelFileTask(String pathToFile) {

        return new Runnable() {

            @Override
            public void run() {

                pdAssistantMainWindow.textFieldOpenedFile.setText(pathToFile);
                
                pdAssistantMainWindow.progressBarLoadingFile.setValue(10);
                
                pdAssistantMainWindow.progressBarLoadingFile.setStringPainted(true);
                
                pdAssistantMainWindow.progressBarLoadingFile.setString("Loading File...");

                try {

                    secondaryAbilities = new SecondaryAbilities(pathToFile);
                    
                    pdAssistantMainWindow.progressBarLoadingFile.setValue(70);
                
                    pdAssistantMainWindow.progressBarLoadingFile.setString("Loading Secondary Abilities...");

                    secondaryAbilitiesTableModel
                            = new SecondaryAbilitiesTableModel(secondaryAbilities.getSecondaryAbilities());
                    
                    importantSecondaryAbilitiesTableModel 
                            = new ImportantSecondaryAbilitiesTableModel(secondaryAbilities.getSecondaryAbilities());
                    
                    pdAssistantMainWindow.progressBarLoadingFile.setValue(90);
                
                    pdAssistantMainWindow.progressBarLoadingFile.setString("Initializing Table Data...");

                    initializeTablesData();
                    
                    pdAssistantMainWindow.progressBarLoadingFile.setValue(100);
                
                    pdAssistantMainWindow.progressBarLoadingFile.setString("File Loaded Successfully");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(pdAssistantMainWindow,
                            "Ha ocurrido un error", "ERROR", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(PDAssistantMainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                
                } catch (InvalidFormatException ex) {
                    Logger.getLogger(PDAssistantMainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    
                    Logger.getLogger(PDAssistantMainWindowController.class.getName()).log(Level.INFO, "Thread Execution Finished");
                     
                    Thread.currentThread().interrupt();
                    
                }

            }

        };
    }

    private void abrirFichaButtonAction() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter fileExtensionFilter
                = new FileNameExtensionFilter("Microsoft Excel Files",
                        "xlsm", "xlsx", "xls");

        fileChooser.setFileFilter(fileExtensionFilter);

        int selectedOption = fileChooser.showOpenDialog(pdAssistantMainWindow);

        if (selectedOption == JFileChooser.APPROVE_OPTION) {
            
            userPropertiesReader.setLastOpenedAnimaExcelFile(fileChooser.getSelectedFile().getAbsolutePath());
            
            userPropertiesReader.writeUserProperties();

            Runnable readTask = readExcelFileTask(fileChooser.getSelectedFile().getAbsolutePath());

            Thread readTaskThread = new Thread(readTask);
            
            readTaskThread.start();
        }
    }

}
