package com.huayu.ssm.bean;

import java.io.Serializable;

public class Pages implements Serializable {

    private Integer currentPage=1;//当前页
    private Integer pageCounts;//总页数
    private long tiaoCount;//总条数
    private Integer putCount=3;//每页放几个

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageCounts() {
        return pageCounts;
    }

    public void setPageCounts(Integer pageCounts) {
        this.pageCounts = pageCounts;
    }

    public long getTiaoCount() {
        return tiaoCount;
    }

    public void setTiaoCount(long tiaoCount) {
        this.tiaoCount = tiaoCount;
    }

    public Integer getPutCount() {
        return putCount;
    }

    public void setPutCount(Integer putCount) {
        this.putCount = putCount;
    }
}
