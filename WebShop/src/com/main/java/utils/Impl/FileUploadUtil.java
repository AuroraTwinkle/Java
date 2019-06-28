package com.main.java.utils.Impl;

import com.main.java.model.FileImage;
import com.main.java.utils.FileUpload;
import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.UUID;


@Component("fileUpload")
public class FileUploadUtil implements FileUpload {
    @Value("#{prop.basePath+prop.filePath}")
    private String filePath;


    //实现文件上传，并返回上传后的新文件名称
    @Override
    public String uploadFile(FileImage fileImage){
        String pic = newFileName(fileImage.getFilename());
        try{
            FileUtil.copyFile(fileImage.getFile(),new File(filePath,pic));
            return pic;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            fileImage.getFile().delete();
        }
    }


    //通过文件名获取扩展名
    private String getFileExt(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }

   //生成UUID随机数，作为新的文件名
    private String newFileName(String fileName) {
        String ext = getFileExt(fileName);
        return UUID.randomUUID().toString() + "." + ext;
    }

}
