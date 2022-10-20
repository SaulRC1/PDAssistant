/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doppelganger.role.system.pdassistant.controller;

import com.doppelganger.role.system.pdassistant.model.SecondaryAbilities;
import com.doppelganger.role.system.pdassistant.model.spinner.TiradaSpinnerModel;
import com.doppelganger.role.system.pdassistant.model.table.SecondaryAbilitiesTableModel;
import com.doppelganger.role.system.pdassistant.ui.PDAssistantMainWindow;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author SaulRC1
 */
public class PDAssistantMainWindowController {
    
    private PDAssistantMainWindow pdAssistantMainWindow = new PDAssistantMainWindow();
    
    private SecondaryAbilities secondaryAbilities;
    
    private SecondaryAbilitiesTableModel secondaryAbilitiesTableModel;

    public PDAssistantMainWindowController() {
        try {
            
            secondaryAbilities = new SecondaryAbilities();
            
            secondaryAbilitiesTableModel = 
                    new SecondaryAbilitiesTableModel(secondaryAbilities.getSecondaryAbilities());
            
        } catch (IOException ex) {
            Logger.getLogger(PDAssistantMainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icono.png"));
        pdAssistantMainWindow.setIconImage(icon.getImage());
        
        initializeSpinner();
        
        initializeTablesData();
        
        initializeButtons();
        
        pdAssistantMainWindow.setVisible(true);
        
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
                
            }
            
        });
        
    }
    
    private void initializeTablesData() {
        pdAssistantMainWindow.tableSecondaryAbilities.setModel(secondaryAbilitiesTableModel);
        
        secondaryAbilitiesTableModel.refreshData(false, null);
    }
    
    
    
}
