/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doppelganger.role.system.pdassistant.model;

import com.doppelganger.role.system.pdassistant.util.services.AnimaExcelReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author SaulRC1
 */
public class SecondaryAbilities {

    public static final int SECONDARY_ABILITIES_SHEET_INDEX = 3;

    private HashMap<String, Integer> secondaryAbilities;

    private final AnimaExcelReader animaExcelReader;

    public static final HashMap<Integer, String> secondaryAbilitiesAssociatedCellIndexes
            = new HashMap<>();

    static {
        initializeSecondaryAbilitiesAssociatedCellIndexes();
    }

    public SecondaryAbilities() {

        this.animaExcelReader = new AnimaExcelReader("C:\\Users\\SaulRC1\\Desktop\\Roll\\Anima\\Partida 6\\Sigma.xlsm");

        this.secondaryAbilities = this.animaExcelReader.readSecondaryAbilitiesFromExcel();
    }

    public HashMap<String, Integer> getSecondaryAbilities() {
        return secondaryAbilities;
    }

    public void setSecondaryAbilities(HashMap<String, Integer> secondaryAbilities) {
        this.secondaryAbilities = secondaryAbilities;
    }

    public static void main(String[] args) {
        SecondaryAbilities secondaryAbilities = new SecondaryAbilities();

        HashMap<String, Integer> abilities = secondaryAbilities.getSecondaryAbilities();

        for (Map.Entry pair : abilities.entrySet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }

    }

    /**
     * Initializes cell indexes of the secondary abilities inside the excel file
     */
    private static void initializeSecondaryAbilitiesAssociatedCellIndexes() {

        secondaryAbilitiesAssociatedCellIndexes.put(1004, "Acrobacias");
        secondaryAbilitiesAssociatedCellIndexes.put(1051, "Atletismo");
        secondaryAbilitiesAssociatedCellIndexes.put(1098, "Montar");
        secondaryAbilitiesAssociatedCellIndexes.put(1146, "Nadar");
        secondaryAbilitiesAssociatedCellIndexes.put(1194, "Trepar");
        secondaryAbilitiesAssociatedCellIndexes.put(1242, "Saltar");
        secondaryAbilitiesAssociatedCellIndexes.put(1289, "Pilotar");
        secondaryAbilitiesAssociatedCellIndexes.put(1331, "Estilo");
        secondaryAbilitiesAssociatedCellIndexes.put(1379, "Intimidar");
        secondaryAbilitiesAssociatedCellIndexes.put(1427, "Liderazgo");
        secondaryAbilitiesAssociatedCellIndexes.put(1475, "Persuasión");
        secondaryAbilitiesAssociatedCellIndexes.put(1516, "Comercio");
        secondaryAbilitiesAssociatedCellIndexes.put(1565, "Callejeo");
        secondaryAbilitiesAssociatedCellIndexes.put(1614, "Etiqueta");
        secondaryAbilitiesAssociatedCellIndexes.put(1663, "Advertir");
        secondaryAbilitiesAssociatedCellIndexes.put(1712, "Buscar");
        secondaryAbilitiesAssociatedCellIndexes.put(1761, "Rastrear");
        secondaryAbilitiesAssociatedCellIndexes.put(1810, "Animales");
        secondaryAbilitiesAssociatedCellIndexes.put(1859, "Ciencia");
        secondaryAbilitiesAssociatedCellIndexes.put(1908, "Ley");
        secondaryAbilitiesAssociatedCellIndexes.put(1957, "Herbolaria");
        secondaryAbilitiesAssociatedCellIndexes.put(2006, "Historia");
        secondaryAbilitiesAssociatedCellIndexes.put(2055, "Táctica");
        secondaryAbilitiesAssociatedCellIndexes.put(2104, "Medicina");
        secondaryAbilitiesAssociatedCellIndexes.put(2153, "Memorizar");
        secondaryAbilitiesAssociatedCellIndexes.put(2201, "Navegación");
        secondaryAbilitiesAssociatedCellIndexes.put(2250, "Ocultismo");
        secondaryAbilitiesAssociatedCellIndexes.put(2299, "Tasación");
        secondaryAbilitiesAssociatedCellIndexes.put(2348, "V.Mágica");
        secondaryAbilitiesAssociatedCellIndexes.put(2397, "Frialdad");
        secondaryAbilitiesAssociatedCellIndexes.put(2446, "P.Fuerza");
        secondaryAbilitiesAssociatedCellIndexes.put(2495, "Res.Dolor");
        secondaryAbilitiesAssociatedCellIndexes.put(2544, "Cerrajería");
        secondaryAbilitiesAssociatedCellIndexes.put(2585, "Disfraz");
        secondaryAbilitiesAssociatedCellIndexes.put(2634, "Ocultarse");
        secondaryAbilitiesAssociatedCellIndexes.put(2683, "Robo");
        secondaryAbilitiesAssociatedCellIndexes.put(2732, "Sigilo");
        secondaryAbilitiesAssociatedCellIndexes.put(2781, "Trampería");
        secondaryAbilitiesAssociatedCellIndexes.put(2830, "Venenos");
        secondaryAbilitiesAssociatedCellIndexes.put(2879, "Arte");
        secondaryAbilitiesAssociatedCellIndexes.put(2928, "Baile");
        secondaryAbilitiesAssociatedCellIndexes.put(2977, "Forja");
        secondaryAbilitiesAssociatedCellIndexes.put(3026, "Runas");
        secondaryAbilitiesAssociatedCellIndexes.put(3075, "Alquimia");
        secondaryAbilitiesAssociatedCellIndexes.put(3116, "Animismo");
        secondaryAbilitiesAssociatedCellIndexes.put(3164, "Música");
        secondaryAbilitiesAssociatedCellIndexes.put(3212, "T.Manos");
        secondaryAbilitiesAssociatedCellIndexes.put(3260, "Caligrafía Ritual");
        secondaryAbilitiesAssociatedCellIndexes.put(3308, "Orfebrería");
        secondaryAbilitiesAssociatedCellIndexes.put(3356, "Confección");
        secondaryAbilitiesAssociatedCellIndexes.put(3405, "Conf.Marionetas");
    }

}
