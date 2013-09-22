package com.syyazilim.runout;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.Toast;

//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesClient;

public class AndroidGridLayoutActivity extends Activity implements OnClickListener {

	private Chronometer chronometer;
	private long timeWhenStopped = 0;
	private int resultCode = 1;
	Distance distance = new Distance();
	GPSTracker gps = new GPSTracker(getApplicationContext());

	// private LocationClient locationClient ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);
		System.out.println("oncreate");
		Log.d("semih", "______create");
//		gps.showSettingsAlert();
		
		chronometer = (Chronometer) findViewById(R.id.chronometerid);
		chronometer.setFormat("00:00:00");
		chronometer.setOnChronometerTickListener(new OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer cArg) {
				long t = SystemClock.elapsedRealtime() - cArg.getBase();
				int h = (int) (t / 3600000);
				int m = (int) (t - h * 3600000) / 60000;
				int s = (int) (t - h * 3600000 - m * 60000) / 1000;
				String hh = h < 10 ? "0" + h : h + "";
				String mm = m < 10 ? "0" + m : m + "";
				String ss = s < 10 ? "0" + s : s + "";
				cArg.setText(hh + ":" + mm + ":" + ss);
				System.out.println("gps.canGetLocation():" + gps.canGetLocation());
				if (gps.canGetLocation()) {
//					Toast.makeText(getApplicationContext(), distance.gps2m(gps.getLatitude(), gps.getLongitude(), gps.getLatitude(), gps.getLongitude()) + "", 1).show();
					Toast.makeText(getApplicationContext(), gps.getLatitude() + "," + gps.getLongitude() + "", 1).show();
					System.out.println(gps.getLatitude() + "," + gps.getLongitude());
					Log.d("semih", gps.getLatitude() + "," + gps.getLongitude());
				}
			}
		});
		chronometer.setBase(SystemClock.elapsedRealtime());
		((Button) findViewById(R.id.start_button)).setOnClickListener(this);
		((Button) findViewById(R.id.stop_button)).setOnClickListener(this);
		((Button) findViewById(R.id.resume_button)).setOnClickListener(this);
		((Button) findViewById(R.id.pause_button)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v != null) {
			switch (v.getId()) {
			case R.id.start_button:
				chronometer.setBase(SystemClock.elapsedRealtime());
				chronometer.start();
				v.setVisibility(View.INVISIBLE);
				((Button) findViewById(R.id.stop_button)).setVisibility(View.VISIBLE);
				((Button) findViewById(R.id.pause_button)).setVisibility(View.VISIBLE);
				((Button) findViewById(R.id.resume_button)).setVisibility(View.INVISIBLE);
				break;
			case R.id.stop_button:
				chronometer.stop();
				v.setVisibility(View.INVISIBLE);
				((Button) findViewById(R.id.start_button)).setVisibility(View.VISIBLE);
				((Button) findViewById(R.id.pause_button)).setVisibility(View.INVISIBLE);
				((Button) findViewById(R.id.resume_button)).setVisibility(View.INVISIBLE);
				timeWhenStopped = 0;
				chronometer.setBase(timeWhenStopped);
				break;
			case R.id.pause_button:
				chronometer.stop();
				timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
				v.setVisibility(View.INVISIBLE);
				((Button) findViewById(R.id.resume_button)).setVisibility(View.VISIBLE);
				((Button) findViewById(R.id.start_button)).setVisibility(View.INVISIBLE);
				((Button) findViewById(R.id.stop_button)).setVisibility(View.VISIBLE);
				break;
			case R.id.resume_button:
				chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
				chronometer.start();
				v.setVisibility(View.INVISIBLE);
				((Button) findViewById(R.id.pause_button)).setVisibility(View.VISIBLE);
				((Button) findViewById(R.id.resume_button)).setVisibility(View.INVISIBLE);
				((Button) findViewById(R.id.stop_button)).setVisibility(View.VISIBLE);
			}
		}
	}
}
