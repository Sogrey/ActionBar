package org.sogrey.actionbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sogrey.actionbar.base.BaseNavigatorBar;

/**
 * Created by Sogrey on 2017/3/12.
 */

public class CustomNavigatorBar extends BaseNavigatorBar {
    private ImageView mImgLeft, mImgRight;
    private TextView mTxtTitle, mTxtLeft, mTxtRight;
    private LinearLayout mLytParentLeft, mLytParentRight;

    public CustomNavigatorBar(Context context) {
        super(context);
    }

    public CustomNavigatorBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNavigatorBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomNavigatorBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews(Context context) {
        setContentView(R.layout.lyt_custom_navigator_bar);
        mImgLeft = (ImageView) mViewRoot.findViewById(R.id.img_custom_action_bar_left);
        mImgRight = (ImageView) mViewRoot.findViewById(R.id.img_custom_action_bar_right);
        mTxtLeft = (TextView) mViewRoot.findViewById(R.id.txt_custom_action_bar_left);
        mTxtRight = (TextView) mViewRoot.findViewById(R.id.txt_custom_action_bar_right);
        mTxtTitle = (TextView) mViewRoot.findViewById(R.id.tv_custom_action_bar_title);
        mLytParentLeft = (LinearLayout) mViewRoot.findViewById(R.id.lyt_custom_action_bar_left);
        mLytParentRight = (LinearLayout) mViewRoot.findViewById(R.id.lyt_custom_action_bar_right);
    }

