package com.future.association.community.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/7/16.
 */

public class CustomGridView extends GridView {
    private int rownum;

    public CustomGridView(Context context) {
        this(context,null);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int colnum = getNumColumns(); //获取列数
        int total = getChildCount();  //获取Item总数
        int verTicalSpace = getVerticalSpacing() ;
        int horizontalSpace = getHorizontalSpacing() ;

        //计算行数
        if (total % colnum == 0) {
            rownum = total / colnum;
        } else {
            rownum = (total / colnum) + 1; //当余数不为0时，要把结果加上1
        }
        Paint localPaint; //设置画笔
        localPaint = new Paint();
        localPaint.setStyle(Paint.Style.STROKE); //画笔实心
        localPaint.setColor(Color.parseColor("#DCDCDC"));//画笔颜色

        View view0 = getChildAt(0); //第一个view
        View viewColLast = getChildAt(colnum - 1);//第一行最后一个view
        try{
            for (int i = 1, j = 1; i < colnum || j < rownum; i++, j++) {
                //画竖线
                if (total % colnum != 0 && i > total % colnum) {
                    canvas.drawLine(view0.getRight() * i + horizontalSpace * (i - 1), view0.getTop(),
                            view0.getRight() * i + horizontalSpace * (i - 1), view0.getBottom() * (rownum - 1), localPaint);
                } else {
                    canvas.drawLine(view0.getRight() * i + horizontalSpace * (i - 1), view0.getTop(),
                            view0.getRight() * i + horizontalSpace * (i - 1), view0.getBottom() * rownum, localPaint);
                }
                //画横线
                canvas.drawLine(view0.getLeft(), view0.getBottom() * j + verTicalSpace * (j - 1),
                        viewColLast.getRight(), viewColLast.getBottom() * j + verTicalSpace * (j - 1), localPaint);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
