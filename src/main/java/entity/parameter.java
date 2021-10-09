package entity;

public class parameter {

    private String pageNum;
    private String pageSize;
    private String unitName;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public parameter(String pageNum, String pageSize, String unitName) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.unitName = unitName;
    }

    public parameter() {
    }

    @Override
    public String toString() {
        return "parameter{" +
                "pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", unitName='" + unitName + '\'' +
                '}';
    }
}
