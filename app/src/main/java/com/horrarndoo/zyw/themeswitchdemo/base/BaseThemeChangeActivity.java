package com.horrarndoo.zyw.themeswitchdemo.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.horrarndoo.zyw.themeswitchdemo.theme.ThemeChangeObserver;

/**
 * Created by Horrarndoo on 2018/6/5.
 * <p>
 * 实现主题管理者注册和注销以及观察者接口方法的BaseActivity
 * 子类在refeshThemeUI中实现具体界面控件颜色属性等相关设置
 */

@SuppressLint("Registered")
public abstract class BaseThemeChangeActivity extends BaseActivity implements ThemeChangeObserver {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThemeManager.registerObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThemeManager.unregisterObserver(this);
    }

    @Override
    public void resetCurrentTheme() {
        setTheme(mThemeManager.getCurrentTheme());
    }

    @Override
    public void notifyThemeChanged() {
        refeshThemeUI();
    }

    /**
     * 子类实现
     * <p>
     * 根据当前主题刷新UI - 设置控件颜色值等属性
     */
    protected abstract void refeshThemeUI();
}
