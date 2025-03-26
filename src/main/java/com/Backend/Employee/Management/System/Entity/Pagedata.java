package com.Backend.Employee.Management.System.Entity;

import java.util.List;

public class Pagedata {
    private int page;
    private int pagesize;

    public Pagedata(int page, int pagesize) {
        this.page = page;
        this.pagesize = pagesize;
    }

    public int getPage() {

        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
}
