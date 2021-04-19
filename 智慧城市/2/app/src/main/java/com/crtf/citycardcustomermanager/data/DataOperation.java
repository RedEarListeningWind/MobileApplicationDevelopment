package com.crtf.citycardcustomermanager.data;

import android.content.SharedPreferences;

public interface DataOperation {

    /**
     * 获取读数据
     * @return
     */
    SharedPreferences getReadData();

    /**
     * 获取写数据
     * @return
     */
    SharedPreferences.Editor getWriteData();
}
