package com.localshare.common.exceptions;

public class TransferExpiredException extends RuntimeException {
    public  TransferExpiredException(String message) {
        super(message);
    }
}
