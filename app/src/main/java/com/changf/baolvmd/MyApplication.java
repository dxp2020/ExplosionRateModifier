package com.changf.baolvmd;

import com.mula.base.BaseApplication;

public class MyApplication extends BaseApplication {

    @Override
    public int getLevel() {
        return LEVEL_APP;
    }

    @Override
    public Class[] subDelegates() {
        return new Class[0];
    }

    @Override
    public void onCreateDelegate() {

    }
}
