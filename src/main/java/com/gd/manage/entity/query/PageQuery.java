package com.gd.manage.entity.query;


public class PageQuery {
    private Integer page;
    private Integer size;

    public PageQuery() {
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageQuery)) {
            return false;
        } else {
            PageQuery other = (PageQuery)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$page = this.getPage();
                Object other$page = other.getPage();
                if (this$page == null) {
                    if (other$page != null) {
                        return false;
                    }
                } else if (!this$page.equals(other$page)) {
                    return false;
                }

                Object this$size = this.getSize();
                Object other$size = other.getSize();
                if (this$size == null) {
                    if (other$size != null) {
                        return false;
                    }
                } else if (!this$size.equals(other$size)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageQuery;
    }

//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        Object $page = this.getPage();
//        int result = result * 59 + ($page == null ? 43 : $page.hashCode());
//        Object $size = this.getSize();
//        result = result * 59 + ($size == null ? 43 : $size.hashCode());
//        return result;
//    }

    public String toString() {
        return "PageQuery(page=" + this.getPage() + ", size=" + this.getSize() + ")";
    }
}

