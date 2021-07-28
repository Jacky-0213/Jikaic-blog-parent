package top.jiakaic.blog.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.jiakaic.blog.utils.QiniuUtils;
import top.jiakaic.blog.vo.Result;

import java.io.File;
import java.util.UUID;

/**
 * @author JK
 * @date 2021/7/28 -10:15
 * @Description
 **/
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile img){
        String fileName= UUID.randomUUID().toString()+"."+ StringUtils.substringAfterLast(img.getOriginalFilename(),".");
        boolean upload = qiniuUtils.upload(img, fileName);
        if (upload){
            return Result.success(QiniuUtils.url + fileName);
        }
        return Result.fail(20001,"上传失败");
    }
}
