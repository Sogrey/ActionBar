package org.sogrey.actionbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.sogrey.actionbar.base.BaseNavigatorBar;

/**
 * Created by Sogrey on 2017/3/12.
 */

public class CustomActionBar extends BaseNavigatorBar {
    private static final int[] mVisibility = {View.VISIBLE, View.INVISIBLE, View.GONE};
    private ImageView mImgLeft, mImgRight;
    private TextView mTxtTitle;
    private LinearLayout mLytToolsParent;

    private int mTxtTitleColor = Color.parseColor("#ffffff");
    private int mActionBarBackground = Color.parseColor("#8194AA");
    private int mTxtTitleSize = 18;

    public CustomActionBar(Context context) {
        super(context);
    }

    public CustomActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomActionBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews(Context context) {
        setContentView(R.layout.lyt_custom_action_bar);
        mImgLeft = (ImageView) mViewRoot.findViewById(R.id.img_custom_action_bar_left);
        mImgRight = (ImageView) mViewRoot.findViewById(R.id.img_custom_action_bar_right);
        mTxtTitle = (TextView) mViewRoot.findViewById(R.id.tv_custom_action_bar_title);
        mLytToolsParent = (LinearLayout) mViewRoot.findViewById(R.id.lyt_custom_action_bar_tools);
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
        int midTitleColor = typedArray.getColor(R.styleable.CustomActionBar_midTextFontColor, mTxtTitleColor);
        float midTitleSize = typedArray.getDimension(R.styleable.CustomActionBar_midTextFontSize, mTxtTitleSize);
        String midTitleText = typedArray.getString(R.styleable.CustomActionBar_midText);

        setBackgroundColor(actionBarBackground);

        mImgLeft.setImageDrawable(leftImageDrawable);
        mImgRight.setImageDrawable(rightImageDrawable);
        mImgLeft.setVisibility(mVisibility[leftImageVisibility]);
        mImgRight.setVisibility(mVisibility[rightImageVisibility]);
        mTxtTitle.setVisibility(mVisibility[midTitleVisibility]);
        mTxtTitle.setTextColor(midTitleColor);
        mTxtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, midTitleSize);
        mTxtTitle.setText(midTitleText);

        typedArray.recycle();
    }

    /**
     * 获取左上角ImageView控件
     *
     * @return 左上角ImageView控件
     */
    public ImageView getmImgLeft() {
        return mImgLeft;
    }

    /**
     * 获取右上角ImageView控件
     *
     * @return 右上角ImageView控件
     */
    public ImageView getmImgRight() {
        return mImgRight;
    }

    /**
     * 获取标题文本控件
     *
     * @return 标题文本控件TextView
     */
    public TextView getmTxtTitle() {
        return mTxtTitle;
    }

    /**
     * 获取右侧工具图标父容器，可以移除其中子空间添加自己需要的一个或多个工具按钮图标
     *
     * @return 右侧工具图标父容器LinearLayout
     */
    public LinearLayout getmLytToolsParent() {
        return mLytToolsParent;
    }

    //------------------点击事件-----------------

    /**
     * 左上角ImageView点击事件
     *
     * @param l 点击事件
     * @return
     */
    public CustomActionBar setOnLeftImageClickListner(OnClickListener l) {
        mImgLeft.setOnClickListener(l);
        return this;
    }

    /**
     * 右上角ImageView点击事件
     *
     * @param l 点击事件
     * @return
     */
    public CustomActionBar setOnRightImageClickListner(OnClickListener l) {
        mImgRight.setOnClickListener(l);
        return this;
    }
    //--------------------显示隐藏-------------------

    /**
     * 设置左上角Image显示与隐藏。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return
     */
    public CustomActionBar setLeftImageVisibility(int visibility) {
        mImgLeft.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右上角Image显示与隐藏。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return
     */
    public CustomActionBar setRightImageVisibility(int visibility) {
        mImgRight.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置标题显示与隐藏。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return
     */
    public CustomActionBar setTitleVisibility(int visibility) {
        mTxtTitle.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右上角工具条父容器显示与隐藏，其显示或隐藏会影响到其子控件的可视与否。
     *
     * @param visibility 可选值：{@link android.view.View#VISIBLE},{@link android.view.View#GONE},{@link android.view.View#INVISIBLE}
     * @return
     */
    public CustomActionBar setLytToolsParentVisibility(int visibility) {
        mLytToolsParent.setVisibility(View.VISIBLE);
        return this;
    }

    //--------------------Left Image Resource-------------------

    /**
     * 设置左上角图标显示资源
     *
     * @param bm Bitmap资源
     */
    public CustomActionBar setLeftImageBitmap(Bitmap bm) {
        mImgLeft.setImageBitmap(bm);
        return this;
    }

    /**
     * 设置左上角图标显示资源
     *
     * @param drawable Drawable资源
     */
    public CustomActionBar setLeftImageDrawable(Drawable drawable) {
        mImgLeft.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置左上角图标显示资源
     *
     * @param resId 资源文件ID
     */
    public CustomActionBar setLeftImageResource(int resId) {
        mImgLeft.setImageResource(resId);
        return this;
    }

    //--------------------Right Image Resource-------------------

    /**
     * 设置右上角图标显示资源
     *
     * @param bm Bitmap资源
     */
    public CustomActionBar setRightImageBitmap(Bitmap bm) {
        mImgRight.setImageBitmap(bm);
        return this;
    }

    /**
     * 设置右上角图标显示资源
     *
     * @param drawable Drawable资源
     */
    public CustomActionBar setRightImageDrawable(Drawable drawable) {
        mImgRight.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置右上角图标显示资源
     *
     * @param resId 资源文件ID
     */
    public CustomActionBar setRightImageResource(int resId) {
        mImgRight.setImageResource(resId);
        return this;
    }
}
