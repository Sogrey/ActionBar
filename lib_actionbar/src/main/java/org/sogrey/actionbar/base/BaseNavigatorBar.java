package org.sogrey.actionbar.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Sogrey on 2017/3/12.
 */

public abstract class BaseNavigatorBar extends LinearLayout {
    protected static final int[] mVisibility = {View.VISIBLE, View.INVISIBLE, View.GONE};

    protected int mTxtTitleColor = Color.parseColor("#ffffff");
    protected int mTxtColor = mTxtTitleColor;
    protected int mActionBarBackground = Color.parseColor("#8194AA");
    protected int mTxtTitleSize = 18;
    protected int mTxtSize = 15;

    protected View mViewRoot;

    public BaseNavigatorBar(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public BaseNavigatorBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    @TargetApi(11)
    public BaseNavigatorBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public BaseNavigatorBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 初始化方法，子类可重写此方法。
     *
     * @param context      视图正在运行的上下文，通过它可以访问当前主题，资源等
     * @param attrs        扩充视图的XML标记的属性
     * @param defStyleAttr 提供视图的默认值的样式资源的引用
     * @param defStyleRes  样式资源
     */
    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setOrientation(LinearLayout.HORIZONTAL);
        initViews(context);
        initAttributes(context, attrs, defStyleAttr);
    }

    /**
     * 初始化视图
     *
     * @param context 上下文
     */
    protected abstract void initViews(Context context);

    /**
     * 获取xml文件里配置的自定义属性
     *
     * @param context      上下文
     * @param attrs        xml里自定义属性
     * @param defStyleAttr .
     */
    protected abstract void initAttributes(Context context, AttributeSet attrs, int defStyleAttr);

    /**
     * 获取根布局视图view
     *
     * @return 根布局视图
     */
    protected View getContentView() {
        return mViewRoot;
    }

    /**
     * 设置内容视图
     *
     * @param layoutId 布局资源ID
     */
    protected void setContentView(int layoutId) {
        mViewRoot = LayoutInflater.from(getContext()).inflate(layoutId, this, true);
    }


}
