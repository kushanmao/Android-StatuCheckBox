package com.jaiky.test.statucheckbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Author by Jaiky, Email jaikydota@163.com, Date on 5/21/2015.
 * PS: Not easy to write code, please indicate.
 */
public class ImageCheckBox extends ImageView {
    //状态，不能使用
    public static final int CHECK_STATE_DISABLED = 0;
    //为不选中状态
    public static final int CHECK_STATE_UNCHECKED = 1;
    //选中状态
    public static final int CHECK_STATE_CHECKED = 2;

    private int check_bkg_id;
    private int uncheck_bkg_id;
    private int disable_check_bkg_id;

    //当前状态
    private int checkState;

    public ImageCheckBox(Context context) {
        this(context, null);
    }

    public ImageCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ImageCheckBox(Context context, AttributeSet attrs,
                         int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray t = getContext().obtainStyledAttributes(attrs,
                R.styleable.ImageCheckBox);
        checkState = t.getInteger(R.styleable.ImageCheckBox_checked_state,
                CHECK_STATE_UNCHECKED);
        check_bkg_id = t.getResourceId(
                R.styleable.ImageCheckBox_checked, 0);
        uncheck_bkg_id = t.getResourceId(
                R.styleable.ImageCheckBox_unchecked, 0);
        disable_check_bkg_id = t.getResourceId(
                R.styleable.ImageCheckBox_checked_disabled, 0);

        setBkgByCheckState();

        //如果可以点击
        if (isClickable()) {
            setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    changeState();
                }
            });
        }

        t.recycle();
    }

    public boolean isCheck(){
        if (checkState == CHECK_STATE_DISABLED) {
            return false;
        }

        if (checkState == CHECK_STATE_CHECKED) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 设置选中状态
     * @param check
     */
    public void setCheck(boolean check){
        if (checkState == CHECK_STATE_DISABLED) {
            return;
        }
        if (check) {
            checkState = CHECK_STATE_CHECKED;
        }
        else {
            checkState = CHECK_STATE_UNCHECKED;
        }
        setBkgByCheckState();
        notifyListner();
    }

    /**
     * 改变Check状态
     */
    public void changeState() {
        if (checkState == CHECK_STATE_DISABLED) {
            return;
        }

        if (checkState == CHECK_STATE_UNCHECKED) {
            checkState = CHECK_STATE_CHECKED;
        } else if (checkState == CHECK_STATE_CHECKED) {
            checkState = CHECK_STATE_UNCHECKED;
        }

        setBkgByCheckState();
        notifyListner();
    }

    public void setCheckDisabled() {
        this.checkState = CHECK_STATE_DISABLED;
        setBkgByCheckState();
    }

    private void setBkgByCheckState() {
        if (checkState == CHECK_STATE_UNCHECKED) {
            setImageResource(uncheck_bkg_id);
        }
        else if (checkState == CHECK_STATE_DISABLED) {
            setImageResource(disable_check_bkg_id);
        }
        else {
            setImageResource(check_bkg_id);
        }

    }

    public interface OnCheckStateChangedListener {
        public void onCheckStateChanged(boolean isChecked);
    }

    private OnCheckStateChangedListener listener;

    public void setOnCheckStateChangedListener(OnCheckStateChangedListener listener) {
        this.listener = listener;
    }

    private void notifyListner() {
        if (this.listener != null) {
            if (checkState == CHECK_STATE_UNCHECKED) {
                this.listener.onCheckStateChanged(false);
            } else if (checkState == CHECK_STATE_CHECKED) {
                this.listener.onCheckStateChanged(true);
            }
        }
    }
}
