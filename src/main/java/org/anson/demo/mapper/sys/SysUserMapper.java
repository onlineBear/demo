package org.anson.demo.mapper.sys;

import java.util.List;

import org.anson.demo.javabean.biz.sys.sysUser.SysUser;
import org.anson.demo.javabean.framework.query.Example;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户mapper
 */
@Repository
@Mapper
public interface SysUserMapper {

    /**
     * 根据 用户编码 查找用户
     * @param no
     * @return
     */
    public SysUser selByNo(@Param(("no")) String no);

    // 通用
    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(Example example);

    SysUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") Example example);

    int updateByExample(@Param("record") SysUser record, @Param("example") Example example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}
