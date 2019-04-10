package seung.commons.excel;

import java.util.ArrayList;

public class SExcelVO {

	private int                 result           = 0;
	private String              message          = "";
	
	private int                 numberOfSheets   = -1;
	private String              exceptionMessage = "";
	private ArrayList<SSheetVO> sSheetList;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNumberOfSheets() {
		return numberOfSheets;
	}
	public void setNumberOfSheets(int numberOfSheets) {
		this.numberOfSheets = numberOfSheets;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public ArrayList<SSheetVO> getSSheetList() {
		return sSheetList;
	}
	public void setSSheetList(ArrayList<SSheetVO> sSheetList) {
		this.sSheetList = sSheetList;
	}
	
}
