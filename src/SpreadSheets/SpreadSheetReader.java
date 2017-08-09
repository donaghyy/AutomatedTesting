package SpreadSheets;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpreadSheetReader {

    private Workbook workbook;

    public SpreadSheetReader(String fileName){
        try {
            FileInputStream excelFile = new FileInputStream(new File(fileName));
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readRow(int rowNo, String sheetName){
        List<String> row = new ArrayList<String>();
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Row currentRow =   datatypeSheet.getRow(rowNo);
        for (Cell currentCell : currentRow) {
            switch (currentCell.getCellType()) {
                
            case Cell.CELL_TYPE_STRING:
                    row.add(currentCell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    row.add(String.valueOf(currentCell.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    row.add(String.valueOf(currentCell.getBooleanCellValue()));
                    break;
                case Cell.CELL_TYPE_BLANK:
                    row.add(currentCell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    System.out.println("Error in cell");
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    row.add(currentCell.getStringCellValue());
                    break;
                    
                    /*case _NONE:
                    System.out.println("No Value in cell");
                    break;*/
            }
        }
        return row;
    }



}
