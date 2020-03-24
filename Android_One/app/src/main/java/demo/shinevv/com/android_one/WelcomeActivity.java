package demo.shinevv.com.android_one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉界面任务条
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        start();
    }


    private void start() {
        iv = findViewById(R.id.welcome_iv);
        AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
        //渐变时间
        aa.setDuration(2000);
        //展示图片渐变动画
        iv.startAnimation(aa);

        //渐变过程监听
        aa.setAnimationListener(new Animation.AnimationListener() {

            /**
             * 动画开始时
             */
            @Override
            public void onAnimationStart(Animation animation) {
                System.out.println("动画开始...");
            }

            /**
             * 重复动画时
             */
            @Override
            public void onAnimationRepeat(Animation animation) {
                System.out.println("动画重复...");
            }

            /**
             * 动画结束时
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(WelcomeActivity.this, OneActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
