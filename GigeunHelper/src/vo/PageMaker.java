package vo;

public class PageMaker {
	
	private int totalCount;			// 전체 게시물의 개수
	private int startPage;			// 게시판화면에 보여질 블럭의 시작페이지 번호
	private int endPage;			// 게시판화면에 보여질 블럭의 마지막페이지 번호
	private boolean prev;			// 이전페이지 존재 여부
	private boolean next;			// 다음페이지 존재 여부
	private int displayPageNum =10;	// 한번에 보여줄 블럭의 수
	private int maxPage;			// 전체 게시물의 페이지 수
	
	Criteria cri;
	
	public void calcPaging() {
		endPage = (int)Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum;
		startPage = (endPage-displayPageNum)+1;
		
		maxPage = (int)Math.ceil((totalCount/(double)cri.getPerPageNum()));
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// prev = startPage == 1 ? false : true;
		prev = (endPage-displayPageNum) <= 0 ? false : true;
		
		next = (endPage == maxPage) ? false : true;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPaging();
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

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
		calcPaging();
	}
	
	/*
	 * public SearchCriteria getSearch() { if(this.cri instanceof SearchCriteria) {
	 * return (SearchCriteria)this.cri; } return null; }
	 * 
	 * public String make(int page) { StringBuilder sb = new StringBuilder();
	 * sb.append("?"); sb.append("&page="+page);
	 * sb.append("&searchName="+getSearch().getSearchName());
	 * sb.append("&searchValue="+getSearch().getSearchValue()); return
	 * sb.toString(); }
	 */
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", maxPage=" + maxPage + ", cri="
				+ cri + "]";
	}
	
	
}
