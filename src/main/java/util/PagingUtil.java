package util;

public class PagingUtil {
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private int pageBlock;
	
	public PagingUtil() {
	
	}

	public PagingUtil(int totalCount, int currentPage, int pageSize, int pageBlock) {
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageBlock = pageBlock;
	}

	public int getStartRow() { //DB쿼리에서 LIMIT 용 
		return (currentPage - 1) * pageSize;
	}
	public int getEndRow() {
		return Math.min(currentPage * pageSize, totalCount);
	}
	public int getTotalPage() { //총 페이지 수
		return (int)Math.ceil((double)totalCount / pageSize);
	}
	public int getStartPage() { //페이지 블록 계산
		return((currentPage - 1) / pageBlock) * pageBlock + 1;
	}
	public int getEndPage() { //페이지 블록 계산
		int endPage = getStartPage() + pageBlock - 1;
		return Math.min(endPage, getTotalPage());
	}
	public boolean hasPrev() {
		return getStartPage() > 1;
	}
	public boolean hasNext() {
		return getEndPage() < getTotalPage();
	}
}
