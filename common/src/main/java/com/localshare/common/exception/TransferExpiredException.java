package com.localshare.common.exception;

public class TransferExpiredException extends RuntimeException {
    public  TransferExpiredException(String message) {
        super(message);
    }
}
