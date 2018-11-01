package org.anson.demo.domain.sys;

import org.anson.demo.domain.IBaseDomain;
import org.anson.demo.pojo.Example;
import org.anson.demo.pojo.bo.sys.SysUserBo;
import org.anson.demo.mapper.sys.SysUserMapper;
import org.anson.demo.pojo.po.sys.SysUserPo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SysUserDomain implements IBaseDomain<SysUserPo, SysUserBo> {
    @Autowired
    private SysUserMapper mapper;

    @Override
    public long countByExample(Example example){
        return mapper.countByExample(example);
    }

    @Override
    public int delById(Long id){
        // 一般不提供删除功能
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delByIdList(List<Object> idList){
        throw new RuntimeException("暂不支持");
    }

    @Override
    public int save(SysUserPo record){
        if(record == null){
            throw new RuntimeException("record is null");
        }

        // 用户编码必输
        if(StringUtils.isBlank(record.getNo())){
            throw new RuntimeException("no is null");
        }

        Date nowTime = new Date();

        record.setId(1L);
        record.setLastUpdateTime(nowTime);

        if(StringUtils.isEmpty(record.getName())){
            record.setName("");
        }

        if(StringUtils.isEmpty(record.getPassword())){
            record.setPassword("");
        }

        if(StringUtils.isEmpty(record.getMobile())){
            record.setMobile("");
        }

        return mapper.insert(record);
    }

    @Override
    public int save(List<SysUserPo> recordList){
        throw new RuntimeException("暂不支持");
    }

    @Override
    public int saveOrUpdate(SysUserPo record){
        throw new RuntimeException("暂不支持");
        //return 0;
    }

    @Override
    public int saveOrUpdate(List<SysUserPo> record){
        throw new RuntimeException("暂不支持");
    }

    @Override
    public List<SysUserBo> selByExample(Example example){
        return SysUserBo.po2bo(mapper.selectByExample(example));
    }

    @Override
    public SysUserBo selById(Long id){
        return SysUserBo.po2bo(mapper.selectByPrimaryKey(id));
    }

    @Override
    public List<SysUserBo> selByIdList(List<Object> idList){
        throw new RuntimeException("暂不支持");
    }

    @Override
    public int updateByExampleSelective(SysUserPo record, Example example){
        Date nowTime = new Date();

        // id 不更新
        record.setId(null);

        // 最后更新时间
        // 若判空, 再赋值, 会有小问题, 当调用方取了最后更新时间, 但是没有更新, 这样子最后更新时间就不准确了
        record.setLastUpdateTime(nowTime);

        return mapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByIdSelective(SysUserPo record){
        if(record == null || record.getId() == null){
            return 0;
        }

        Date nowTime = new Date();

        // 最后更新时间
        record.setLastUpdateTime(nowTime);

        return mapper.updateByPrimaryKeySelective(record);
    }
}
