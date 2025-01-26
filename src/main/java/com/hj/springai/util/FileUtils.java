package com.hj.springai.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * @FileName FileUtils
 * @author yrpyy
 * @Data 2024-12-15
 **/
@Component
public class FileUtils {
    public static String upLoad(MultipartFile multipartFile, String UPLOAD_DIR) {
        //设定文件存储路径为项目根目录下的 upload 文件夹, 也可以是直接定义绝对路径"
        int id = TokenUtils.getCurrentUser().getUid();
        String contentType = multipartFile.getContentType();
        //检查文件是否为空
        if (multipartFile.isEmpty()) {
            return "No file upload";
        }
        //重命名文件名
        String fileName = multipartFile.getOriginalFilename();
        int index = contentType.lastIndexOf("/");
        String type = contentType.substring(0,index);

        String reName = "id" + "-" + "001" + "-" + type + "-" + fileName;
        System.out.println(type);
        //检查文件名是否为空或者空字符串
        if (fileName == null || fileName.trim().isEmpty()) {
            return "Filename cannot be null or empty";
        }
        //检查文件大小: 数字 10485760 等于10 * 1024 * 1024, 也就是 10MB 的字节大小
        long fileSize = multipartFile.getSize();
        if (fileSize > 10485760) {
            return "File size exceeds the limit!";
        }
        //检查文件类型(自由添加)
        if ("image/png".equals(contentType) || "image/jpeg".equals(contentType)) {
            UPLOAD_DIR = "image\\";
            return upLoadFile(UPLOAD_DIR,reName,multipartFile);
        }else if ("video/mp4".equals(contentType) || "video/mp3".equals(contentType) || "video/avi".equals(contentType)) {
            UPLOAD_DIR += "video\\";
            return upLoadFile(UPLOAD_DIR,reName,multipartFile);
        } else if ("text/txt".equals(contentType) || "text/doc".equals(contentType) || "text/html".equals(contentType) || "text/plain".equals(contentType) || "text/pdf".equals(contentType)) {
            UPLOAD_DIR += "text\\";
            return upLoadFile(UPLOAD_DIR,reName,multipartFile);
        }else {
            System.out.println("不支持该类型文件");
            return "不支持该类型文件";
        }
    }

    //文件上传
    public static String upLoadFile(String UPLOAD_DIR,String filename,MultipartFile multipartFile){
        try {
            //如果目标路径下的文件夹不存在, 则创建新文件夹
            File file = new File(UPLOAD_DIR);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    System.out.println("Directory created: " + UPLOAD_DIR);
                } else {
                    System.out.println("Failed to create directory: " + UPLOAD_DIR);
                }
            } else {
                System.out.println("Directory already exists: " + UPLOAD_DIR);
            }
            File uploadFile = new File(file, filename);
            System.out.println(filename);
            multipartFile.transferTo(uploadFile);
            return "File uploaded successfully: " + multipartFile.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
            return "File upload failed";
        }
    }

    //雪花形成编号
    public static long getFileId(){
        Date date = new Date();
        int random = (int) (Math.random() * 1000000);
        return date.getTime()*1000000+random;
    }
}
