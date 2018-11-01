package org.anson.demo.domain;

import org.anson.demo.pojo.Example;
import org.anson.demo.pojo.po.sys.SysUser;

import java.util.List;

/**
 * 领域通用方法
 */
public interface IDomain<T, S> {
    public long countByExample(Example example);

    public int deleteByPrimaryKey(Long id);

    public int insert(T record);

    public List<S> selectByExample(Example example);

    public S selectByPrimaryKey(Long id);

    public int updateByExampleSelective(T record, Example example);

    public int updateByPrimaryKeySelective(T record);

    // 不需要以下
    //public int deleteByExample(Example example);
    //public int insertSelective(T record);
    //public int updateByPrimaryKey(T record);
    //public int updateByExample(T record, Example example);
}
