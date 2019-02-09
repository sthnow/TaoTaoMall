package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户处理Service
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TaotaoResult checkUserData(String data, int type) {
        //执行查询

        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        //设置查询条件
        //查询数据type类型：1-判断用户名是否可用,2-判断手机号是否可用,3-判断邮箱是否可用
        if (type == 1) {
            criteria.andUsernameEqualTo(data);
        }
        else if(type == 2){
            criteria.andPhoneEqualTo(data);
        }
        else if(type == 3){
            criteria.andEmailEqualTo(data);
        }
        else{
            return TaotaoResult.build(400,"非法数据");
        }
        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);

        if (list != null && list.size() > 0) {
            //查询到数据，返回false
            return TaotaoResult.ok(false);
        }
        //未查询到数据，数据可用
        return TaotaoResult.ok(true);

    }
}
