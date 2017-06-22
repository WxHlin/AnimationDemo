package com.demo.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.animation.ObjectAnimator.ofFloat;


public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView image;
    private Button move ,menu ,value;

    private int[] res={R.id.img1,R.id.img2,R.id.img3,R.id.img4,R.id.img5,R.id.img6};
    private List<ImageView> list=new ArrayList<ImageView>();

    private boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        image = (ImageView) findViewById(R.id.image);
        move=(Button) findViewById(R.id.move);
        menu= (Button) findViewById(R.id.menu);
        value= (Button) findViewById(R.id.value);
        move.setOnClickListener(this);
        menu.setOnClickListener(this);
        value.setOnClickListener(this);

        for (int i = 0; i <res.length ; i++) {
            ImageView imageView= (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            list.add(imageView);
        }
    }
    /**
     * translationX/translationY-------便宜距离
     * X/Y----具体移动到某一个点
     * rotation、rotationX/rotationY----旋转
     * scaleX/scaleY--------缩放
     * alpha-------透明度
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.move:
                //第一种写法
//                ObjectAnimator.ofFloat(image,"translationX",0f,200f).setDuration(1000).start();
//                ObjectAnimator.ofFloat(image,"rotation",0f,360f).setDuration(1000).start();
//                ObjectAnimator.ofFloat(image,"translationY",0f,200f).setDuration(1000).start();
                //第二种写法 谷歌对PropertyValuesHolder做过优化，节省系统资源
//                PropertyValuesHolder p1=PropertyValuesHolder.ofFloat("translationX",0f,200f);
//                PropertyValuesHolder p2=PropertyValuesHolder.ofFloat("translationY",0f,200f);
//                PropertyValuesHolder p3=PropertyValuesHolder.ofFloat("rotation",0f,360f);
//                ObjectAnimator.ofPropertyValuesHolder(image,p1,p2,p3).setDuration(1000).start();
                //第三种写法
                ObjectAnimator anim1= ObjectAnimator.ofFloat(image,"translationX",0f,200f);
                ObjectAnimator anim2=ofFloat(image,"rotation",0f,360f);
                ObjectAnimator anim3=ofFloat(image,"translationY",0f,200f);
                AnimatorSet set=new AnimatorSet();
//                set.playTogether(anim1,anim2,anim3);//一起播放
//                set.playSequentially(anim1,anim2,anim3);//按先后顺序执行
                //先执行1和3，再执行2
                set.play(anim1).with(anim3);
                set.play(anim2).after(anim1);
                set.setDuration(1000);
                //添加动画监听
                //第一种写法   AnimatorListener
//                set.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
                //第二种写法  AnimatorListenerAdapter
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Toast.makeText(PropertyAnimationActivity.this,"end",Toast.LENGTH_SHORT).show();
                    }
                });
                set.start();
                break;
            case R.id.menu:
                if (flag){
                    showMenu();
                    flag=false;
                } else{
                    hideMenu();
                    flag=true;
                }
                break;
            case R.id.value:
                ValueAnimator animator=ValueAnimator.ofInt(0,100);
                animator.setDuration(5000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer val= (Integer) animation.getAnimatedValue();
                        value.setText(val+"");
                    }
                });
                animator.start();
                break;
            default:
                Toast.makeText(PropertyAnimationActivity.this,"click"+v.getId(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //手气菜单
    private void hideMenu() {
        for (int i = 0; i <list.size() ; i++) {
            ObjectAnimator anim=ObjectAnimator.ofFloat(list.get(i),"translationY",130*i,0f);
            anim.setDuration(500);
            anim.setStartDelay(i*300);
            anim.setInterpolator(new BounceInterpolator());//设置插值器
            anim.start();
        }
    }

    //显示菜单
    private void showMenu() {
        for (int i = 0; i <list.size() ; i++) {
            ObjectAnimator anim=ObjectAnimator.ofFloat(list.get(i),"translationY",0,130*i);
            anim.setDuration(500);
            anim.setStartDelay(i*300);
            anim.setInterpolator(new BounceInterpolator());//设置插值器
            anim.start();
        }
    }
}
