package ru.mrmarvel.hellofigma.util;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class ExcelWriter {

    public HSSFWorkbook workbook;
//    private String nameTemplate = "Готовности по типу";
    public HashMap<Percentage, String> PercentageEnumToString = new HashMap<Percentage, String>(){{
        put(Percentage.FLOOR_ROUGH, "floor_rough");
        put(Percentage.FLOOR_FINISH, "finish_floor");
        put(Percentage.TOILET, "toilet");
        put(Percentage.RADIATOR, "radiator");
        put(Percentage.CEILING, "ceiling");
        put(Percentage.WALL_FINISH, "wall_finish");
        put(Percentage.WALL_ROUGH, "wall_rough");
    }};

    public ExcelWriter() {
        this.workbook = new HSSFWorkbook();
    }

    public void fillSheet(Percentage sheetName, int numFloor, int numSection, float percent) {
        numFloor++;
        // CHECK IF SHEET EXISTS
        String sheetN = PercentageEnumToString.get(sheetName);
        HSSFSheet currentSheet = this.workbook.getSheet(sheetN);
        if (currentSheet == null) {
            currentSheet = this.workbook.createSheet(sheetN);
            Row sheetNameRow = currentSheet.createRow(0);
            Cell sheetNameCell = sheetNameRow.createCell(0);
            sheetNameCell.setCellValue(sheetN);
        }
        // FILL CURRENT
        Row row = currentSheet.getRow(numFloor);
        if (row == null){
            row = currentSheet.createRow(numFloor);
        }
        Cell cell = row.createCell(numSection);
        cell.setCellValue(percent);
    }

    public void saveWorkbook(String filename) {
        try {
            File filePath = new File(Environment.getExternalStorageDirectory() + "/" +filename);
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(filePath);
            this.workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void createSheet() {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//
//        //Create a blank sheet
//        HSSFSheet sheet = workbook.createSheet("Employee Data");
//
//        //This data needs to be written (Object[])
//        Map<String, Object[]> data = new TreeMap<String, Object[]>();
//        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
//        data.put("2", new Object[]{1, "Amit", "Shukla"});
//        data.put("3", new Object[]{2, "Lokesh", "Gupta"});
//        data.put("4", new Object[]{3, "John", "Adwards"});
//        data.put("5", new Object[]{4, "Brian", "Schultz"});
//
//        //Iterate over data and write to sheet
//        Set<String> keyset = data.keySet();
//        int rownum = 0;
//        for (String key : keyset) {
//            Row row = sheet.createRow(rownum++);
//            Object[] objArr = data.get(key);
//            int cellnum = 0;
//            for (Object obj : objArr) {
//                Cell cell = row.createCell(cellnum++);
//                if (obj instanceof String)
//                    cell.setCellValue((String) obj);
//                else if (obj instanceof Integer)
//                    cell.setCellValue((Integer) obj);
//            }
//        }
//        try {
//            File filePath = new File(Environment.getExternalStorageDirectory() + "/Demo.xls");
//            //Write the workbook in file system
//            FileOutputStream out = new FileOutputStream(filePath);
//            workbook.write(out);
//            out.close();
//            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

