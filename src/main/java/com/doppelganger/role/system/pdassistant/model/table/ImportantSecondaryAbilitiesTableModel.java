/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doppelganger.role.system.pdassistant.model.table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SaulRC1
 */
public class ImportantSecondaryAbilitiesTableModel extends DefaultTableModel {

    private HashMap<String, Integer> secondaryAbilities;

    private static final Set<String> importantSecondaryAbilities = new HashSet<>();

    static {
        initializeImportantSecondaryAbilities();
    }

    public ImportantSecondaryAbilitiesTableModel(HashMap<String, Integer> secondaryAbilities) {
        this.addColumn("Habilidad");
        this.addColumn("Valor");
        this.addColumn("Resultado De La Tirada");

        this.secondaryAbilities = secondaryAbilities;
    }

    private static void initializeImportantSecondaryAbilities() {

        importantSecondaryAbilities.add("Advertir");
        importantSecondaryAbilities.add("Buscar");
        importantSecondaryAbilities.add("Rastrear");
        importantSecondaryAbilities.add("V.Mágica");
        importantSecondaryAbilities.add("Sigilo");
        importantSecondaryAbilities.add("Ocultismo");
        importantSecondaryAbilities.add("Persuasión");
        importantSecondaryAbilities.add("Intimidación");

    }

    public void refreshData(boolean calculateValues, Integer result) {
        //Discards all current rows
        this.setRowCount(0);

        for (Map.Entry pair : secondaryAbilities.entrySet()) {

            String[] row;

            String key = (String) pair.getKey();

            if (importantSecondaryAbilities.contains(key)) {
                if (calculateValues && result != null) {
                    Integer computedValue = (Integer) pair.getValue() + result;

                    row = initializeRow((String) pair.getKey(), (Integer) pair.getValue(),
                            computedValue);
                } else {
                    row = initializeRow((String) pair.getKey(), (Integer) pair.getValue(), null);
                }

                this.addRow(row);
            }
        }
    }

    private String[] initializeRow(String ability, Integer abilityValue, Integer computedValue) {

        String[] row = new String[3];

        row[0] = ability;

        row[1] = abilityValue.toString();

        if (computedValue == null) {

            row[2] = "";

        } else {

            row[2] = computedValue.toString();

        }

        return row;

    }

}
