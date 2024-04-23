package cn.moexc.vcs.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class R {
    private Integer code;
    private String msg;
    private Object data;

    public static R success(){
        return R.success(true);
    }

    public static R success(Object data){
        return new R(0, "success", data);
    }

    public static R error(String msg){
        return R.error(-1, msg);
    }

    public static R error(Integer code, String msg){
        return new R(code, msg, null);
    }
}
