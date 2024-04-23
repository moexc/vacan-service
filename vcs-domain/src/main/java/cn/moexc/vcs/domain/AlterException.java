package cn.moexc.vcs.domain;

import lombok.Getter;

@Getter
public class AlterException extends RuntimeException{

    private final String message;

    public AlterException(String message) {
        this.message = message;
    }
}
