package org.anson.demo.mapper.sys;

import java.util.List;
import org.anson.demo.pojo.po.sys.SysUserPo;
import org.anson.demo.pojo.Example;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户mapper
 */
@Repository   // 加这个 @Repository 注解, 只是单纯不想让 idea 报 不能注入 mapper的错
@Mapper
public interface SysUserMapper {
    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserPo record);

    int insertSelective(SysUserPo record);

    List<SysUserPo> selectByExample(Example example);

    SysUserPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserPo record, @Param("example") Example example);

    int updateByExample(@Param("record") SysUserPo record, @Param("example") Example example);

    int updateByPrimaryKeySelective(SysUserPo record);

    int updateByPrimaryKey(SysUserPo record);
}
