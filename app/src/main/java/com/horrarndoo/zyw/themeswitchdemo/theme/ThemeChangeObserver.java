package com.horrarndoo.zyw.themeswitchdemo.theme;

/**
 * Created by Horrarndoo on 2018-05-29.
 * <p>
 * Threme变化观察者接口
 */

public interface ThemeChangeObserver {
    /**
     * 重新设置当前的Theme
     */
    void resetCurrentTheme();

    /**
     * 通知订阅者Theme发生变化
     */
    void notifyThemeChanged();
}
