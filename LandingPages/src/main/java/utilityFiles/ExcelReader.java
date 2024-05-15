package utilityFiles;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	@SuppressWarnings("resource")
	public static Object[][] readData(String sheetName, String columnName) throws IOException, Exception {
        FileInputStream fileInputStream = new FileInputStream("D:\\003_Automation\\Landing Pages\\006_Script\\LandingPages\\src\\main\\java\\files\\Validations.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][1];

        int columnIndex = -1;
        for (int i = 0; i < colCount; i++) {
            if (sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex == -1) {
            throw new IllegalArgumentException("Column with name '" + columnName + "' not found in the sheet.");
        }

        for (int i = 1; i <= rowCount; i++) {
            Cell cell = sheet.getRow(i).getCell(columnIndex);
            if (cell != null) {
                data[i - 1][0] = cellToString(cell);
            } else {
                data[i - 1][0] = ""; 
            }
            
        }

        workbook.close();
        fileInputStream.close();

        return data;
    }

    public static String cellToString(Cell cell) {
    	
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            return "";
        }
    }

}
