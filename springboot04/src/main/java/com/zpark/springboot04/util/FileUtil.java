package com.zpark.springboot04.util;

import com.zpark.springboot04.exception.DownloadFileNotExists;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Celery
 * 文件工具类
 */
public class FileUtil {
    /**
     * 用于文件操作的本地路径
     */
    static String baseLocation = "E:/Desktop/file/";

    /**
     * 下载文件
     *
     * @param fileName
     * @param response
     * @throws DownloadFileNotExists
     */
    public static void fileDownload(String fileName, HttpServletResponse response) throws DownloadFileNotExists {
        //创建一个要下载的文件对象
        File file = new File(baseLocation + fileName);
        //判断该文件是否存在
        if (!file.exists()) {
            throw new DownloadFileNotExists();
        }
        //读取文件，此时需要一个文件输入流
        FileInputStream fileInputStream = null;
        try {
            //设置响应头，表明这个响应的类型是文件
            response.setHeader("Content-Type", "application/octet-stream");
            //设置响应头，表明文件的名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            //设置响应头，表明文件的大小
            response.setHeader("Content-Length", file.length() + "");

            fileInputStream = new FileInputStream(file);
            //创建一个信息的缓冲区，10kB
            byte[] buffer = new byte[10 * 1024];
            //读取到的字节数（当这个值为-1则结束）
            int len = -1;
            //循环读取文件并输出，len表示的是“实际读取到的字节数”
            while ((len = fileInputStream.read(buffer)) != -1) {
                //输出
                response.getOutputStream().write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    fileInputStream = null;
                }
            }
        }

    }

    public static void fileUpload(MultipartFile file) {
        //新建一个文件
        File f = new File(baseLocation + file.getOriginalFilename());
        int index = 1;
        //得到一个肯定不存在的文件名
        while (f.exists()) {
            f = new File(baseLocation + index + file.getOriginalFilename());
            index++;
        }
        //创建这个文件
        try {
            f.createNewFile();
            //创建一个文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            //得到上传文件的数据
            byte[] data = file.getBytes();
            //将数据输出在磁盘上
            fileOutputStream.write(data);
            //关流
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
