package com.chong.exception;

import com.chong.enums.ResultCode;
import lombok.Getter;

import java.io.IOException;

/**
 * 存储异常
 * Created by LXChild on 07/04/2017.
 */
public class StorageException extends IOException {

    @Getter
    private final ResultCode code;

    private StorageException(ResultCode code) {
        super(code.getComment());
        this.code = code;
    }

    private StorageException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }


    public static class StorageFileException extends StorageException {
        public StorageFileException() {
            super(ResultCode.STORAGE_FAILED);
        }

        public StorageFileException(String message) {
            super(ResultCode.STORAGE_FAILED, message);
        }
    }

    public static class FileNotFoundException extends StorageException {
        public FileNotFoundException() {
            super(ResultCode.NOT_FOUND);
        }

        public FileNotFoundException(String message) {
            super(ResultCode.NOT_FOUND, message);
        }
    }
}
