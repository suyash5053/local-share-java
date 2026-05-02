package com.localshare.common.exception;

public class DeviceNotFoundException extends  RuntimeException{
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
