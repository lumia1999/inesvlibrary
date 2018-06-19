package com.inesv.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.inesv.library.R;

/**
 * 限制小数位的输入文本
 */
public class FigureEdittext extends android.support.v7.widget.AppCompatEditText {
    /**
     * 要限制的小数位数
     */
    private int decimalplaces;

    public FigureEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        // this.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inesv);
        decimalplaces = typedArray.getInt(R.styleable.inesv_decimalplaces, 0);
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().contains(".")) {
                    if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > decimalplaces) {
                        charSequence = charSequence.toString().subSequence(0,
                                charSequence.toString().indexOf(".") + decimalplaces + 1);
                        FigureEdittext.this.setText(charSequence);
                        FigureEdittext.this.setSelection(charSequence.length());
                    }
                }
                if (charSequence.toString().trim().substring(0).equals(".")) {
                    charSequence = "0" + charSequence;
                    FigureEdittext.this.setText(charSequence);
                    FigureEdittext.this.setSelection(2);
                }
                if (charSequence.toString().startsWith("0")
                        && charSequence.toString().trim().length() > 1) {
                    if (!charSequence.toString().substring(1, 2).equals(".")) {
                        FigureEdittext.this.setText(charSequence.subSequence(0, 1));
                        FigureEdittext.this.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
