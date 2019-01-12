package com.taotao.controller;

import com.taotao.common.utils.FastDFSClient;
import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传Controller
 */

@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping("/pic/upload")
    //直接通过Response响应数据给浏览器
    //springMVC有个默认行为，如果响应json，jakson会将对象转换为json串再响应给浏览器
    //如果返回的是字符串，就直接响应，不在做转换
    @ResponseBody
    //因为之前使用kindEditor插件对浏览器的兼容性不是很好，因此如果响应的是json数据，会导致部分浏览器图片上传无法使用
    //如果返回的是字符串，则不会有该问题

    //要将对象转换为json串要用到json工具类
    public String picUpload(MultipartFile uploadFile) {
        try {
            //接受上传的文件
            //取扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            url = IMAGE_SERVER_URL + url;
            //响应上传图片的url
            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            return JsonUtils.objectToJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message","图片上传失败");
            return JsonUtils.objectToJson(result);
        }
    }
}
