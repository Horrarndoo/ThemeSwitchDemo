package com.horrarndoo.zyw.themeswitchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.horrarndoo.zyw.themeswitchdemo.helper.ResourceHelper;
import com.horrarndoo.zyw.themeswitchdemo.theme.ThemeChangeObserver;
import com.horrarndoo.zyw.themeswitchdemo.theme.ThemeManager;

public class MainActivity extends AppCompatActivity implements ThemeChangeObserver {
    protected ThemeManager mThemeManager = new ThemeManager();
    private ResourceHelper mHelper;
    private TextView tvTheme;
    private Button btnTheme;
    private LinearLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(mThemeManager.getCurrentTheme());
        setContentView(R.layout.activity_main);

        tvTheme = findViewById(R.id.tv_theme);
        btnTheme = findViewById(R.id.btn_theme);
        llRoot = findViewById(R.id.ll_root);

        mHelper = new ResourceHelper(this);
        mThemeManager.registerObserver(this);
        btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThemeManager.setThemeMode(!mThemeManager.isNightThemeMode());
            }
        });
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
        tvTheme.setText(mThemeManager.isNightThemeMode() ? "夜间模式" : "白天模式");
        mHelper.setTextColorByAttr(tvTheme, android.R.attr.textColor);
        mHelper.setTextColorByAttr(btnTheme, android.R.attr.textColor);
        mHelper.setBackgroundResourceByAttr(llRoot, android.R.attr.windowBackground);
        mHelper.setBackgroundResourceByAttr(btnTheme, R.attr.widgetBackground);
    }
}
