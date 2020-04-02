package vo;

// 페이징 처리 기준
public class Criteria {

	private int page;			// 현재 페이지 정보
	private int perPageNum;		// 한 페이지에 보여줄 게시글의 수
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int page, int perPageNum) {
		setPage(page);
		setPerPageNum(perPageNum);
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <=0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum>100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() {
		return (this.page-1)*perPageNum;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
}
