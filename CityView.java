package com.nanyue.runningman.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class CityView extends View{
	/**View中需要显示的内容*/
	private String s = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**所要绘制的view的高度*/
	private int height;
	/**需要绘制的文字的个数*/
	private int size;
	//画笔
	private Paint paint;
	/**监听事件*/
	private OnWordSelectedListenner listenner;

	/**
	 * 自定义监听事件的接口
	 */
	public interface OnWordSelectedListenner{
		//设置监听事件
		void selectedListenner(MotionEvent event, String word);
	}
	/**给view设置监听事件的方法*/
	public void setOnWordSelectedListenner(OnWordSelectedListenner listenner) {
		this.listenner = listenner;
	}

	public CityView(Context context) {
		this(context,null);
	}

	public CityView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//初始化操作
		paint = new Paint();
		int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
		//设置所绘制文字的大小
		paint.setTextSize(size);
		paint.setColor(Color.parseColor("#666666"));
		paint.setAntiAlias(true);
	}

	public CityView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	
	private int last = -1;
	/**重写touch事件*/
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:	//手指按下时的事件
			if(listenner!=null){
				//获取touch在View中的高度
				float y = event.getY();
				//获取点击到的字母的索引值
				int index = (int) (y/height*size);
				if(index>0&&index!=last){
					listenner.selectedListenner(event,String.valueOf(s.charAt(index)));
					last = index;
				}
			}
			break;
		case MotionEvent.ACTION_UP://手指抬起时的时间
			//
			if(listenner!=null){
				listenner.selectedListenner(event,null);
			}
			break;

		default:
			break;
		}
		return true;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//获取view的宽度
		int width = getWidth();
		//获取view的高度
		height = getHeight();
		//计算每一个字母的高度
		int each = height/s.length();
		//获取需要绘制的文字的个数
		size = s.length();
		for (int i = 0; i < size; i++) {
			String w = String.valueOf(s.charAt(i));
			//测量文字大小
			float size = paint.measureText(w);
			//文字X轴的起始点位置((控件宽度-文字宽度)/2  目的使所要绘制的文字位于view的中间）
			float x = (width-size)/2;
			/*
				一：要绘制的文字
				二：x轴的起始点坐标
				三：Y轴的起始点坐标
				四：画笔对象
			 */
			canvas.drawText(w, x, (i+1)*each, paint);
		}
	}
}
