package com.hsr.yxw.common;

import java.util.List;

/**
 * <p>分页工具类</p>
 * @author 胡绍瑞 [hsr74108520@foxmail.com]
 * @param <T>
 */
public class PageBean<T> {
    /**
     * 从数据库中查询的数据
     */
    private List<T> pageList;
    /**
     * 数据库中总记录条数
     */
    private Integer totalCount;
    /**
     * 每页显示记录条数
     */
    private Integer pageSize;
    /**
     * 每页起始记录数（不包含该条）
     */
    private Integer firstResult;

    public Integer getFirstResult() {
        return firstResult;
    }
    /**
     * 当前页数
     */
    private Integer currentPage;
    /**
     * 前一页
     */
    private Integer prePage;
    public Integer getPrePage() {
        return prePage;
    }
    /**
     * 后一页
     */
    private Integer nextPage;
    public Integer getNextPage() {
        return nextPage;
    }
    /**
     * 总页数
     */
    private Integer totalPage;

    public PageBean(Integer totalCount, Integer pageSize, Integer currentPage) {
        this.totalCount = totalCount;//从数据库中查询的总记录数
        this.pageSize = pageSize;//每页的记录数
        this.currentPage = currentPage;//请求页码

        if (this.pageSize == null) {
            //默认每页显示10条记录
            this.pageSize = 10;
        }

        //计算总页数
        this.totalPage = (this.totalCount + this.pageSize - 1) /this.pageSize;

        //当前页码
        if (this.currentPage == null) {
            //默认显示第一页
            this.currentPage = 1;
        } else if (currentPage < 1) {
            this.currentPage = 1;
        } else if (currentPage > this.totalPage) {
            this.currentPage = totalPage;
        }
        //计算firstResult，MySQL查询limit的第一个参数
        this.firstResult = (this.currentPage - 1) * this.pageSize;
        if (this.firstResult < 0) {
            this.firstResult = 0;
        }

        //设置前一页
        if (this.currentPage - 1 <= 0) {
            this.prePage = 1;
        } else {
            this.prePage = this.currentPage - 1;
        }
        //设置后一页
        if (this.currentPage >= this.totalPage) {
            this.nextPage = this.totalPage;
        } else {
            this.nextPage = this.currentPage + 1;
        }

    }

    public List<T> getPageList() {
        return pageList;
    }
    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
