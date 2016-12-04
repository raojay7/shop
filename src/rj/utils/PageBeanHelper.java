package rj.utils;

import java.util.List;

/**
 * 使用这个工具，需要传入总记录数和传入的list（list在dao中拿到）
 * Created by 隽 on 2016/12/4.
 */
public class PageBeanHelper<T>{
    // 每页显示的数据集合:
    // 从哪开始:
    private int begin;
    //每行显示几个
    private int limit;
    //第几页
    private int page;

    //第一步
    public PageBeanHelper(int limit, int page) {
        this.limit = limit;
        this.page = page;
    }

    //第二步
    public void setBegin() {
        begin=(page-1)*limit;
    }

    //第三步
    //当前页面数，所有的记录数，每行显示多少,查询的对象数组
    //不支持多表查询
    public PageBean findByPageHelper(int totalCount,List<T> list){
        //创建pagebean对象
        PageBean<T> pageBean=new PageBean<T>();
        //设置当前页
        pageBean.setPage(page);

        //设置总数据数
        int total=totalCount;
        pageBean.setTotal(total);

        //设置一页显示多少

        pageBean.setLimit(limit);

        //设置总页数
        int totalPage=0;
        if(total % limit == 0){
            totalPage = total / limit;
        }else{
            totalPage = total / limit + 1;
        }
        pageBean.setTotalPage(totalPage);

        pageBean.setList(list);
        return pageBean;
    }

    public int getBegin() {
        return begin;
    }

    public int getLimit() {
        return limit;
    }
}
