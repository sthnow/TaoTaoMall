package com.taotao.content.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理service
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Autowired TbContentMapper tbContentMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(long parentId) {
        //根据parentId查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        //设置查询条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

        List<EasyUITreeNode> resultList = new ArrayList<>();

        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public TaotaoResult addContentCategory(long parentId, String name) {
        //创建一个pojo对象
        TbContentCategory contentCategory = new TbContentCategory();
        //补全这个pojo的属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //状态，setStatus 1.正常 2.删除
        contentCategory.setStatus(1);
        //排序，默认是1
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //插入到数据库
        contentCategoryMapper.insert(contentCategory);
        //判断父节点的状态
        //查询父节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            //如果父节点为叶子节点应该改为父节点
            parent.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        //返回结果
        return TaotaoResult.ok(contentCategory);
    }

    @Override
    /**
     * 重命名节点的方法
     */
    public TaotaoResult renameContentCategory(long id, String name) {
        TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //修改节点的名称
        tbContentCategory.setName(name);
        //从内存中更新到数据库中
        contentCategoryMapper.updateByPrimaryKey(tbContentCategory);

        return TaotaoResult.ok();
    }


    @Override
    //删除节点的方法
    public TaotaoResult deleteContentCategory(long id) {
        //1.首先根据这个id查询到pojo
        //2.通过pojo中的isParent属性查询是不是父节点
        //如果不是父节点，删除并返回ok
        //如果是父节点，不删除并返回
        TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);

        if (!tbContentCategory.getIsParent()) {
            //将其父节点设置为叶子节点
            //得到其父节点的id
            long parentId = tbContentCategory.getParentId();
            //通过父节点的id查询到父节点
            //创建example
            TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
            //设置查询条件
            TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //根据example设置查询条件的id查询到list
            List<TbContentCategory> list = contentCategoryMapper.selectByExample(tbContentCategoryExample);

            //如果list的长度==1，表示这个父节点下只有一个子节点
            if (list.size() == 1) {
                //如果该父节点只有一个子节点则设置该父节点为叶子节点
                TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
                parentNode.setIsParent(false);
                contentCategoryMapper.updateByPrimaryKey(parentNode);
            }else {
                //如果该父节点下有多个子节点则不改变其状态
//                System.out.println("该父节点下还有其他子节点，不改变其状态");
            }
            contentCategoryMapper.deleteByPrimaryKey(id);
                return TaotaoResult.ok();
            }

        else {
                return TaotaoResult.build(500, "notOk");
            }

        }

    @Override
    /**
     * 分类内容展示方法
     */
    public EasyUIDataGridResult showContentCategory(long id, int page, int rows) {
        PageHelper.startPage(page,rows);
        //通过分类的id查询到一个list
        //创建example
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        //根据设置的条件查询
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);

        PageInfo<TbContent> tbContentPageInfo = new PageInfo<>(tbContents);
        //将tbContents包装成EasyUIDataGridResult
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        //向里面设置数据
        result.setTotal(tbContentPageInfo.getTotal());
        result.setRows(tbContents);
        return result;
    }


}
