package com.syyazilim.runout;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ((Button) findViewById(R.id.result_save_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.result_cancel_button)).setOnClickListener(this);

        ActionBar actionBar = getActionBar();
        actionBar.hide();


        LinearLayout linearLayoutSub = (LinearLayout) findViewById(R.id.result_linear_layout_sub);
        LinearLayout linearLayoutSuper = (LinearLayout) findViewById(R.id.result_linear_layout_super);

        ObjectAnimator colorFadeSuper = ObjectAnimator.ofObject(linearLayoutSuper, "backgroundColor", new ArgbEvaluator(), Color.argb(255, 90, 255, 250), 0xFFFFFF);
        //ObjectAnimator colorFadeSub = ObjectAnimator.ofObject(linearLayoutSub, "visibility", new ArgbEvaluator(), View.VISIBLE);

        colorFadeSuper.setDuration(5000);
        //colorFadeSub.setDuration(3000);
        colorFadeSuper.start();
        //colorFadeSub.start();

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        linearLayoutSub.setVisibility(View.VISIBLE);
        linearLayoutSub.startAnimation(animFadeIn);

        Animation animMove = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        TextView resultMessageView = (TextView)findViewById(R.id.result_message);
        resultMessageView.setVisibility(View.VISIBLE);
        resultMessageView.startAnimation(animMove);

/*
        (new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TextView resultMessageView = (TextView)findViewById(R.id.result_message);
                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
                resultMessageView.setVisibility(View.INVISIBLE);
                resultMessageView.startAnimation(animFadeOut);
                //overridePendingTransition(R.anim.fade_out, R.anim.deneme_anim);

            }
        }).start();*/


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

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.main_start_button:
                    view.setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_pause)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_resume)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_pause_button)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_resume_button)).setVisibility(View.INVISIBLE);
                    break;
                case R.id.main_stop_button_pause:
                    ((Button) findViewById(R.id.main_start_button)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_pause_button)).setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_resume)).setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_pause)).setVisibility(View.INVISIBLE);
            }
        }
    }
    private void redirect(Class clazz){
        Intent intent = new Intent(getApplicationContext(), clazz);
        startActivity(intent);
    }

}
