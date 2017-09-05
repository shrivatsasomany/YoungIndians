package com.youngindians.Communication;

/**
 * Created by shrivatsa on 14/08/17.
 */

public interface HTTPCallback {
    public void onSuccess(Object o);
    public void onFailure(String error);
}
