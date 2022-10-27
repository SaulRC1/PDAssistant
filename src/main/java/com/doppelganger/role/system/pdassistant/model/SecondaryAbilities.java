package com.doppelganger.role.system.pdassistant.model;

import com.doppelganger.role.system.pdassistant.util.services.AnimaExcelReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author SaulRC1
 */
public class SecondaryAbilities {

    public static final int SECONDARY_ABILITIES_SHEET_INDEX = 3;
    
    public static final int SECONDARY_ABILITIES_CELL_OFFSET = 4;

    private HashMap<String, Integer> secondaryAbilities;

    private final AnimaExcelReader animaExcelReader;
    
    public static final Set<String> secondaryAbilitiesNames = new HashSet<>();

    public static final HashMap<Integer, String> secondaryAbilitiesAssociatedCellIndexesVersion8_4_3
            = new HashMap<>();

    public static final HashMap<Integer, String> secondaryAbilitiesAssociatedCellIndexesVersion8_4_1
            = new HashMap<>();

    static {
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3();
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1();
        initializeSecondaryAbilitiesNames();
    }

    public SecondaryAbilities(String pathToFile) throws FileNotFoundException, IOException, InvalidFormatException {

        this.animaExcelReader = new AnimaExcelReader(pathToFile);

        this.secondaryAbilities = this.animaExcelReader.readSecondaryAbilitiesFromExcelGeneralAlgorithm();
    }

    public HashMap<String, Integer> getSecondaryAbilities() {
        return secondaryAbilities;
    }

    public void setSecondaryAbilities(HashMap<String, Integer> secondaryAbilities) {
        this.secondaryAbilities = secondaryAbilities;
    }

    public AnimaExcelReader getAnimaExcelReader() {
        return animaExcelReader;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, InvalidFormatException {
        /*SecondaryAbilities secondaryAbilities = new SecondaryAbilities("C:\\Users\\SaulWorkStation\\Downloads\\Andrea.xlsm");
        
        System.out.println("SecondaryAbilites");

        String version = secondaryAbilities.getAnimaExcelReader().getAnimaExcelVersion();

        System.out.println("Version " + version);

        HashMap<String, Integer> abilities = secondaryAbilities.getSecondaryAbilities();

        for (Map.Entry pair : abilities.entrySet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }*/
        
        for (String secondaryAbilitiesName : secondaryAbilitiesNames) {
            System.out.println("Habilidad Secundaria: " + secondaryAbilitiesName);
        }
    }
    
    
    private static void initializeSecondaryAbilitiesNames() {
        for (Map.Entry pair : secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.entrySet()) {
            secondaryAbilitiesNames.add((String) pair.getValue());
        }
    }

    /**
     * Initializes cell indexes of the secondary abilities inside the excel file
     */
    private static void secondaryAbilitiesAssociatedCellIndexesVersion8_4_1() {

        //Version 8.4.1
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(961, "Acrobacias");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1006, "Atletismo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1051, "Montar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1096, "Nadar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1141, "Trepar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1186, "Saltar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1231, "Pilotar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1271, "Estilo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1317, "Intimidar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1363, "Liderazgo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1409, "Persuasión");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1448, "Comercio");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1495, "Callejeo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1542, "Etiqueta");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1589, "Advertir");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1636, "Buscar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1683, "Rastrear");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1730, "Animales");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1777, "Ciencia");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1824, "Ley");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1871, "Herbolaria");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1918, "Historia");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(1965, "Táctica");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2012, "Medicina");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2059, "Memorizar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2105, "Navegación");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2152, "Ocultismo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2199, "Tasación");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2246, "V.Mágica");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2293, "Frialdad");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2340, "P. Fuerza");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2387, "Res.Dolor");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2434, "Cerrajería");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2473, "Disfraz");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2520, "Ocultarse");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2567, "Robo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2614, "Sigilo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2661, "Trampería");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2708, "Venenos");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2755, "Arte");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2802, "Baile");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2849, "Forja");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2896, "Runas");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2943, "Alquimia");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(2982, "Animismo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(3028, "Música");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(3074, "T. Manos");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(3120, "Caligrafía ritual");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(3166, "Orfebrería");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(3212, "Confección");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_1.put(3259, "Conf.Marionetas");

    }

    /**
     * Initializes cell indexes of the secondary abilities inside the excel file
     */
    private static void secondaryAbilitiesAssociatedCellIndexesVersion8_4_3() {

        //Version 8.4.3
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1004, "Acrobacias");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1051, "Atletismo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1098, "Montar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1146, "Nadar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1194, "Trepar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1242, "Saltar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1289, "Pilotar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1331, "Estilo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1379, "Intimidar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1427, "Liderazgo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1475, "Persuasión");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1516, "Comercio");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1565, "Callejeo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1614, "Etiqueta");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1663, "Advertir");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1712, "Buscar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1761, "Rastrear");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1810, "Animales");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1859, "Ciencia");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1908, "Ley");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(1957, "Herbolaria");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2006, "Historia");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2055, "Táctica");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2104, "Medicina");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2153, "Memorizar");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2201, "Navegación");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2250, "Ocultismo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2299, "Tasación");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2348, "V. Mágica");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2397, "Frialdad");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2446, "P. Fuerza");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2495, "Res. Dolor");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2544, "Cerrajería");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2585, "Disfraz");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2634, "Ocultarse");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2683, "Robo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2732, "Sigilo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2781, "Trampería");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2830, "Venenos");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2879, "Arte");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2928, "Baile");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(2977, "Forja");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3026, "Runas");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3075, "Alquimia");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3116, "Animismo");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3164, "Música");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3212, "T. Manos");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3260, "Caligrafía ritual");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3308, "Orfebrería");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3356, "Confección");
        secondaryAbilitiesAssociatedCellIndexesVersion8_4_3.put(3405, "Conf. marionetas");
    }

}
