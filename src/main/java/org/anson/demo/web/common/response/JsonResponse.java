package org.anson.demo.web.common.response;

import lombok.Data;

/**
 * 响应格式
 */
@Data
public class JsonResponse {
    /**
     * 状态码
     */
    private String code;

    /**
     * 数据
     */
    private Object data;

    /**
     * 信息
     */
    private String msg;

    /**
     * 成功
     * @return
     */
    public static JsonResponse ok(){
        return new JsonResponse(ResponseCodeEnum.OK, null, null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static JsonResponse ok(Object data){
        return new JsonResponse(ResponseCodeEnum.OK, data, null);
    }

    /**
     * 客户端错误
     * @param msg
     * @return
     */
    public static JsonResponse clientErr(String msg){
        return new JsonResponse(ResponseCodeEnum.CLIENT_ERROR, null, msg);
    }

    /**
     * 服务端错误
     * @param msg
     * @return
     */
    public static JsonResponse serverErr(String msg){
        return new JsonResponse(ResponseCodeEnum.SERVER_ERROR, null, msg);
    }

    private JsonResponse(ResponseCodeEnum code, Object data, String msg){
        this.code = code.code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 状态代码 - 枚举
     */
    private enum ResponseCodeEnum {

        /**
         * 200 ok
         */
        OK("200"),

        /**
         * 404 not found
         */
        NOT_FOUND("404"),

        /**
         * 无权限访问接口
         */
        UNAUTHORIZED("430"),

        /**
         * token已过期
         */
        TOKEN_EXPIRED("451"),

        /**
         * refreshtoken已过期
         */
        REFRESH_TOKEN_EXPIRED("452"),

        /**
         * token未过期
         */
        TOKEN_UNEXPIRED("453"),

        /**
         * 客户端错误
         */
        CLIENT_ERROR("499"),

        /**
         * 服务器错误
         */
        SERVER_ERROR("599");

        private String code;

        private ResponseCodeEnum(String code){
            this.code = code;
        }

    }
}
