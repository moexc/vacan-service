package cn.moexc.vcs.service.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {
    private long total;
    private List<T> data;
}
