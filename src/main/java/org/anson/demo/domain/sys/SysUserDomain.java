package org.anson.demo.domain.sys;

import org.anson.demo.constant.Constant;
import org.anson.demo.domain.framework.IBaseDomain;
import org.anson.demo.javabean.biz.sys.sysUser.dto.GetDto;
import org.anson.demo.tool.helper.id.IdHelper;
import org.anson.demo.javabean.framework.query.Example;
import org.anson.demo.javabean.biz.sys.sysUser.SysUser;
import org.anson.demo.javabean.biz.sys.sysUser.SysUserBo;
import org.anson.demo.mapper.sys.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SysUserDomain implements IBaseDomain<SysUser, SysUserBo> {
    @Autowired
    private SysUserMapper mapper;

    public SysUserBo selByNo(String no){
        SysUser sysUser = mapper.selByNo(no);
        return SysUserBo.entity2bo(sysUser);
    }

    public List<SysUserBo> sel(GetDto dto){
        Date nowTime = new Date();
        Example example = new Example();

        if(dto == null){
            List<SysUser> userList = mapper.selectByExample(example);
            return SysUserBo.entity2bo(userList);
        }

        Example.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(dto.getNo())){
            criteria.andEqualTo(SysUser.NO, dto.getNo());
        }

        if(!StringUtils.isEmpty(dto.getMobile())){
            criteria.andEqualTo(SysUser.MOBILE, dto.getMobile());
        }

        if(dto.getLastUpdateTime() != null){
            criteria.andGreaterThan(SysUser.LASTUPDATETIME, dto.getLastUpdateTime());
        }

        if(dto.getBeginCreateTime() != null){
            criteria.andGreaterThan(SysUser.CREATETIME, dto.getBeginCreateTime());
        }

        criteria.andLessThan(SysUser.CREATETIME, dto.getEndCreateTime()==null?nowTime:dto.getEndCreateTime());

        criteria.andLike(SysUser.NAME, "%" + dto.getName() + "%");

        if(StringUtils.isNotEmpty(dto.getOrderByClause())){
            example.setOrderByClause(dto.getOrderByClause());
        }

        List<SysUser> userList = mapper.selectByExample(example);
        return SysUserBo.entity2bo(userList);
    }

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
    public Long save(SysUser record){
        if(record == null){
            throw new RuntimeException("record is null");
        }

        // 用户编码必输
        if(StringUtils.isBlank(record.getNo())){
            throw new RuntimeException("no is null");
        }

        Date nowTime = new Date();

        record.setId(IdHelper.nextId());
        record.setCreateTime(nowTime);
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

        // 已存在该用户编码
        if(mapper.selByNo(record.getNo()) != null){
            throw new RuntimeException("no has exists");
        }

        mapper.insert(record);

        return record.getId();
    }

    @Override
    public int save(List<SysUser> recordList){
        throw new RuntimeException("暂不支持");
    }

    @Override
    public int saveOrUpdate(SysUser record){
        throw new RuntimeException("暂不支持");
        //return 0;
    }

    @Override
    public int saveOrUpdate(List<SysUser> record){
        throw new RuntimeException("暂不支持");
    }

    @Override
    public List<SysUserBo> selByExample(Example example){
        return SysUserBo.entity2bo(mapper.selectByExample(example));
    }

    @Override
    public SysUserBo selById(Long id){
        return SysUserBo.entity2bo(mapper.selectByPrimaryKey(id));
    }

    @Override
    public List<SysUserBo> selByIdList(List<Object> idList){
        throw new RuntimeException("暂不支持");
    }

    @Override
    public int updateByExampleSelective(SysUser record, Example example){
        Date nowTime = new Date();

        // id 不更新
        record.setId(null);

        // 最后更新时间
        // 若判空, 再赋值, 会有小问题, 当调用方取了最后更新时间, 但是没有更新, 这样子最后更新时间就不准确了
        record.setLastUpdateTime(nowTime);

        return mapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByIdSelective(SysUser record){
        if(record == null || record.getId() == null){
            return 0;
        }

        Date nowTime = new Date();

        // 最后更新时间
        record.setLastUpdateTime(nowTime);

        return mapper.updateByPrimaryKeySelective(record);
    }
}
