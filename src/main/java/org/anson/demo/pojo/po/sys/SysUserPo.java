package org.anson.demo.pojo.po.sys;

import lombok.Data;
import org.anson.demo.pojo.po.BasePo;

/**
 * 用户 po
 */
@Data
public class SysUserPo extends BasePo {
    /**
     * 用户编码
     */
    private String no;
    /**
     * 用户编码列 - no
     */
    public static final String NO = "no";

    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户名称列 - name
     */
    public static final String NAME = "name";

    /**
     * 密码
     */
    private String password;
    /**
     * 密码列 - password
     */
    public static final String PASSWORD = "password";

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 手机号列 - mobile
     */
    public static final String MOBILE = "mobile";
}
