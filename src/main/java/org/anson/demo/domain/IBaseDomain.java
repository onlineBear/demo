package org.anson.demo.domain;

import org.anson.demo.pojo.Example;

import java.util.List;

/**
 * 领域通用方法
 * 若有些方法不需实现, 则可直接抛异常, 说明暂不实现既可
 */
public interface IBaseDomain<PO, BO> {
    /**
     * 根据条件计数
     * @param example
     * @return
     */
    public long countByExample(Example example);

    /**
     * 根据主键id删除记录
     * @param id
     * @return
     */
    public int delById(Long id);

    /**
     * 根据 idList 删除, Object 为 PO 或 主键类型 Long
     * @param idList
     * @return
     */
    public int delByIdList(List<Object> idList);

    /**
     * 插入记录
     * @param record
     * @return
     */
    public int save(PO record);

    /**
     * 插入记录
     * @param recordList
     * @return
     */
    public int save(List<PO> recordList);

    /**
     * 插入记录或更新记录
     * @param record
     * @return
     */
    public int saveOrUpdate(PO record);

    /**
     * 插入记录或更新记录
     * @param record
     * @return
     */
    public int saveOrUpdate(List<PO> record);

    /**
     * 根据条件查询
     * @param example
     * @return
     */
    public List<BO> selByExample(Example example);

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    public BO selById(Long id);

    /**
     * 根据 主键列表(idList) 查询, Object 为 PO 类型 或 主键类型 Long
     * @param idList
     * @return
     */
    public List<BO> selByIdList(List<Object> idList);

    /**
     * 根据条件更新
     * @param record
     * @param example
     * @return
     */
    public int updateByExampleSelective(PO record, Example example);

    /**
     * 根据主键id更新
     * @param record
     * @return
     */
    public int updateByIdSelective(PO record);

    // 不需要以下
    //public int delByExample(Example example);
    //public int insertSelective(T record);
    //public int updateById(T record);
    //public int updateByExample(T record, Example example);
}
