package com.syyazilim.runout;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.internal.view.menu.ActionMenuView;
import com.actionbarsherlock.internal.widget.IcsSpinner;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class MainActivity extends SherlockActivity implements View.OnClickListener {

    private AlertDialog countDownDialog;
    static final String[] COUNTRIES = new String[] {
            "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
            "Angola", "Anguilla", "Canada", "France", "Spain" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getActionBar().setHomeButtonEnabled(true);

        ((Button) findViewById(R.id.main_start_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_stop_button_pause)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_stop_button_resume)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_resume_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.main_pause_button)).setOnClickListener(this);

        LinearLayout linearLayoutSub = (LinearLayout) findViewById(R.id.main_linear_layout_sub);

        ActionBar actionBar = getActionBar();


        /*LinearLayout linearLayoutSuper = (LinearLayout) findViewById(R.id.main_linear_layout_super);

        ObjectAnimator colorFadeSuper = ObjectAnimator.ofObject(linearLayoutSuper, "backgroundColor", new ArgbEvaluator(), Color.argb(255, 90, 255, 250), 0xFFFFFF);
        //ObjectAnimator colorFadeSub = ObjectAnimator.ofObject(linearLayoutSub, "visibility", new ArgbEvaluator(), View.VISIBLE);

        colorFadeSuper.setDuration(3000);
        //colorFadeSub.setDuration(3000);
        colorFadeSuper.start();
        //colorFadeSub.start();
        */

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        linearLayoutSub.setVisibility(View.VISIBLE);
        linearLayoutSub.startAnimation(animFadeIn);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu sub = menu.addSubMenu("MENU");
        sub.add(0, 1, 0, "Settings");
        sub.add(0, 2, 0, "Log Out");
        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch(item.getItemId())
        {
            case 1:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            case 2:
                System.exit(1);
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.main_start_button:
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.countdown);
                    mp.start();
                    view.setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_pause)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_resume)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_pause_button)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_resume_button)).setVisibility(View.INVISIBLE);




                    /*View dialogView = getLayoutInflater().inflate(R.layout.countdown, null);

                    showCountDownDialog(dialogView, "3");
                    countDown((TextView) dialogView.findViewById(R.id.count_down), 3);
                    */

                    redirect(CountDownActivity.class);

                    break;
                case R.id.main_stop_button_pause:
                    ((Button) findViewById(R.id.main_start_button)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_pause_button)).setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_resume)).setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_pause)).setVisibility(View.INVISIBLE);
                    break;
                case R.id.main_stop_button_resume:
                    ((Button) findViewById(R.id.main_start_button)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_pause_button)).setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_resume)).setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_pause)).setVisibility(View.INVISIBLE);
                    redirect(ResultActivity.class);
                    break;
                case R.id.main_pause_button:
                    ((Button) findViewById(R.id.main_resume_button)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_pause_button)).setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_pause)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_resume)).setVisibility(View.VISIBLE);
                    break;
                case R.id.main_resume_button:
                    ((Button) findViewById(R.id.main_pause_button)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_resume_button)).setVisibility(View.INVISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_pause)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.main_stop_button_resume)).setVisibility(View.VISIBLE);
            }
        }
    }

    private void redirect(Class clazz) {
        Intent intent = new Intent(getApplicationContext(), clazz);
        startActivity(intent);
    }

    private void countDown(final TextView tv, final int count) {
        if (tv != null) {
            if (count == 0) {
                tv.setText(""); //Note: the TextView will be visible again here.
                if (countDownDialog != null) {
                    //countDownDialog.hide();
                    countDownDialog.dismiss();
                }
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

    private void showCountDownDialog(View dialogView, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialogView);
        builder.setInverseBackgroundForced(true);

        //builder.setMessage(message);
        //builder.setCancelable(true);
        countDownDialog = builder.create();
        //dialog.setInverseBackgroundForced(true);

        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //countDownDialog.setContentView(R.layout.countdown);
        countDownDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        //Drawable d = new ColorDrawable(Color.BLACK);
        //d.setAlpha(130);

        //countDownDialog.getWindow().setBackgroundDrawable(d);

        //dialog.setContentView(R.layout.countdown);
        countDownDialog.show();


       /* Drawable d = new ColorDrawable(Color.BLACK);
        d.setAlpha(130);
        AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
        TextView myMsg = new TextView(this);
        myMsg.setText("Central");
        myMsg.setTextSize(80);
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        myMsg.setBackground(d);

        popupBuilder.setInverseBackgroundForced(true);
        popupBuilder.setView(myMsg);
        AlertDialog dialog = popupBuilder.create();
        dialog.getWindow().setBackgroundDrawable(d);
        dialog.show();
*/


    }

    private void showCountDownDialog(View dialogView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialogView);

        builder.setInverseBackgroundForced(true);

        countDownDialog = builder.create();

        TextView textView = new TextView(this);

        //dialog.setInverseBackgroundForced(true);

        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //countDownDialog.setContentView(R.layout.countdown);
        countDownDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        countDownDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //Drawable d = new ColorDrawable(Color.BLACK);
        //d.setAlpha(130);

        //countDownDialog.getWindow().setBackgroundDrawable(d);

        //dialog.setContentView(R.layout.countdown);
        countDownDialog.show();


    }
}

