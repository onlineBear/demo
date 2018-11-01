package org.anson.demo.domain.sys;

import org.anson.demo.domain.IDomain;
import org.anson.demo.pojo.Example;
import org.anson.demo.pojo.bo.sys.SysUserBo;
import org.anson.demo.mapper.sys.SysUserMapper;
import org.anson.demo.pojo.po.sys.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SysUserDomain implements IDomain<SysUser, SysUserBo> {
    @Autowired
    private SysUserMapper mapper;

    // 通用功能
    @Override
    public long countByExample(Example example){
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id){
        // 一般不提供删除功能
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUser record){
        if(record == null){
            throw new RuntimeException("record is null");
        }

        // 用户编码必输
        if(StringUtils.isBlank(record.getNo())){
            throw new RuntimeException("no is null");
        }

        Date nowTime = new Date();

        record.setId(1L);
        record.setLastUpdatetime(nowTime);

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
    public List<SysUserBo> selectByExample(Example example){
        return SysUserBo.po2bo(mapper.selectByExample(example));
    }

    @Override
    public SysUserBo selectByPrimaryKey(Long id){
        return SysUserBo.po2bo(mapper.selectByPrimaryKey(id));
    }

    @Override
    public int updateByExampleSelective(SysUser record, Example example){
        Date nowTime = new Date();

        // id 不更新
        record.setId(null);

        // 最后更新时间
        // 若判空, 再赋值, 会有小问题, 当调用方取了最后更新时间, 但是没有更新, 这样子最后更新时间就不准确了
        record.setLastUpdatetime(nowTime);

        return mapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record){
        if(record == null || record.getId() == null){
            return 0;
        }

        Date nowTime = new Date();

        // 最后更新时间
        record.setLastUpdatetime(nowTime);

        return mapper.updateByPrimaryKeySelective(record);
    }
}
