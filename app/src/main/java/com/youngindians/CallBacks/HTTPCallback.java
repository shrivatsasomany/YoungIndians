package com.youngindians.CallBacks;

import java.sql.Struct;

/**
 * Created by shrivatsa on 14/08/17.
 */

public interface HTTPCallback {
    public void onSuccess(String info, int callCode);
    public void onFailure(int callBackCode, int callCode);
}
