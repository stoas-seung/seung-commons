package seung.commons;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;

import seung.commons.arguments.SMap;
import seung.commons.excel.SCellVO;
import seung.commons.excel.SExcelVO;
import seung.commons.excel.SRowVO;
import seung.commons.excel.SSheetVO;

public class SExcelU {

	/**
	 * @param src
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static SExcelVO readXls(String path) throws FileNotFoundException, IOException {
		return readXls(new File(path));
	}
	public static SExcelVO readXls(File file) throws FileNotFoundException, IOException {
		return readXls(IOUtils.toByteArray(new FileInputStream(file)));
	}
	public static SExcelVO readXls(byte[] src) {
		
		SExcelVO            sExcelVO   = new SExcelVO();
		
		ArrayList<SSheetVO> sSheetList = null;
		SSheetVO            sSheetVO   = null;
		ArrayList<SRowVO>   sRowList   = null;
		SRowVO              sRowVO     = null;
		ArrayList<SCellVO>  sCellList  = null;
		SCellVO             sCellVO    = null;
		
		Workbook workbook  = null;
		SMap     condition = null;
		
		try {
			
			sSheetList = new ArrayList<SSheetVO>();
			
			workbook = WorkbookFactory.create(new ByteArrayInputStream(src));
			
			sExcelVO.setNumberOfSheets(workbook.getNumberOfSheets());
			
//			boolean isContinue = false;
			for(Sheet sheet : workbook) {
				
//				isContinue = false;
				condition = null;
				
				sSheetVO = new SSheetVO();
				sRowList = new ArrayList<SRowVO>();
				
				sSheetVO.setSheetName(sheet.getSheetName());
				sSheetVO.setPhysicalNumberOfRows(sheet.getPhysicalNumberOfRows());
				
//				if(sMap.containsKey("sheetConditions") && sMap.get("sheetConditions") != null && sMap.getListSMap("sheetConditions").size() > 0) {
//					isContinue = true;
//					for(SMap sheetCondition : sMap.getListSMap("sheetConditions")) {
//						if(sheetCondition.containsKey("sheetName") && sSheetVO.getSheetName().equals(sheetCondition.getString("sheetName"))) {
//							condition = new SMap();
//							condition.putAll(sheetCondition);
//							break;
//						}
//					}
//				}
				
//				if(condition != null) {
//					isContinue = true;
//					if(condition.containsKey("sheetName") && sSheetVO.getSheetName().equals(condition.getString("sheetName"))) {
//						isContinue = false;
//					}
//					if(isContinue) {
//						sSheetList.add(sSheetVO);
//						sMap.put("sheetList", sSheetList);
//						continue;
//					}
//				}
				
				for(Row row : sheet) {
					
					sRowVO = new SRowVO();
					sCellList = new ArrayList<SCellVO>();
					
					sRowVO.setRowNum(row.getRowNum());
					
					if(
						condition != null
						&& condition.containsKey("maxRowNum")
						&& row.getRowNum() > condition.getInt("maxRowNum")
						)
						break;
					
//					if(sMap.containsKey("sheetConditions") && sMap.get("sheetConditions") != null && sMap.getListSMap("sheetConditions").size() > 0) {
//						isContinue = true;
//						for(SMap sheetCondition : sMap.getListSMap("sheetConditions")) {
//							if(sheetCondition.containsKey("sheetName") && sSheetVO.getSheetName().equals(sheetCondition.getString("sheetName"))) {
//								isContinue = false;
//								break;
//							}
//						}
//						if(isContinue) continue;
//					}
					
					for(Cell cell : row) {
						
//						isContinue = false;
						
						sCellVO = new SCellVO();
						
						sCellVO.setRowIndex(cell.getRowIndex());
						sCellVO.setColumnIndex(cell.getColumnIndex());
						sCellVO.setCellValue(getCellValue(cell));
						
						if(
							condition != null
							&& condition.containsKey("maxCellNum")
							&& cell.getColumnIndex() > condition.getInt("maxCellNum")
							)
							break;
						
						sCellList.add(sCellVO);
					}// end of cell
					
					sRowVO.setSCellList(sCellList);
					sRowList.add(sRowVO);
					
				}// end of row
				
				sSheetVO.setSRowList(sRowList);
				sSheetList.add(sSheetVO);
			}// end of sheet
			
			sExcelVO.setSSheetList(sSheetList);
			sExcelVO.setResult(1);
			
		} catch (Exception e) {
			
			sExcelVO.setExceptionMessage("" + e);
			
		}
		
		return sExcelVO;
	}
	
	private static String getCellValue(Cell cell) {
		
		String cellValue = "";
		
		switch (cell.getCellTypeEnum()) {
			case BLANK:
				cellValue = "";
				break;
			case BOOLEAN:
				cellValue = "" + cell.getBooleanCellValue();
				break;
			case STRING:
				cellValue = "" + cell.getStringCellValue();
				break;
			case NUMERIC:
				if(DateUtil.isCellDateFormatted(cell)) cellValue = "" + cell.getDateCellValue();
				else                                   cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
				break;
			case FORMULA:
				cellValue = cell.getCellFormula();
				break;
			default:
				cellValue = null;
				break;
		}
		
		return cellValue;
	}
}
