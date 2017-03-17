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

import org.sogrey.actionbar.Views.SearchEditText;
import org.sogrey.actionbar.base.BaseNavigatorBar;

/**
 * 自定义搜索栏
 * Created by Sogrey on 2017/3/14.
 */

public class CustomSearchBar extends BaseNavigatorBar {
    private ImageView mImgLeft, mImgRight;
    private TextView mTxtLeft, mTxtRight;
    private LinearLayout mLytParentLeft, mLytParentRight;
    private SearchEditText mEdtCenter;

    public CustomSearchBar(Context context) {
        super(context);
    }

    public CustomSearchBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSearchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews(Context context) {
        setContentView(R.layout.lyt_custom_search_bar);
        mImgLeft = (ImageView) mViewRoot.findViewById(R.id.img_custom_action_bar_left);
        mImgRight = (ImageView) mViewRoot.findViewById(R.id.img_custom_action_bar_right);
        mTxtLeft = (TextView) mViewRoot.findViewById(R.id.txt_custom_action_bar_left);
        mTxtRight = (TextView) mViewRoot.findViewById(R.id.txt_custom_action_bar_right);
        mEdtCenter = (SearchEditText) mViewRoot.findViewById(R.id.edt_custom_search_bar_center);
        mLytParentLeft = (LinearLayout) mViewRoot.findViewById(R.id.lyt_custom_action_bar_left);
        mLytParentRight = (LinearLayout) mViewRoot.findViewById(R.id.lyt_custom_action_bar_right);
    }

