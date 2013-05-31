package com.example.lab1;

import android.app.Activity;
import android.content.ContextWrapper;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{

	public static LinearLayout layout;
	public static ContextWrapper context;
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		int width = getWindowManager().getDefaultDisplay().getWidth();
		int height = getWindowManager().getDefaultDisplay().getHeight();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout = (LinearLayout) findViewById(R.id.layout1);
		layout.setOrientation(LinearLayout.VERTICAL);

		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor accelerometer = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		AccelerometerSensorEventListener accelerometerListener = new AccelerometerSensorEventListener(
				getApplicationContext(), layout,width,height);

		
		sensorManager.registerListener(accelerometerListener, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
		Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

		 GenericSensorListener LightSensorEventListener = new GenericSensorListener(
				getApplicationContext(),"Light Sensor");

		sensorManager.registerListener(LightSensorEventListener, lightSensor,
				SensorManager.SENSOR_DELAY_FASTEST);

		Sensor magneticField = sensorManager
				.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

		GenericSensorListener magneticFieldlistener = new GenericSensorListener(
				getApplicationContext(),"Magnetic");

		sensorManager.registerListener(magneticFieldlistener, magneticField,
				SensorManager.SENSOR_DELAY_NORMAL);

		Sensor rotationVector = sensorManager
				.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

		GenericSensorListener rotationVectorListener = new GenericSensorListener(
				getApplicationContext(),"Rotation");

		sensorManager.registerListener(rotationVectorListener, rotationVector,
				SensorManager.SENSOR_DELAY_NORMAL);
		
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;

	}

}
