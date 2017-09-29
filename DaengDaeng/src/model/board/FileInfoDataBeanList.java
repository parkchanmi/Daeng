package model.board;

import java.util.ArrayList;
import java.util.List;

import model.dto.FileInfoDataBean;


public class FileInfoDataBeanList {
	private List<FileInfoDataBean> FileInfoDataBeanList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;

	public FileInfoDataBeanList() {
		this(new ArrayList<FileInfoDataBean>(), 0, 0, 0, 0);
	}

	public FileInfoDataBeanList(List<FileInfoDataBean> FileInfoDataBeanList, int requestPageNumber, int totalPageCount, int startRow,
			int endRow) {
		this.FileInfoDataBeanList = FileInfoDataBeanList; //비어있는 array가 저장되어있음
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public List<FileInfoDataBean> getPdsItemList() {
		return FileInfoDataBeanList;
	}

	public boolean isHasPdsItem() { // 글이 있는지 없는지 확인
		return !FileInfoDataBeanList.isEmpty(); // 글이 없으면 false
	}

	public int getRequestPage() {
		return requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

}