package com.inesv.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

/**
 * 底部按钮布局
 */
public class TabBarLayout extends RadioGroup {
    private OnTabItemClickListener tabItemClickListener;
    /**
     * 当前视图位置
     */
    private int position = 0;

    public TabBarLayout(Context context) {
        super(context);
    }

    public TabBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(LinearLayout.HORIZONTAL);
    }

    public TabBarLayout addButton(CompoundButton compoundButton) {
        this.addView(compoundButton);
        //默认设置第一个选中
        ((CompoundButton) this.getChildAt(0)).setChecked(true);
        compoundButton.setTag(position);
        compoundButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabItemClickListener != null) {
                    tabItemClickListener.onItemClick((Integer) v.getTag());
                }
            }
        });
        position++;
        return this;
    }

    public void setTabItemClickListener(OnTabItemClickListener tabItemClickListener) {
        this.tabItemClickListener = tabItemClickListener;
    }

    public interface OnTabItemClickListener {
        void onItemClick(int position);
    }
}
