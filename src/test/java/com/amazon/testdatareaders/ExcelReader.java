package com.amazon.testdatareaders;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private String filePath;

	public ExcelReader(String filePath) {
		this.filePath = filePath;
	}

	public List<String> getFieldNameFromExcel(String sheetName) {

		List<String> fileNames = new ArrayList<>();
		try {
			FileInputStream fs = new FileInputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sheet = wb.getSheet(sheetName);
			int rows = sheet.getPhysicalNumberOfRows();
			for(int row=1;row<rows;row++) {
				fileNames.add(sheet.getRow(row).getCell(0).getStringCellValue());
			}	
			wb.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileNames;
	}
}
