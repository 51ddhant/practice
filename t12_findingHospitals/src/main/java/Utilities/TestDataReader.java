package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataReader {

	// To read test data from excel based on test name and return the 2d object
	// array of it
	public static Object[][] getTestData(String DataFileName, String testName) {

		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + DataFileName + ".xlsx");
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int rowNumber = 0;
		while (sheet.getRow(rowNumber) == null
				|| !String.valueOf(sheet.getRow(rowNumber).getCell(0)).equalsIgnoreCase(testName)) {
			rowNumber++;
		}

		int testRowNum = rowNumber + 2;
		int columnNumber = 0;
		while (sheet.getRow(testRowNum).getCell(columnNumber) != null) {
			columnNumber++;
		}

		Object[][] dataSet = new Object[1][columnNumber];
		for (int column = 0; column < columnNumber; column++) {
			dataSet[0][column] = String.valueOf(sheet.getRow(testRowNum).getCell(column));
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataSet;
	}
}