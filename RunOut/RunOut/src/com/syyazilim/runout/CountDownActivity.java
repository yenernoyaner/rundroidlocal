package com.syyazilim.runout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CountDownActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown);

        countDown((TextView)findViewById(R.id.count_down), 3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_log_out:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void countDown(final TextView tv, final int count) {
        if (tv != null) {
            if (count == 0) {
                tv.setText(""); //Note: the TextView will be visible again here.
                super.onBackPressed();
                return;
            }
            tv.setText(count + "");
            AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
            animation.setDuration(1000);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                public void onAnimationEnd(Animation anim) {
                    countDown(tv, count - 1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            tv.startAnimation(animation);
            //showCountDownDialog(count + "");

        }
    }

    private void redirect(Class clazz) {
        Intent intent = new Intent(getApplicationContext(), clazz);
        startActivity(intent);
    }
}

