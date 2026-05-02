package com.localshare.common.exception;

public class TransferRejectedException extends RuntimeException {
    public TransferRejectedException(String message) {
        super(message);
    }
}
