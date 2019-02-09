package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
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

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public TaotaoResult register(TbUser user) {
        //检查数据的有效性
        if(StringUtils.isBlank(user.getUsername())){
            return TaotaoResult.build(400, "用户名不能为空");
        }
        //判断用户名是否重复
        TaotaoResult result = checkUserData(user.getUsername(), 1);
        if (!(boolean) result.getData()) {
            return TaotaoResult.build(400, "用户名重复");
        }
        //判断密码是否为空
        if (StringUtils.isBlank(user.getPassword())) {
            return TaotaoResult.build(400, "密码不能为空");
        }
        if (StringUtils.isNotBlank(user.getPhone())) {
            //是否重复校验
            TaotaoResult phoneResult = checkUserData(user.getPhone(), 2);
            if(!(boolean)phoneResult.getData()){
                return TaotaoResult.build(400, "电话号码重复");
            }
        }
        //如果email不为空，进行重复性校验
        if (StringUtils.isNotBlank(user.getEmail())) {
            //是否重复校验
            TaotaoResult phoneResult = checkUserData(user.getEmail(), 2);
            if(!(boolean)phoneResult.getData()){
                return TaotaoResult.build(400, "email重复");
            }
        }
        //补全pojo属性
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //密码要md5加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        tbUserMapper.insert(user);
        //返回注册成功
        return TaotaoResult.ok();

    }
}
