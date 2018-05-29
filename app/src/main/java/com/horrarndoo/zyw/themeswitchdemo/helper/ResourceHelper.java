package com.horrarndoo.zyw.themeswitchdemo.helper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Horrarndoo on 2018-05-29.
 * <p>
 */

public class ResourceHelper {

    private final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#00FFFFFF");
    private final int DEFAULT_HINT_TEXT_COLOR = Color.parseColor("#FF969696");
    private final int DEFAULT_TEXT_COLOR = Color.parseColor("#FF727272");
    private final int DEFAULT_TINT_COLOR = Color.parseColor("#00FFFFFF");
    private final int DEFAULT_COLOR = Color.parseColor("#FF000000");
    private final float DEFAULT_FLOAT_VALUE = 1.0f;

    private Context mContext;  // 上下文环境

    public ResourceHelper(Context context) {
        setContext(context);
    }

    /**
     * 设置上下文环境
     *
     * @param context 上下文环境
     */
    private void setContext(Context context) {
        this.mContext = context;
    }

    /**
     * 获取上下文环境
     *
     * @return Context
     */
    private Context getContext() {
        return mContext;
    }

    /**
     * 检查上下文环境
     */
    private boolean isCurrentContextValid() {
        if (getContext() == null)
            throw new NullPointerException(" the context could't be null. ");
        return getContext() != null;
    }

    /**
     * 设置当前上下文环境下的主题样式
     *
     * @param resId 需要设置上去的主题样式所对应的资源Id
     */
    public void setCurrentTheme(int resId) {
        if (!isCurrentContextValid())
            return;
        getContext().setTheme(resId);
    }

    /**
     * 获得当前上下文环境下对应的主题Theme
     *
     * @return Theme
     */
    public Resources.Theme getCurrentContextTheme() {
        if (!isCurrentContextValid())
            return null;
        return getContext().getTheme();
    }

