package org.anson.demo.dao.sys;

import java.math.BigDecimal;
import java.util.List;
import org.anson.demo.po.sys.Sysuser;
import org.anson.demo.po.sys.SysuserExample;
import org.apache.ibatis.annotations.Param;

public interface SysuserMapper {
    long countByExample(SysuserExample example);

    int deleteByExample(SysuserExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(Sysuser record);

    int insertSelective(Sysuser record);

    List<Sysuser> selectByExample(SysuserExample example);

    Sysuser selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") Sysuser record, @Param("example") SysuserExample example);

    int updateByExample(@Param("record") Sysuser record, @Param("example") SysuserExample example);

    int updateByPrimaryKeySelective(Sysuser record);

    int updateByPrimaryKey(Sysuser record);
}