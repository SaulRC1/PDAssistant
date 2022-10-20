/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doppelganger.role.system.pdassistant.model.table;

import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author SaulRC1
 */
public class SecondaryAbilitiesTableModel extends DefaultTableModel {

    private HashMap<String, Integer> secondaryAbilities;
    
    public SecondaryAbilitiesTableModel(HashMap<String, Integer> secondaryAbilities) {
        this.addColumn("Habilidad");
        this.addColumn("Valor");
        this.addColumn("Resultado De La Tirada");
        
        this.secondaryAbilities = secondaryAbilities;
    }
    
    public void refreshData(boolean calculateValues, Integer result) {
        
        //Discards all current rows
        this.setRowCount(0);
        
        for (Map.Entry pair : secondaryAbilities.entrySet()) {
            
            String[] row;
            
            if(calculateValues && result != null) {
                Integer computedValue = (Integer) pair.getValue() + result;
                
                row = initializeRow((String) pair.getKey(), (Integer) pair.getValue(),
                                         computedValue);
            } else {
                row = initializeRow((String) pair.getKey(), (Integer) pair.getValue(),null);
            }
            
            this.addRow(row);
        }
        
        
    }
    
    private String[] initializeRow(String ability, Integer abilityValue, Integer computedValue) {
        
        String[] row = new String[3];
        
        row[0] = ability;
        
        row[1] = abilityValue.toString();
        
        if(computedValue == null) {
            
            row[2] = "";
            
        } else {
            
            row[2] = computedValue.toString();
            
        }
        
        
        return row;
        
    }
    
}
