package rj.utils;

import java.util.List;

/**
 * 分页的工具类
 * 封装了一些需要的数据
 * Created by 隽 on 2016/11/23.
 */
public class PageBean <T>{
    //当前页
    private int page;
    //总页数
    private int totalPage;
    //一页显示多少个数据
    private int limit;
    //总数据数
    private int total;
    //代表数据的集合
    private List<T> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
