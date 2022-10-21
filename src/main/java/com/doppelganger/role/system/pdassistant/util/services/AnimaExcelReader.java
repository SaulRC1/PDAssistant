package com.doppelganger.role.system.pdassistant.util.services;

import com.doppelganger.role.system.pdassistant.model.SecondaryAbilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author SaulRC1
 */
public class AnimaExcelReader {

    private String pathToFile;

    private XSSFWorkbook excelFile;

    private String animaExcelVersion;

    public static final int ANIMA_VERSION_CELL_INDEX = 36;

    public static final String ANIMA_VERSION_8_4_1 = "8.4.1";

    public static final String ANIMA_VERSION_8_4_3 = "8.4.3";

    public static final int ANIMA_VERSION_ROW_INDEX = 104;

    public static final int ANIMA_VERSION_SHEET = 1;

    public AnimaExcelReader(String pathToFile) throws FileNotFoundException, IOException, InvalidFormatException {

        this.pathToFile = pathToFile;

        retrieveExcelFile();

        this.animaExcelVersion = this.retrieveAnimaExcelVersion();

    }

    public String getAnimaExcelVersion() {
        return animaExcelVersion;
    }

    private void retrieveExcelFile() throws FileNotFoundException, IOException, InvalidFormatException {
        File file = new File(pathToFile);

        //FileInputStream fileInputStream = new FileInputStream(file);
        this.excelFile = new XSSFWorkbook(file);
    }

    private String retrieveAnimaExcelVersion() {
        //Get the Excel Sheet where secondary abilities are located
        XSSFSheet sheet = excelFile.getSheetAt(ANIMA_VERSION_SHEET);

        String versionString = sheet.getRow(ANIMA_VERSION_ROW_INDEX).getCell(ANIMA_VERSION_CELL_INDEX).toString();

        String versionNumber = versionString.split(" ")[3];

        return versionNumber;
    }

    /**
     * Retrieves all secondary abilities from Anima Excel File
     *
     * @return A HashMap containing the secondary abilities and its values
     */
    public HashMap<String, Integer> readSecondaryAbilitiesFromExcelGeneralAlgorithm() {

        //Create the HashMap that will be returned
        HashMap<String, Integer> secondaryAbilities = new HashMap<>();

        //Current cell index
        int cellIndex = 0;

        //Get the Excel Sheet where secondary abilities are located
        XSSFSheet sheet = excelFile.getSheetAt(SecondaryAbilities.SECONDARY_ABILITIES_SHEET_INDEX);

        //Get an iterator for each row inside the excel sheet
        Iterator<Row> itr = sheet.iterator();

        //Iterate over all rows
        while (itr.hasNext()) {

            //Get next row
            Row row = itr.next();

            //Get an iterator for every cell inside this row
            Iterator<Cell> cellIterator = row.cellIterator();

            //Iterate over all cells
            while (cellIterator.hasNext()) {

                //Get next cell
                Cell cell = cellIterator.next();

                if (SecondaryAbilities.secondaryAbilitiesNames.contains(cell.toString())) {
                    
                    System.out.println("Celda: [" + cell.getColumnIndex() + "]: " + cell.toString());

                    Cell valueCell = row.getCell(cell.getColumnIndex() + SecondaryAbilities.SECONDARY_ABILITIES_CELL_OFFSET);

                    //Secondary Abilities are computed by a formula hence its type
                    if (valueCell != null && valueCell.getCellType() == CellType.FORMULA) {

                        //Get which type is the formula computed value
                        CellType type = valueCell.getCachedFormulaResultType();
                        
                        System.out.println("Celda Valor: " + valueCell.toString());

                        //If the computed formula type is numeric, and the index is
                        //corresponds to a secondary ability, then store it
                        if (type == CellType.NUMERIC) {
                            
                            ArrayList<String> secondaryAbilitiesNamesList 
                                    = new ArrayList<>(SecondaryAbilities.secondaryAbilitiesNames);
                            
                            int indexOfSecondaryAbilityName 
                                    = secondaryAbilitiesNamesList.indexOf(cell.toString());
                            
                            String secondaryAbilityName = secondaryAbilitiesNamesList.get(indexOfSecondaryAbilityName);

                            //Put the secondary ability and its associated value in 
                            //the returned Hash Map
                            secondaryAbilities.put(secondaryAbilityName, (int) valueCell.getNumericCellValue());
                        }

                    }

                }

                cellIndex++;
            }

        }

        return secondaryAbilities;
    }

    /**
     * Retrieves all secondary abilities from Anima Excel File
     *
     * @return A HashMap containing the secondary abilities and its values
     */
    @Deprecated
    public HashMap<String, Integer> readSecondaryAbilitiesFromExcel() {

        //Create the HashMap that will be returned
        HashMap<String, Integer> secondaryAbilities = new HashMap<>();

        HashMap<Integer, String> secondaryAbilitiesAssociatedCellIndexes = null;

        if (animaExcelVersion.equals(ANIMA_VERSION_8_4_3)) {

            secondaryAbilitiesAssociatedCellIndexes = SecondaryAbilities.secondaryAbilitiesAssociatedCellIndexesVersion8_4_3;

        } else if (animaExcelVersion.equals(ANIMA_VERSION_8_4_1)) {

            secondaryAbilitiesAssociatedCellIndexes = SecondaryAbilities.secondaryAbilitiesAssociatedCellIndexesVersion8_4_1;

        }

        //Current cell index
        int cellIndex = 0;

        //Get the Excel Sheet where secondary abilities are located
        XSSFSheet sheet = excelFile.getSheetAt(SecondaryAbilities.SECONDARY_ABILITIES_SHEET_INDEX);

        //Get an iterator for each row inside the excel sheet
        Iterator<Row> itr = sheet.iterator();

        //Iterate over all rows
        while (itr.hasNext()) {

            //Get next row
            Row row = itr.next();

            //Get an iterator for every cell inside this row
            Iterator<Cell> cellIterator = row.cellIterator();

            //Iterate over all cells
            while (cellIterator.hasNext()) {

                //Get next cell
                Cell cell = cellIterator.next();

                System.out.println("Celda [" + cellIndex + "]: " + cell.toString());

                //Secondary Abilities are computed by a formula hence its type
                if (cell.getCellType() == CellType.FORMULA) {

                    //Get which type is the formula computed value
                    CellType type = cell.getCachedFormulaResultType();

                    //If the computed formula type is numeric, and the index is
                    //corresponds to a secondary ability, then store it
                    if (type == CellType.NUMERIC
                            && secondaryAbilitiesAssociatedCellIndexes.containsKey(cellIndex)) {

                        //Obtain secondary ability name
                        String secondaryAbilityName = secondaryAbilitiesAssociatedCellIndexes.get(cellIndex);

                        //Put the secondary ability and its associated value in 
                        //the returned Hash Map
                        secondaryAbilities.put(secondaryAbilityName, (int) cell.getNumericCellValue());
                    }

                }

                cellIndex++;
            }

        }

        return secondaryAbilities;
    }
}
