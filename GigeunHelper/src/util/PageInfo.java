package util;

public class PageInfo {
	
	int page;		// 현재 페이지
	int maxPage; 	// 전체 페이지 개수
	int startPage;	// 현블럭 내부의 시작 페이지 번호
	int endPage;	// 현블럭 내부의 마지막 페이지 번호
	int listCount;	// 전체 페이지 개수
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", listCount=" + listCount + "]";
	}
}
