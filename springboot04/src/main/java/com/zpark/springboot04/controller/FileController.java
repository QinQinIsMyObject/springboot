package com.zpark.springboot04.controller;

import com.zpark.springboot04.exception.DownloadFileNotExists;
import com.zpark.springboot04.util.FileUtil;
import com.zpark.springboot04.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Celery
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @GetMapping("test1")
    public Object test1() {
        return R.ok("test1正常！");
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @return
     */
    @GetMapping("/downloadFile")
    public Object downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        //因为是下载文件，所以返回值不能是json
        try {
            FileUtil.fileDownload(fileName, response);
        } catch (DownloadFileNotExists downloadFileNotExists) {
            return R.error("文件不存在！");
        }
        return null;
    }

}