    /**
     * 根据attr属性id获得typedValue对象
     *
     * @param attrId 资源属性对应的id
     * @return TypedValue
     */
    @Nullable
    private TypedValue obtainTypedValue(int attrId) {

        Resources.Theme theme = getCurrentContextTheme();
        if (theme == null)
            return null;
        TypedValue typedValue = new TypedValue();
        try {
            theme.resolveAttribute(attrId, typedValue, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typedValue;
    }

    /**
     * 根据所给定的arrtId获得Identifier资源id
     *
     * @param attrId 资源属性对应的id
     */
    public int getIdentifierByAttrId(int attrId) {
        TypedValue typedValue = obtainTypedValue(attrId);
        return typedValue == null ? 0 : typedValue.resourceId;
    }

    /**
     * 根据给定的attrId获得typedArray属性集合
     *
     * @param attrId 资源属性对应的id
     */
    @Nullable
    private TypedArray innerGetTypedArrayByAttr(int attrId) {
        if (!isCurrentContextValid())
            return null;
        TypedValue typedValue = obtainTypedValue(attrId);
        if (typedValue == null)
            return null;
        TypedArray typedArray = null;
        try {
            typedArray = getContext().getResources().obtainTypedArray(typedValue.resourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typedArray;
    }

    /**
     * 根据给定的attrId获得typedArray属性集合
     *
     * @param attrId 资源属性对应的id
     */
    public TypedArray getTypedArrayByAttr(int attrId) {
        return innerGetTypedArrayByAttr(attrId);
    }

    /**
     * 根据给定的attrId获取Color颜色值
     *
     * @param attrId       资源属性对应的id
     * @param defaultColor 默认color
     */
    private int innerGetColorByAttr(int attrId, int defaultColor) {
        if (!isCurrentContextValid())
            return defaultColor;
        TypedValue typedValue = obtainTypedValue(attrId);
        if (typedValue == null)
            return defaultColor;
        try {
            defaultColor = ResourcesCompat.getColor(getContext().getResources(), typedValue
                    .resourceId, getCurrentContextTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultColor;
    }

    /**
     * 根据给定的attrId获取Color颜色值
     *
     * @param attrId       资源属性对应的id
     * @param defaultColor 默认color
     */
    public int getColorByAttr(int attrId, int defaultColor) {
        return innerGetColorByAttr(attrId, defaultColor);
    }

    /**
     * 根据给定的attrId获取Color颜色值
     *
     * @param attrId 资源属性对应的id
     */
    public int getColorByAttr(int attrId) {
        return innerGetColorByAttr(attrId, DEFAULT_COLOR);
    }

    /**
     * 根据给定的attrId获取Drawable对象
     *
     * @param attrId          资源属性对应的id
     * @param defaultDrawable 默认drawable
     */
    private Drawable innerGetDrawableByAttr(int attrId, Drawable defaultDrawable) {
        if (!isCurrentContextValid())
            return defaultDrawable;
        TypedValue typedValue = obtainTypedValue(attrId);
        if (typedValue == null)
            return defaultDrawable;
        try {
            defaultDrawable = ResourcesCompat.getDrawable(getContext().getResources(), typedValue
                    .resourceId, getCurrentContextTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultDrawable;
    }

    /**
     * 根据给定的attrId获取Drawable对象
     *
     * @param attrId          资源属性对应的id
     * @param defaultDrawable 默认drawable
     */
    public Drawable getDrawableByAttr(int attrId, Drawable defaultDrawable) {
        return innerGetDrawableByAttr(attrId, defaultDrawable);
    }

    /**
     * 根据给定的attrId获取Drawable对象
     *
     * @param attrId 资源属性对应的id
     */
    public Drawable getDrawableByAttr(int attrId) {
        return getDrawableByAttr(attrId, null);
    }

    /**
     * 根据给定的attrId获取ColorStateList对象
     *
     * @param attrId                资源属性对应的id
     * @param defaultColorStateList ColorStateList
     */
    private ColorStateList innerGetColorStateListByAttr(int attrId, ColorStateList
            defaultColorStateList) {
        if (!isCurrentContextValid())
            return defaultColorStateList;
        TypedValue typedValue = obtainTypedValue(attrId);
        if (typedValue == null)
            return defaultColorStateList;
        try {
            defaultColorStateList = ResourcesCompat.getColorStateList(getContext().getResources()
                    , typedValue.resourceId, getCurrentContextTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultColorStateList;
    }

    /**
     * 根据给定的attrId获取ColorStateList对象
     *
     * @param attrId                资源属性对应的id
     * @param defaultColorStateList
     */
    public ColorStateList getColorStateListByAttr(int attrId, ColorStateList
            defaultColorStateList) {
        return innerGetColorStateListByAttr(attrId, defaultColorStateList);
    }

    /**
     * 根据给定的attrId获取ColorStateList对象
     *
     * @param attrId 资源属性对应的id
     */
    public ColorStateList getColorStateListByAttr(int attrId) {
        return innerGetColorStateListByAttr(attrId, null);
    }

    /**
     * 根据给定的attrId获取float的值
     *
     * @param attrId       资源属性对应的id
     * @param defaultFloat 默认float值
     */
    private float innerGetFloatByAttrId(int attrId, float defaultFloat) {
        if (!isCurrentContextValid())
            return defaultFloat;
        TypedValue typedValue = obtainTypedValue(attrId);
        if (typedValue == null)
            return defaultFloat;
        try {
            defaultFloat = typedValue.getFloat();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultFloat;
    }

    /**
     * 根据给定的attrId获取float的值
     *
     * @param attrId       资源属性对应的id
     * @param defaultFloat 默认float值
     */
    public float getFloatByAttrId(int attrId, float defaultFloat) {
        return innerGetFloatByAttrId(attrId, defaultFloat);
    }

    /**
     * 根据给定的attrId获取float的值
     *
     * @param attrId 资源属性对应的id
     */
    public float getFloatByAttrId(int attrId) {
        return getFloatByAttrId(attrId, DEFAULT_FLOAT_VALUE);
    }

    /**
     * 根据给定的attrId获取dimen的值
     *
     * @param attrId       资源属性对应的id
     * @param defaultFloat 默认尺寸值
     */
    private float innerDimensionByAttrId(int attrId, float defaultFloat) {
        if (!isCurrentContextValid())
            return defaultFloat;
        TypedValue typedValue = obtainTypedValue(attrId);
        if (typedValue == null)
            return defaultFloat;
        try {
            defaultFloat = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultFloat;
    }

    /**
     * 根据给定的attrId获取dimen的值
     *
     * @param attrId       资源属性对应的id
     * @param defaultFloat 默认尺寸值
     */
    public float getDimenByAttrId(int attrId, float defaultFloat) {
        return innerDimensionByAttrId(attrId, defaultFloat);
    }

    /**
     * 根据给定的attrId获取dimen的值
     *
     * @param attrId 资源属性对应的id
     */
    public float getDimenByAttrId(int attrId) {
        return getDimenByAttrId(attrId, DEFAULT_FLOAT_VALUE);
    }

    /**
     * 制作着色Drawable
     *
     * @param drawable       需要进行着色的Drawable对象
     * @param colorStateList 需要着色的颜色值
     * @return Drawable
     */
    private Drawable innerCreateTintDrawable(@NonNull Drawable drawable, ColorStateList
            colorStateList) {
        Drawable tintDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(tintDrawable, colorStateList);
        return tintDrawable;
    }

    /**
     * 制作着色Drawable
     *
     * @param drawable       需要进行着色的Drawable对象
     * @param colorStateList 需要着色的颜色值
     */
    public Drawable createTintDrawable(@NonNull Drawable drawable, ColorStateList colorStateList) {
        return innerCreateTintDrawable(drawable, colorStateList);
    }

    /**
     * 设置View背景
     *
     * @param view   要设置背景的view
     * @param attrId 资源属性对应的id
     */
    public void setBackgroundResourceByAttr(@NonNull View view, int attrId) {
        int identifier = getIdentifierByAttrId(attrId);
        if (identifier == 0)
            return;
        view.setBackgroundResource(identifier);
    }

    /**
     * 为ImageView设置attrId对应的资源作为图片资源
     *
     * @param imageView 要设置resource的imageview
     * @param attrId    资源属性对应的id
     */
    public void setImageResourceByAttr(@NonNull ImageView imageView, int attrId) {
        int identifier = getIdentifierByAttrId(attrId);
        if (identifier == 0)
            return;
        imageView.setImageResource(identifier);
    }

    /**
     * 为View设置指定Drawable作为Background
     *
     * @param view           要设置drawable的view
     * @param drawable       drawable
     * @param colorStateList 需要着色的颜色值
     */
    public void setTintBackground(@NonNull View view, @NonNull Drawable drawable, ColorStateList
            colorStateList) {
        Drawable tintedDrawable = createTintDrawable(drawable, colorStateList);
        if (tintedDrawable == null)
            return;
        view.setBackgroundDrawable(tintedDrawable);
    }

    /**
     * 为View设置指定着色的Drawable作为Background
     *
     * @param view                  要设置drawable的view
     * @param attrId                资源属性对应的id
     * @param drawable              drawable
     * @param defaultColorStateList 默认着色的颜色值
     */
    public void setTintBackgroundByAttr(@NonNull View view, @NonNull Drawable drawable, int
            attrId, ColorStateList defaultColorStateList) {
        ColorStateList tintColor = innerGetColorStateListByAttr(attrId, defaultColorStateList);
        setTintBackground(view, drawable, tintColor);
    }

    /**
     * 为View设置指定着色的Drawable作为Background
     *
     * @param view     要设置drawable的view
     * @param attrId   资源属性对应的id
     * @param drawable drawable
     */
    public void setTintBackgroundByAttr(@NonNull View view, @NonNull Drawable drawable, int
            attrId) {
        setTintBackgroundByAttr(view, drawable, attrId, null);
    }

    /**
     * 直接根据View当前Background设置着色Drawable并重新设置背景
     *
     * @param view           View
     * @param colorStateList 着色color
     */
    public void tintBackground(@NonNull View view, ColorStateList colorStateList) {
        Drawable backgroundDrawable = view.getBackground();
        if (backgroundDrawable == null)
            return;
        Drawable tintedDrawable = createTintDrawable(backgroundDrawable, colorStateList);
        if (tintedDrawable == null)
            return;
        view.setBackgroundDrawable(tintedDrawable);
    }

    /**
     * 直接根据View当前Background设置着色Drawable并重新设置背景
     *
     * @param view                  要设置drawable的view
     * @param attrId                资源属性对应的id
     * @param defaultColorStateList 默认着色color
     */
    public void tintBackgroundByAttr(@NonNull View view, int attrId, ColorStateList
            defaultColorStateList) {
        ColorStateList colorStateList = innerGetColorStateListByAttr(attrId, defaultColorStateList);
        tintBackground(view, colorStateList);
    }

    /**
     * 直接根据View当前Background设置着色Drawable并重新设置背景
     *
     * @param view   要设置drawable的view
     * @param attrId 资源属性对应的id
     */
    public void tintBackgroundByAttr(@NonNull View view, int attrId) {
        tintBackgroundByAttr(view, attrId, null);
    }

    /**
     * 为指定ImageView设置指定的着色Drawable
     *
     * @param imageView      要设置着色drawable的imageview
     * @param drawable       drawable
     * @param colorStateList 着色color
     */
    public void setTintImageDrawable(@NonNull ImageView imageView, @NonNull Drawable drawable,
                                     ColorStateList colorStateList) {
        Drawable tintedDrawable = createTintDrawable(drawable, colorStateList);
        if (tintedDrawable == null)
            return;
        imageView.setImageDrawable(tintedDrawable);
    }

    /**
     * 为指定ImageView设置指定的着色Drawable
     *
     * @param imageView             要设置着色drawable的imageview
     * @param drawable              drawable
     * @param attrId                资源属性对应的id
     * @param defaultColorStateList 默认着色color
     */
    public void setTintImageDrawableByAttr(@NonNull ImageView imageView, @NonNull Drawable
            drawable, int attrId, ColorStateList defaultColorStateList) {
        ColorStateList colorStateList = innerGetColorStateListByAttr(attrId, defaultColorStateList);
        setTintImageDrawable(imageView, drawable, colorStateList);
    }

    /**
     * 为指定ImageView设置指定的着色Drawable
     *
     * @param imageView 要设置着色drawable的imageview
     * @param drawable  drawable
     * @param attrId    资源属性对应的id
     */
    public void setTintImageDrawableByAttr(@NonNull ImageView imageView, @NonNull Drawable
            drawable, int attrId) {
        setTintImageDrawableByAttr(imageView, drawable, attrId, null);
    }

    /**
     * 设置ImageView当前ImageDrawable着色并重新设置ImageDrawable
     *
     * @param imageView      imageview
     * @param colorStateList 着色color
     */
    public void tintImageDrawable(@NonNull ImageView imageView, ColorStateList colorStateList) {
        Drawable originDrawable = imageView.getDrawable();
        if (originDrawable == null)
            return;
        Drawable tintedDrawable = createTintDrawable(originDrawable, colorStateList);
        if (tintedDrawable == null)
            return;
        imageView.setImageDrawable(tintedDrawable);
    }

    /**
     * 设置ImageView当前ImageDrawable着色并重新设置ImageDrawable
     *
     * @param imageView             imageview
     * @param attrId                资源属性对应的id
     * @param defaultColorStateList 默认着色color
     */
    public void tintImageDrawableByAttr(@NonNull ImageView imageView, int attrId, ColorStateList
            defaultColorStateList) {
        ColorStateList colorStateList = innerGetColorStateListByAttr(attrId, defaultColorStateList);
        tintImageDrawable(imageView, colorStateList);
    }

    /**
     * 直接根据ImageView当前ImageDrawable设置着色Drawable并重新设置ImageDrawable
     *
     * @param imageView imageview
     * @param attrId    资源属性对应的id
     */
    public void tintImageDrawableByAttr(@NonNull ImageView imageView, int attrId) {
        tintImageDrawableByAttr(imageView, attrId, null);
    }

    /**
     * 设置View的Alpha透明度
     *
     * @param view         view
     * @param attrId       资源属性对应的id
     * @param defaultFloat 默认值
     */
    public void setAlphaByAttr(@NonNull View view, int attrId, float defaultFloat) {
        float alpha = innerGetFloatByAttrId(attrId, defaultFloat);
        view.setAlpha(alpha);
    }

    /**
     * 设置View的Alpha透明度
     *
     * @param view   view
     * @param attrId 资源属性对应的id
     */
    public void setAlphaByAttr(@NonNull View view, int attrId) {
        setAlphaByAttr(view, attrId, DEFAULT_FLOAT_VALUE);
    }

    /**
     * 设置背景颜色值
     *
     * @param view         view
     * @param attrId       资源属性对应的id
     * @param defaultColor 默认color
     */
    public void setBackgroundColorByAttr(@NonNull View view, int attrId, int defaultColor) {
        int backgroundColor = innerGetColorByAttr(attrId, defaultColor);
        view.setBackgroundColor(backgroundColor);
    }

    /**
     * 设置背景颜色值
     *
     * @param view   view
     * @param attrId 资源属性对应的id
     */
    public void setBackgroundColorByAttr(@NonNull View view, int attrId) {
        setBackgroundColorByAttr(view, attrId, DEFAULT_BACKGROUND_COLOR);
    }

    /**
     * 设置textView字体颜色值
     *
     * @param textView              textview
     * @param attrId                资源属性对应的id
     * @param defaultColorStateList 默认着色color
     */
    public void setTextColorByAttr(@NonNull TextView textView, int attrId, ColorStateList
            defaultColorStateList) {
        ColorStateList colorStateList = innerGetColorStateListByAttr(attrId, defaultColorStateList);
        textView.setTextColor(colorStateList);
    }

    /**
     * 设置textView字体颜色值
     *
     * @param textView textview
     * @param attrId   资源属性对应的id
     */
    public void setTextColorByAttr(@NonNull TextView textView, int attrId) {
        setTextColorByAttr(textView, attrId, null);
    }

    /**
     * 设置textView提示颜色值
     *
     * @param textView              textview
     * @param attrId                资源属性对应的id
     * @param defaultColorStateList 默认着色color
     */
    public void setHintTextColorByAttr(@NonNull TextView textView, int attrId, ColorStateList
            defaultColorStateList) {
        ColorStateList colorStateList = innerGetColorStateListByAttr(attrId, defaultColorStateList);
        textView.setHintTextColor(colorStateList);
    }

    /**
     * 设置textView提示颜色值
     *
     * @param textView textview
     * @param attrId   资源属性对应的id
     */
    public void setHintTextColorByAttr(@NonNull TextView textView, int attrId) {
        setHintTextColorByAttr(textView, attrId, null);
    }

    /**
     * 设置NavigationView item字体颜色值
     *
     * @param nv     NavigationView
     * @param attrId 资源属性对应的id
     */
    public void setNavigationViewTextColorByAttr(@NonNull NavigationView nv, int attrId) {
        ColorStateList colorStateList = innerGetColorStateListByAttr(attrId, null);
        nv.setItemTextColor(colorStateList);
    }

    /**
     * 设置NavigationView item icon颜色值
     *
     * @param nv     NavigationView
     * @param attrId 资源属性对应的id
     */
    public void setNavigationViewIconColorByAttr(@NonNull NavigationView nv, int attrId) {
        ColorStateList colorStateList = innerGetColorStateListByAttr(attrId, null);
        nv.setItemIconTintList(colorStateList);
    }
}