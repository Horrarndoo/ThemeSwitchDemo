package com.horrarndoo.zyw.themeswitchdemo.theme;


import com.horrarndoo.zyw.themeswitchdemo.utils.AppUtils;
import com.horrarndoo.zyw.themeswitchdemo.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horrarndoo on 2018/5/29.
 * <p>
 */
public class ThemeManager {
    //    后续做多主题使用
    //    public static final int THEME_DAY_MODE = 1;
    //    public static final int THEME_NIGHT_MODE = -1;

    private List<ThemeChangeObserver> mThemeChangeObservers; //  主题切换监听栈

    /**
     * 获得observer list
     */
    private List<ThemeChangeObserver> obtainThemeChangeObserverStack() {
        if (mThemeChangeObservers == null)
            mThemeChangeObservers = new ArrayList<>();
        return mThemeChangeObservers;
    }

    /**
     * 向list中添加observer
     */
    public void registerObserver(ThemeChangeObserver observer) {
        if (observer == null || obtainThemeChangeObserverStack().contains(observer))
            return;
        obtainThemeChangeObserverStack().add(observer);
    }

    /**
     * 从list中移除observer
     */
    public void unregisterObserver(ThemeChangeObserver observer) {
        if (observer == null || !(obtainThemeChangeObserverStack().contains(observer)))
            return;
        obtainThemeChangeObserverStack().remove(observer);
    }

    /**
     * 向所有对象发送更新UI的指令
     */
    public void notifyByThemeChanged() {
        List<ThemeChangeObserver> observers = obtainThemeChangeObserverStack();
        for (ThemeChangeObserver observer : observers) {
            observer.resetCurrentTheme();
            observer.notifyThemeChanged();
        }
    }

    /**
     * 获取theme mode
     *
     * @return themeMode
     */
    public boolean isNightThemeMode() {
        return SpUtils.getNightModel(AppUtils.getContext());
    }

    /**
     * 设置theme mode
     *
     * @param isNightMode 是否夜间模式 true： 夜间 false： 白天
     */
    public void setThemeMode(boolean isNightMode) {
        SpUtils.setNightModel(AppUtils.getContext(), isNightMode);
        notifyByThemeChanged();
    }

    /**
     * 返回当前Theme
     *
     * @return 当前Theme
     */
    public int getCurrentTheme() {
        return ThemeConstants.themeArr[SpUtils.getThemeIndex(AppUtils.getContext())
                ][isNightThemeMode() ? 1 : 0];
    }
}
