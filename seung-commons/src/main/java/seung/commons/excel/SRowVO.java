package seung.commons.excel;

import java.util.ArrayList;

public class SRowVO {

	private int                rowNum = -1;
	private ArrayList<SCellVO> sCellList;
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public ArrayList<SCellVO> getSCellList() {
		return sCellList;
	}
	public void setSCellList(ArrayList<SCellVO> sCellList) {
		this.sCellList = sCellList;
	}
	
}
