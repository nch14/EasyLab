package com.chenh.easylab.util;

import java.util.Objects;

/**
 * Created by chenh on 2016/6/10.
 */
public class ServerBackData {
    private boolean resultState;
    /**
     * 如果resultState为false，
     */
    private String message;

    private Object object;

    public boolean isResultState() {
        return resultState;
    }

    public void setResultState(boolean resultState) {
        this.resultState = resultState;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
