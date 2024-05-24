package cn.moexc.vcs.service.vo;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageResultFactory<T> {

    private final List<T> list;

    public PageResultFactory(List<T> list) {
        this.list = list;
    }

    public PageResult<T> of(){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        PageResult<T> result = new PageResult<>();
        result.setTotal(pageInfo.getTotal());
        result.setData(list);
        return result;
    }
}
