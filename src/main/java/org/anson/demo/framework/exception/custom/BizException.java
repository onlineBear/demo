package org.anson.demo.framework.exception.custom;

import lombok.Data;

/**
 * 业务异常
 */
@Data
public class BizException extends RuntimeException{
    private String msg;

    public BizException(String msg){
        this.msg = msg;
    }

    public BizException(){
    }
}
