package com.demo.customview;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private Button text1,text2;
    private ImageView image,loading;

    private boolean flag = false;

    private AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        text1 = (Button) findViewById(R.id.text1);
        text2 = (Button) findViewById(R.id.text2);
        image = (ImageView) findViewById(R.id.image);
        loading = (ImageView) findViewById(R.id.loading);

        //帧动画
        loading.setBackgroundResource(R.drawable.anim);
        animationDrawable= (AnimationDrawable) loading.getBackground();
        animationDrawable.start();

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    Tool.hideView(text);
                    flag = true;
//                    animationDrawable.start();
                } else {
                    flag = false;
                    Tool.showView(text);
//                    animationDrawable.stop();
                }
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PropertyAnimationActivity.class));
            }
        });

        //补间动画
        AnimationSet animationSet = new AnimationSet(true);
        //代码设置动画-------透明度
        Animation alphaAnimation = new AlphaAnimation(0, 1);
        //通过代码的方式定义--------旋转动画
        Animation rotateAnimation = new RotateAnimation(0, 360, RELATIVE_TO_SELF, 0.5F
                , RELATIVE_TO_SELF, 0.5F);
        //缩放动画
        Animation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.1f,
                RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        //平移动画
        Animation tranalateAbimation = new TranslateAnimation(RELATIVE_TO_SELF,
                0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f);

        //        animationSet.setFillBefore(true);//设置动画结束后,控件将回到执行之前状态
        animationSet.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
        animationSet.setDuration(5000);//设置动画持续时间为5秒
        animationSet.setRepeatCount(2);//设置动画重复次数
        animationSet.setStartOffset(500);//设置动画执行之前的等待时间

//        1. AccelerateDecelerateInterpolator：在动画开始与结束的地方速率改变比较慢，在中间的时候速率快。
//        2.AccelerateInterpolator：在动画开始的地方速率改变比较慢，然后开始加速
//        3. CycleInterpolator：动画循环播放特定的次数，速率改变沿着正弦曲线
//        4.DecelerateInterpolator：在动画开始的地方速率改变比较慢，然后开始减速
//        5. LinearInterpolator：动画以均匀的速率改变
        animationSet.setInterpolator(new AccelerateInterpolator());

        //添加动画
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(tranalateAbimation);
        image.startAnimation(animationSet);

        //在XML中定义动画j及使用
//        animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_in);
//        image.startAnimation(animation);

    }


}
