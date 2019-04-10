package seung.commons.excel;

import java.util.ArrayList;

public class SSheetVO {

	private String            sheetName            = "";
	private int               physicalNumberOfRows = -1;
	private ArrayList<SRowVO> sRowList;
	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public int getPhysicalNumberOfRows() {
		return physicalNumberOfRows;
	}
	public void setPhysicalNumberOfRows(int physicalNumberOfRows) {
		this.physicalNumberOfRows = physicalNumberOfRows;
	}
	public ArrayList<SRowVO> getSRowList() {
		return sRowList;
	}
	public void setSRowList(ArrayList<SRowVO> sRowList) {
		this.sRowList = sRowList;
	}
	
}
