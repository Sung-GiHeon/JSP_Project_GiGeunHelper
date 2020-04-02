package vo;

public class SearchVO {
	private String searchName;
	private String searchValue;
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	@Override
	public String toString() {
		return "SearchVO [searchName=" + searchName + ", searchValue=" + searchValue + "]";
	}
	
}
