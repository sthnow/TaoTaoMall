package com.taotao.item.pojo;

import com.taotao.pojo.TbItem;

//为什么不需要实现序列化接口？
public class Item extends TbItem {

    public Item(TbItem tbItem){
        //初始化属性
        this.setId(tbItem.getId());
        this.setTitle(tbItem.getTitle());
        this.setSellPoint(tbItem.getSellPoint());
        this.setPrice(tbItem.getPrice());
        this.setNum(tbItem.getNum());
        this.setBarcode(tbItem.getBarcode());
        this.setImage(tbItem.getImage());
        this.setCid(tbItem.getCid());
        this.setStatus(tbItem.getStatus());
        this.setCreated(tbItem.getCreated());
        this.setUpdated(tbItem.getUpdated());
    }

    public String[] getImages(){
        if(this.getImage() != null && !"".equals(this.getImage())){
            String image = this.getImage();
            String[] strings = image.split(",");
            return strings;
        }
        return null;
    }
}