    @Override
    protected void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomActionBar, defStyleAttr, 0);
        int actionBarBackground = typedArray.getColor(R.styleable.CustomActionBar_titleBarBackground, mActionBarBackground);
        int midSearchTextBackgroundResource = typedArray.getInt(R.styleable.CustomActionBar_midSearchTextBackgroundResource, R.drawable.bg_search_edt);
        Drawable leftImageDrawable = typedArray.getDrawable(R.styleable.CustomActionBar_leftImage);
        Drawable rightImageDrawable = typedArray.getDrawable(R.styleable.CustomActionBar_rightImage);
        Drawable drawableSearch = typedArray.getDrawable(R.styleable.CustomActionBar_drawableSearch);
        Drawable drawableDelete = typedArray.getDrawable(R.styleable.CustomActionBar_drawableDelete);
        int leftImageVisibility = typedArray.getInt(R.styleable.CustomActionBar_leftImageVisiable, View.VISIBLE);
        int rightImageVisibility = typedArray.getInt(R.styleable.CustomActionBar_rightImageVisible, View.VISIBLE);
        int midSearchTextVisibility = typedArray.getInt(R.styleable.CustomActionBar_midSearchTextVisiable, View.VISIBLE);
        int leftTextVisibility = typedArray.getInt(R.styleable.CustomActionBar_leftTextVisibale, View.VISIBLE);
        int rightTextVisibility = typedArray.getInt(R.styleable.CustomActionBar_rightTextVisible, View.VISIBLE);
        int midSearchTextColor = typedArray.getColor(R.styleable.CustomActionBar_midSearchTextFontColor, mTxtTitleColor);
        int midSearchHintTextFontColor = typedArray.getColor(R.styleable.CustomActionBar_midSearchHintTextFontColor, mTxtTitleColor);
        int leftTextColor = typedArray.getColor(R.styleable.CustomActionBar_leftTextColor, mTxtColor);
        int rightTextColor = typedArray.getColor(R.styleable.CustomActionBar_rightTextColor, mTxtColor);
        float midSearchTextSize = typedArray.getDimension(R.styleable.CustomActionBar_midSearchTextFontSize, mTxtTitleSize);
        float leftTextSize = typedArray.getDimension(R.styleable.CustomActionBar_leftTextFontSize, mTxtSize);
        float rightTextSize = typedArray.getDimension(R.styleable.CustomActionBar_rightTextFontSize, mTxtSize);
        String midSearchText = typedArray.getString(R.styleable.CustomActionBar_midSearchText);
        String midSearchHintText = typedArray.getString(R.styleable.CustomActionBar_midSearchHintText);
        String leftText = typedArray.getString(R.styleable.CustomActionBar_leftText);
        String rightText = typedArray.getString(R.styleable.CustomActionBar_rightText);

        setBackgroundColor(actionBarBackground);

        mImgLeft.setImageDrawable(leftImageDrawable);
        mImgRight.setImageDrawable(rightImageDrawable);
        mImgLeft.setVisibility(mVisibility[leftImageVisibility]);
        mImgRight.setVisibility(mVisibility[rightImageVisibility]);
        mEdtCenter.setVisibility(mVisibility[midSearchTextVisibility]);
        mTxtLeft.setVisibility(mVisibility[leftTextVisibility]);
        mTxtRight.setVisibility(mVisibility[rightTextVisibility]);
        mEdtCenter.setTextColor(midSearchTextColor);
        mTxtLeft.setTextColor(leftTextColor);
        mTxtRight.setTextColor(rightTextColor);
        mEdtCenter.setText(midSearchText);
        mTxtLeft.setText(leftText);
        mTxtRight.setText(rightText);
        mEdtCenter.setTextSize(TypedValue.COMPLEX_UNIT_SP, midSearchTextSize);
        mTxtLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftTextSize);
        mTxtRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightTextSize);

        mEdtCenter.setHint(midSearchHintText);
        mEdtCenter.setHintTextColor(midSearchHintTextFontColor);
        mEdtCenter.setBackgroundResource(midSearchTextBackgroundResource);
        setDrawableDelTmp(drawableDelete);
        setDrawableLeft(drawableSearch);

        typedArray.recycle();

        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    //----------------点击事件-----------------

    /**
     * 左上角ImageView点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setOnLeftImageClickListner(OnClickListener l) {
        mImgLeft.setOnClickListener(l);
        return this;
    }

    /**
     * 右上角ImageView点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setOnRightImageClickListner(OnClickListener l) {
        mImgRight.setOnClickListener(l);
        return this;
    }

    /**
     * 左上角TextView点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setOnLeftTextClickListner(OnClickListener l) {
        mTxtLeft.setOnClickListener(l);
        return this;
    }

    /**
     * 右上角TextView点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setOnRightTextClickListner(OnClickListener l) {
        mTxtRight.setOnClickListener(l);
        return this;
    }

    /**
     * 左上角父布局点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setOnLeftParentLayoutClickListner(OnClickListener l) {
        mLytParentLeft.setOnClickListener(l);
        return this;
    }

    /**
     * 右上角父布局点击事件
     *
     * @param l 点击事件
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setOnRightParentLayoutClickListner(OnClickListener l) {
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
    public CustomSearchBar setLeftImageVisibility(int visibility) {
        mImgLeft.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右上角Image显示与隐藏。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setRightImageVisibility(int visibility) {
        mImgRight.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置搜索输入框显示与隐藏。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setTitleVisibility(int visibility) {
        mEdtCenter.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置左上角工具条父容器显示与隐藏，其显示或隐藏会影响到其子控件的可视与否。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setLeftParentLayoutVisibility(int visibility) {
        mLytParentLeft.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右上角工具条父容器显示与隐藏，其显示或隐藏会影响到其子控件的可视与否。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setRightParentLayoutVisibility(int visibility) {
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
    public CustomSearchBar setLeftImageBitmap(Bitmap bm) {
        mImgLeft.setImageBitmap(bm);
        return this;
    }

    /**
     * 设置左上角图标显示资源
     *
     * @param drawable Drawable资源
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setLeftImageDrawable(Drawable drawable) {
        mImgLeft.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置左上角图标显示资源
     *
     * @param resId 资源文件ID
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setLeftImageResource(int resId) {
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
    public CustomSearchBar setRightImageBitmap(Bitmap bm) {
        mImgRight.setImageBitmap(bm);
        return this;
    }

    /**
     * 设置右上角图标显示资源
     *
     * @param drawable Drawable资源
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setRightImageDrawable(Drawable drawable) {
        mImgRight.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置右上角图标显示资源
     *
     * @param resId 资源文件ID
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setRightImageResource(int resId) {
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
    public CustomSearchBar setLeftText(int resId) {
        mTxtLeft.setText(resId);
        return this;
    }

    /**
     * 设置左上角TextView文本
     *
     * @param title 文本字符串
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setLeftText(CharSequence title) {
        mTxtLeft.setText(title);
        return this;
    }

    /**
     * 设置左上角TextView文本颜色
     *
     * @param color 颜色值资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setLeftTextColor(int color) {
        mTxtLeft.setTextColor(color);
        return this;
    }

    /**
     * 设置左上角TextView文本字体大小
     *
     * @param textSize 标题文本字体大小，单位sp
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setLeftTextSize(float textSize) {
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
    public CustomSearchBar setRightText(int resId) {
        mTxtRight.setText(resId);
        return this;
    }

    /**
     * 设置右上角TextView文本
     *
     * @param title 标题文本
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setRightText(CharSequence title) {
        mTxtRight.setText(title);
        return this;
    }

    /**
     * 设置右上角TextView文本颜色
     *
     * @param color 颜色值资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setRightTextColor(int color) {
        mTxtRight.setTextColor(color);
        return this;
    }

    /**
     * 设置右上角TextView文本字体大小
     *
     * @param textSize 标题文本字体大小，单位sp
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setRightTextSize(float textSize) {
        mTxtRight.setTextSize(textSize);
        return this;
    }
    //------------------设置搜索框内容和提示-------------------

    /**
     * 设置搜索输入框内容
     *
     * @param resId 搜索输入框文本资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setSearchText(int resId) {
        mEdtCenter.setText(resId);
        return this;
    }

    /**
     * 设置搜索输入框内容
     *
     * @param title 搜索输入框内容文本
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setSearchText(CharSequence title) {
        mEdtCenter.setText(title);
        return this;
    }

    /**
     * 设置搜索输入框内容文本颜色
     *
     * @param color 颜色值资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setSearchColor(int color) {
        mEdtCenter.setTextColor(color);
        return this;
    }

    /**
     * 设置搜索输入框内容字体大小
     *
     * @param textSize 标题文本字体大小，单位sp
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setSearchTextSize(float textSize) {
        mEdtCenter.setTextSize(textSize);
        return this;
    }

    /**
     * 设置搜索输入框提示内容
     *
     * @param resId 搜索输入框提示内容文本资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setSearchHintText(int resId) {
        mEdtCenter.setHint(resId);
        return this;
    }

    /**
     * 设置搜索输入框提示内容
     *
     * @param title 搜索输入框提示内容
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setSearchHintText(CharSequence title) {
        mEdtCenter.setHint(title);
        return this;
    }

    /**
     * 设置搜索输入框提示内容文本颜色
     *
     * @param color 颜色值资源ID
     * @return 返回当前实例便于链式操作
     */
    public CustomSearchBar setSearchHintTextColor(int color) {
        mEdtCenter.setHintTextColor(color);
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
     * 获取标题{@link SearchEditText}
     *
     * @return 标题{@link SearchEditText}
     */
    public SearchEditText getmEdtSearchCenter() {
        return mEdtCenter;
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

    /**
     * 设置删除按钮暂存资源
     *
     * @param drawableDelTmp 删除按钮暂存资源
     * @return 返回本实例便于链式操作
     */
    public CustomSearchBar setDrawableDelTmp(Drawable drawableDelTmp) {
        mEdtCenter.setDrawableDelTmp(drawableDelTmp);
        return this;
    }

    /**
     * 设置搜索图标资源
     *
     * @return 返回本实例便于链式操作
     */
    public CustomSearchBar setDrawableLeft(Drawable d) {
        mEdtCenter.setDrawableLeft(d);
        return this;
    }
}
