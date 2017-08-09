package SpreadSheets;

import org.junit.Test;

import java.util.List;

public class SpreadSheetExample {

    @Test
    public void spreadSheet(){
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/Workbook1.xlsx");
        List<String> row = sheetReader.readRow(1, "theSheet");

        for(String cell : row){
            System.out.println(cell);
        }


    }


}
