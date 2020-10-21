package com.zpark.springboot04.exception;

/**
 * @author Celery
 * 下载时可能出现的异常
 */
public class DownloadFileNotExists extends Exception {

    public DownloadFileNotExists() {
        super();
    }

    public DownloadFileNotExists(String message) {
        super(message);
    }

}
