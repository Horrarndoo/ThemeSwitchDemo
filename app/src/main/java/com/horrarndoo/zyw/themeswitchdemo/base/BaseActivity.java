package com.horrarndoo.zyw.themeswitchdemo.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.horrarndoo.zyw.themeswitchdemo.helper.ResourceHelper;
import com.horrarndoo.zyw.themeswitchdemo.theme.ThemeManager;

/**
 * Created by Horrarndoo on 2018/5/29.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    protected ThemeManager mThemeManager = new ThemeManager();
    protected ResourceHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new ResourceHelper(this);
        setTheme(mThemeManager.getCurrentTheme());
    }
}
