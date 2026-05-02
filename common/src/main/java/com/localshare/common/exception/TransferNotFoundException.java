package com.localshare.common.exception;

public class TransferNotFoundException  extends RuntimeException {
    public TransferNotFoundException(String message) {
        super(message);
    }
}
