package jxls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Issue180Test {

    public static void main(String[] args) throws Exception {
        // Prepare
        String src = "test_template.xlsx";
        String work = "target/test_work.xlsx";
        new File("target").mkdir();
        File out = new File("target/test_OUTPUT.xlsx");
        Files.copy(Paths.get(src), Paths.get(work));
        
        // Test
        try (Workbook workbook = WorkbookFactory.create(new File(work))) {
            Sheet sheet = workbook.getSheetAt(0);
            Cell cell = sheet.getRow(5 - 1).getCell(0);
            
            cell.setCellFormula("SUM(A2:A4)");

// This simulates the fix.            
//            CTCell _cell = ((XSSFCell) cell).getCTCell();
//            if (_cell.isSetV()) {
//                _cell.unsetV();
//            }

            //workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            //workbook.setForceFormulaRecalculation(true);
            try (OutputStream os = new FileOutputStream(out)) {
                workbook.write(os);
            }
        }
        
        // Cleanup
        new File(work).delete();
    }
}
