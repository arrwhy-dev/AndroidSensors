package com.example.androidsensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ryogarat.androidsensors.R;

public class BarometerActivity extends Activity implements SensorEventListener,
		OnClickListener
{
	private SensorManager mSensorManager;
	private Sensor mPressure;
	private Sensor mTemp;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		tv = (TextView) findViewById(R.id.textview1);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
		mTemp = mSensorManager
				.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}

	@Override
	protected void onResume()
	{
		// Register a listener for the sensor.
		super.onResume();
		mSensorManager.registerListener(this, mPressure,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause()
	{
		// Be sure to unregister the sensor when the activity pauses.
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent event)
	{
		float millibars_of_pressure = event.values[0];
		tv.setText(String.valueOf(millibars_of_pressure) + " mBar");
		// Do something with this sensor data.

	}

	public void onClick(View v)
	{
		Intent i = new Intent(this, TemperatureActivity.class);
		startActivity(i);
	}

}
