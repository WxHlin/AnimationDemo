package com.demo.customview;

import android.view.View;
import android.view.animation.RotateAnimation;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

class Tool {

    /**
     *
     * @param text
     */
    public static void showView(View text) {
        /*
        * 参数
        * 1   0度
        * 2   180度
        * 3和4    中心点
        * */
        RotateAnimation ra=new RotateAnimation(180,360, text.getWidth()/2,text.getHeight()/2);
        ra.setDuration(500);//动画时间
        ra.setFillAfter(true);//动画停留在播放完成的状态
        text.startAnimation(ra);//开始动画
    }

    public static void hideView(View text) {
        hideView(text,0);
    }


    public static void hideView(View text,long startOffset) {
        /*
        * 参数
        * 1   0度
        * 2   180度
        * 3和4    中心点
        * */
        RotateAnimation ra=new RotateAnimation(0,180, text.getWidth()/2,text.getHeight());
        ra.setDuration(500);//动画时间
        ra.setFillAfter(true);//动画停留在播放完成的状态
        ra.setStartOffset(startOffset);//设置动画延迟多久后播放
        text.startAnimation(ra);//开始动画
    }
}
