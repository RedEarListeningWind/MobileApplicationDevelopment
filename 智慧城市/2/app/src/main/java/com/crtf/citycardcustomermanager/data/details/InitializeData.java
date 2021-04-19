package com.crtf.citycardcustomermanager.data.details;


import android.content.Context;
import android.content.SharedPreferences;

import com.crtf.citycardcustomermanager.data.DataOperation;

/**
 * 初始化数据
 */
public class InitializeData implements DataOperation {

    private static volatile InitializeData instance;
    private static final String INITIALIZE = "Initialize";

    private Context context;

    private volatile SharedPreferences sharedPreferences;

    public static InitializeData getInstance(Context context) {
        if (instance == null)
            synchronized (InitializeData.class) {
                if (instance == null)
                    instance = new InitializeData(context);
            }
        return instance;
    }

    public InitializeData(Context context) {
        this.context = context;
    }

    @Override
    public SharedPreferences getReadData() {
        if (this.sharedPreferences == null)
            synchronized (this) {
                if (this.sharedPreferences == null)
                    this.sharedPreferences = context.getSharedPreferences(InitializeData.INITIALIZE, Context.MODE_PRIVATE);
            }
        return this.sharedPreferences;
    }

    @Override
    public SharedPreferences.Editor getWriteData() {
        return getReadData().edit();
    }
}
