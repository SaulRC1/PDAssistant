package com.doppelganger.role.system.pdassistant.util.services;

import com.doppelganger.role.system.pdassistant.model.SecondaryAbilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public AnimaExcelReader(String pathToFile) throws FileNotFoundException, IOException {

        this.pathToFile = pathToFile;

        retrieveExcelFile();

    }

    private void retrieveExcelFile() throws FileNotFoundException, IOException {
        File file = new File(pathToFile);

        FileInputStream fileInputStream = new FileInputStream(file);

        this.excelFile = new XSSFWorkbook(fileInputStream);
    }

    /**
     * Retrieves all secondary abilities from Anima Excel File
     *
     * @return A HashMap containing the secondary abilities and its values
     */
    public HashMap<String, Integer> readSecondaryAbilitiesFromExcel() {

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

                //Secondary Abilities are computed by a formula hence its type
                if (cell.getCellType() == CellType.FORMULA) {

                    //Get which type is the formula computed value
                    CellType type = cell.getCachedFormulaResultType();

                    //If the computed formula type is numeric, and the index is
                    //corresponds to a secondary ability, then store it
                    if (type == CellType.NUMERIC
                            && SecondaryAbilities.secondaryAbilitiesAssociatedCellIndexes.containsKey(cellIndex)) {

                        //Obtain secondary ability name
                        String secondaryAbilityName = SecondaryAbilities.secondaryAbilitiesAssociatedCellIndexes.get(cellIndex);

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
