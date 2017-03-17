package org.sogrey.actionbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import org.sogrey.actionbar.base.BaseNavigatorBar;

/**
 * Created by Sogrey on 2017/3/14.
 */

public class CustomIosActionBar extends BaseNavigatorBar {
    public CustomIosActionBar(Context context) {
        super(context);
    }

    public CustomIosActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomIosActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomIosActionBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews(Context context) {

    }

    @Override
    protected void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

    }
}