    @Override
    protected void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomActionBar, defStyleAttr, 0);
        int actionBarBackground = typedArray.getColor(R.styleable.CustomActionBar_titleBarBackground, mActionBarBackground);
        Drawable leftImageDrawable = typedArray.getDrawable(R.styleable.CustomActionBar_leftImage);
        Drawable rightImageDrawable = typedArray.getDrawable(R.styleable.CustomActionBar_rightImage);
        int leftImageVisibility = typedArray.getInt(R.styleable.CustomActionBar_leftImageVisiable, View.VISIBLE);
        int rightImageVisibility = typedArray.getInt(R.styleable.CustomActionBar_rightImageVisible, View.VISIBLE);
        int midTitleVisibility = typedArray.getInt(R.styleable.CustomActionBar_midTextVisiable, View.VISIBLE);
        int leftTextVisibility = typedArray.getInt(R.styleable.CustomActionBar_leftTextVisibale, View.VISIBLE);
        int rightTextVisibility = typedArray.getInt(R.styleable.CustomActionBar_rightTextVisible, View.VISIBLE);
        int midTitleColor = typedArray.getColor(R.styleable.CustomActionBar_midTextFontColor, mTxtTitleColor);
        int leftTextColor = typedArray.getColor(R.styleable.CustomActionBar_leftTextColor, mTxtColor);
        int rightTextColor = typedArray.getColor(R.styleable.CustomActionBar_rightTextColor, mTxtColor);
        float midTitleSize = typedArray.getDimension(R.styleable.CustomActionBar_midTextFontSize, mTxtTitleSize);
        float leftTextSize = typedArray.getDimension(R.styleable.CustomActionBar_leftTextFontSize, mTxtSize);
        float rightTextSize = typedArray.getDimension(R.styleable.CustomActionBar_rightTextFontSize, mTxtSize);
        String midTitleText = typedArray.getString(R.styleable.CustomActionBar_midText);
        String leftText = typedArray.getString(R.styleable.CustomActionBar_leftText);
        String rightText = typedArray.getString(R.styleable.CustomActionBar_rightText);

        setBackgroundColor(actionBarBackground);

        mImgLeft.setImageDrawable(leftImageDrawable);
        mImgRight.setImageDrawable(rightImageDrawable);
        mImgLeft.setVisibility(mVisibility[leftImageVisibility]);
        mImgRight.setVisibility(mVisibility[rightImageVisibility]);
        mTxtTitle.setVisibility(mVisibility[midTitleVisibility]);
        mTxtLeft.setVisibility(mVisibility[leftTextVisibility]);
        mTxtRight.setVisibility(mVisibility[rightTextVisibility]);
        mTxtTitle.setTextColor(midTitleColor);
        mTxtLeft.setTextColor(leftTextColor);
        mTxtRight.setTextColor(rightTextColor);
        mTxtTitle.setText(midTitleText);
        mTxtLeft.setText(leftText);
        mTxtRight.setText(rightText);
        mTxtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, midTitleSize);
        mTxtLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftTextSize);
        mTxtRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightTextSize);

        typedArray.recycle();
    }

    //----------------点击事件-----------------

    /**
     * 左上角ImageView点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setOnLeftImageClickListner(OnClickListener l) {
        mImgLeft.setOnClickListener(l);
        return this;
    }

    /**
     * 右上角ImageView点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setOnRightImageClickListner(OnClickListener l) {
        mImgRight.setOnClickListener(l);
        return this;
    }

    /**
     * 左上角TextView点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setOnLeftTextClickListner(OnClickListener l) {
        mTxtLeft.setOnClickListener(l);
        return this;
    }

    /**
     * 右上角TextView点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setOnRightTextClickListner(OnClickListener l) {
        mTxtRight.setOnClickListener(l);
        return this;
    }

    /**
     * 左上角父布局点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setOnLeftParentLayoutClickListner(OnClickListener l) {
        mLytParentLeft.setOnClickListener(l);
        return this;
    }

    /**
     * 右上角父布局点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setOnRightParentLayoutClickListner(OnClickListener l) {
        mLytParentRight.setOnClickListener(l);
        return this;
    }
    //--------------------显示隐藏-------------------

    /**
     * 设置左上角Image显示与隐藏。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftImageVisibility(int visibility) {
        mImgLeft.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右上角Image显示与隐藏。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightImageVisibility(int visibility) {
        mImgRight.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置标题显示与隐藏。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setTitleVisibility(int visibility) {
        mTxtTitle.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置左上角工具条父容器显示与隐藏，其显示或隐藏会影响到其子控件的可视与否。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftParentLayoutVisibility(int visibility) {
        mLytParentLeft.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右上角工具条父容器显示与隐藏，其显示或隐藏会影响到其子控件的可视与否。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightParentLayoutVisibility(int visibility) {
        mLytParentRight.setVisibility(View.VISIBLE);
        return this;
    }
    //--------------------Left Image Resource-------------------

    /**
     * 设置左上角图标显示资源
     *
     * @param bm Bitmap资源
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftImageBitmap(Bitmap bm) {
        mImgLeft.setImageBitmap(bm);
        return this;
    }

    /**
     * 设置左上角图标显示资源
     *
     * @param drawable Drawable资源
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftImageDrawable(Drawable drawable) {
        mImgLeft.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置左上角图标显示资源
     *
     * @param resId 资源文件ID
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftImageResource(int resId) {
        mImgLeft.setImageResource(resId);
        return this;
    }

    //--------------------Right Image Resource-------------------

    /**
     * 设置右上角图标显示资源
     *
     * @param bm Bitmap资源
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightImageBitmap(Bitmap bm) {
        mImgRight.setImageBitmap(bm);
        return this;
    }

    /**
     * 设置右上角图标显示资源
     *
     * @param drawable Drawable资源
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightImageDrawable(Drawable drawable) {
        mImgRight.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置右上角图标显示资源
     *
     * @param resId 资源文件ID
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightImageResource(int resId) {
        mImgRight.setImageResource(resId);
        return this;
    }
    //---------------左上角TextView-----------

    /**
     * 设置左上角TextView文本
     *
     * @param resId 文本资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftText(int resId) {
        mTxtLeft.setText(resId);
        return this;
    }

    /**
     * 设置左上角TextView文本
     *
     * @param title 文本字符串
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftText(CharSequence title) {
        mTxtLeft.setText(title);
        return this;
    }

    /**
     * 设置左上角TextView文本颜色
     *
     * @param color 颜色值资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftTextColor(int color) {
        mTxtLeft.setTextColor(color);
        return this;
    }

    /**
     * 设置左上角TextView文本字体大小
     *
     * @param textSize 标题文本字体大小，单位sp
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setLeftTextSize(float textSize) {
        mTxtLeft.setTextSize(textSize);
        return this;
    }
    //---------------右上角TextView-----------

    /**
     * 设置右上角TextView文本
     *
     * @param resId 标题文本资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightText(int resId) {
        mTxtRight.setText(resId);
        return this;
    }

    /**
     * 设置右上角TextView文本
     *
     * @param title 标题文本
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightText(CharSequence title) {
        mTxtRight.setText(title);
        return this;
    }

    /**
     * 设置右上角TextView文本颜色
     *
     * @param color 颜色值资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightTextColor(int color) {
        mTxtRight.setTextColor(color);
        return this;
    }

    /**
     * 设置右上角TextView文本字体大小
     *
     * @param textSize 标题文本字体大小，单位sp
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setRightTextSize(float textSize) {
        mTxtRight.setTextSize(textSize);
        return this;
    }
    //------------------设置标题-------------------

    /**
     * 设置标题
     *
     * @param resId 标题文本资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setTitleText(int resId) {
        mTxtTitle.setText(resId);
        return this;
    }

    /**
     * 设置标题
     *
     * @param title 标题文本
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setTitleText(CharSequence title) {
        mTxtTitle.setText(title);
        return this;
    }

    /**
     * 设置标题颜色
     *
     * @param color 颜色值资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setTitleColor(int color) {
        mTxtTitle.setTextColor(color);
        return this;
    }

    /**
     * 设置标题字体大小
     *
     * @param textSize 标题文本字体大小，单位sp
     * @return 返回当前实例便于链式操作
     */
    public CustomNavigatorBar setTitleTextSize(float textSize) {
        mTxtTitle.setTextSize(textSize);
        return this;
    }

    //---------------- getter -----------------

    /**
     * 获取左边ImageView
     *
     * @return 左边ImageView
     */
    public ImageView getmImgLeft() {
        return mImgLeft;
    }

    /**
     * 获取右边ImageView
     *
     * @return 右边ImageView
     */
    public ImageView getmImgRight() {
        return mImgRight;
    }

    /**
     * 获取标题TextView
     *
     * @return 标题TextView
     */
    public TextView getmTxtTitle() {
        return mTxtTitle;
    }

    /**
     * 获取左侧TextView，一般是返回按钮
     *
     * @return 左侧TextView
     */
    public TextView getmTxtLeft() {
        return mTxtLeft;
    }

    /**
     * 获取右侧TextView，一般是提交、保存操作按钮
     *
     * @return 右侧TextView
     */
    public TextView getmTxtRight() {
        return mTxtRight;
    }

    /**
     * 获取左侧父布局
     *
     * @return 左侧父布局LinearLayout
     */
    public LinearLayout getmLytParentLeft() {
        return mLytParentLeft;
    }

    /**
     * 获取右侧父布局
     *
     * @return 右侧父布局LinearLayout
     */
    public LinearLayout getmLytParentRight() {
        return mLytParentRight;
    }
}
