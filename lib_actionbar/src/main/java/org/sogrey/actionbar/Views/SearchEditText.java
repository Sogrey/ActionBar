package org.sogrey.actionbar.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.sogrey.actionbar.R;

/**
 * Created by Sogrey on 2017/03/14
 */
public class SearchEditText extends AppCompatEditText implements View.OnFocusChangeListener, View.OnKeyListener, TextWatcher {
    private final String TAG = SearchEditText.this.getClass().getSimpleName();

    /**
     * 搜索栏中图标的位置,可选值有：{@link SearchIconPosition#LEFT}(-1) and {@link SearchIconPosition#CENTER}(0),<s>{@link SearchIconPosition#RIGHT}(1)</s> was deprecated.
     */
    public enum SearchIconPosition {
        LEFT(-1), CENTER(0),
        /**
         * @deprecated
         */
        RIGHT(1);
        int value;

        SearchIconPosition(int value) {
            this.value = value;
        }
    }

    /**
     * 搜索栏中图标的位置
     */
    private SearchIconPosition mSearchIconPosition = SearchIconPosition.CENTER;
    /**
     * 搜索栏中图标的默认位置-居中
     */
    private SearchIconPosition mSearchIconPositionDefault = SearchIconPosition.CENTER;
    /**
     * 是否开启点击软键盘搜索
     */
    private boolean pressSearch = false;
    /**
     * 软键盘搜索键监听
     */
    private OnSearchClickListener listener;

    private Drawable drawableLeft, drawableDel, drawableDelTmp; // 搜索图标和删除按钮图标
    private int eventX, eventY; // 记录点击坐标
    private Rect rect; // 控件区域

    public void setOnSearchClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }

    public interface OnSearchClickListener {
        void onSearchClick(View view, String keyword);
    }

    public SearchEditText(Context context) {
        this(context, null);
        init();
    }


    public SearchEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnFocusChangeListener(this);
        setOnKeyListener(this);
        addTextChangedListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mSearchIconPosition) {
            case LEFT: {//居左
                if (length() < 1) {
                    drawableDel = null;
                }
                this.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, drawableDel, null);
            }
            break;
            case CENTER: {//居中
                if (drawableLeft == null) drawableLeft =getDrawableLeft();
                float textWidth = getPaint().measureText(getHint().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth - getPaddingLeft() - getPaddingRight()) / 2, 0);
            }
            break;
            case RIGHT: {
                /*已过时*/
            }
            break;
        }
        super.onDraw(canvas);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // 被点击时，恢复默认样式
        if (!pressSearch && TextUtils.isEmpty(getText().toString())) {
            mSearchIconPosition = hasFocus ? mSearchIconPositionDefault : SearchIconPosition.LEFT;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        pressSearch = (keyCode == KeyEvent.KEYCODE_ENTER);
        if (pressSearch && listener != null && event.getAction() == KeyEvent.ACTION_DOWN) {
            /*隐藏软键盘*/
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
            listener.onSearchClick(v, getText().toString());
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 清空edit内容
        if (drawableDel != null && event.getAction() == MotionEvent.ACTION_UP) {
            eventX = (int) event.getRawX();
            eventY = (int) event.getRawY();
            if (rect == null) rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - drawableDel.getIntrinsicWidth();
            if (rect.contains(eventX, eventY)) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }


    @Override
    public void afterTextChanged(Editable arg0) {
        if (this.length() < 1) {
            setDrawableDel(null);
        } else {
            setDrawableDel(getDrawableDelTmp());
        }
    }


    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                              int arg3) {
    }

    /**
     * 设置删除按钮资源
     *
     * @param drawableDel 删除按钮资源
     * @return 返回本实例便于链式操作
     */
    public SearchEditText setDrawableDel(Drawable drawableDel) {
        this.drawableDel = drawableDel;
        return this;
    }

    /**
     * 设置删除按钮暂存资源
     *
     * @param drawableDelTmp 删除按钮暂存资源
     * @return 返回本实例便于链式操作
     */
    public SearchEditText setDrawableDelTmp(Drawable drawableDelTmp) {
        this.drawableDelTmp = drawableDelTmp;
        invalidate();
        return this;
    }

    /**
     * 获取删除按钮暂存资源
     *
     * @return 删除按钮暂存资源
     */
    public Drawable getDrawableDelTmp() {
        return drawableDelTmp == null ? this.getResources().getDrawable(R.drawable.delete) : drawableDelTmp;
    }

    /**
     * 获取搜索图标资源
     * @return 搜索图标资源
     */
    public Drawable getDrawableLeft() {
        return drawableLeft == null ? this.getResources().getDrawable(R.drawable.search) : drawableLeft;
    }

    /**
     * 设置搜索图标资源
     * @return 返回本实例便于链式操作
     */
    public SearchEditText setDrawableLeft(Drawable d) {
        this.drawableLeft =d;
        invalidate();
        return this;
    }

}