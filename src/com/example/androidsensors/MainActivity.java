package com.example.androidsensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ryogarat.androidsensors.R;

//TODO: clean up code and extract out methods
public class MainActivity extends Activity implements OnClickListener
{

	public static LinearLayout layout;
	private static int mWidth;

	@Override
	// TODO Refactor and clean up this method
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout = (LinearLayout) findViewById(R.id.layout1);

		mWidth = getWindowManager().getDefaultDisplay().getWidth();

		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		// setupSensors
		Sensor accelerometer = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		Sensor magneticSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		Sensor rotationSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		Sensor temperatureSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		Sensor pressureSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_PRESSURE);

		// Set up event listeners
		AccelerometerSensorEventListener accelerometerListener = new AccelerometerSensorEventListener(
				getApplicationContext(), layout);
		GenericSensorListener LightSensorEventListener = new GenericSensorListener(
				getApplicationContext(), SensorStringResources.LIGHT_SENSOR);
		GenericSensorListener magneticFieldlistener = new GenericSensorListener(
				getApplicationContext(), SensorStringResources.MAGNETIC);
		GenericSensorListener rotationVectorListener = new GenericSensorListener(
				getApplicationContext(), SensorStringResources.ROATATION_SENSOR);
		GenericSensorListener tempSensor = new GenericSensorListener(
				getApplicationContext(),
				SensorStringResources.TEMPERATURE_SENSOR);
		GenericSensorListener pressuresenslistener = new GenericSensorListener(
				getApplicationContext(), SensorStringResources.PRESSURE_SENSOR);

		// Register event listeners
		sensorManager.registerListener(accelerometerListener, accelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);

		sensorManager.registerListener(LightSensorEventListener, lightSensor,
				SensorManager.SENSOR_DELAY_FASTEST);

		sensorManager.registerListener(magneticFieldlistener, magneticSensor,
				SensorManager.SENSOR_DELAY_NORMAL);

		sensorManager.registerListener(rotationVectorListener, rotationSensor,
				SensorManager.SENSOR_DELAY_NORMAL);

		sensorManager.registerListener(tempSensor, temperatureSensor,
				SensorManager.SENSOR_DELAY_NORMAL);

		sensorManager.registerListener(pressuresenslistener, pressureSensor,
				sensorManager.SENSOR_DELAY_NORMAL);

		ToggleFlashLight();

		Button flashLightToggleButton = new Button(this);
		flashLightToggleButton.setText("Vibrate");
		flashLightToggleButton.setVisibility(Button.VISIBLE);

		layout.addView(flashLightToggleButton);

		flashLightToggleButton.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View v)
			{
				Vibrator viber = ((Vibrator) getSystemService(VIBRATOR_SERVICE));
				long[] a = { (long) 0.1, (long) 0.2, (long) 0.3, (long) 0.4,
						(long) 0.5, (long) 0.6, (long) 0.7, (long) 0.8,
						(long) 0.9 };

				viber.vibrate(250);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;

	}

	private void ToggleFlashLight()
	{
		if (CameraFlashManager.cameraIsPresent(getApplicationContext()))
		{
			FlashLightToggleListener();
		}
	}

	private void FlashLightToggleListener()
	{
		Button flashLightToggleButton = setUpCameraToggleButton();
		flashLightToggleButton.setOnClickListener(this);

	}

	private Button setUpCameraToggleButton()
	{
		Button flashLightToggleButton = new Button(this);
		flashLightToggleButton
				.setText(SensorStringResources.TOGGLE_FLASH_LIGHT);
		flashLightToggleButton.setVisibility(Button.VISIBLE);
		layout.addView(flashLightToggleButton);
		return flashLightToggleButton;

	}

	public void onClick(View v)
	{
		if (CameraFlashManager.cameraIsRunning())
		{
			CameraFlashManager.disconnectCamera();
			CameraFlashManager.toggleCameraState();
		}
		else
		{

			CameraFlashManager.connectToCamera();
			CameraFlashManager.toggleCameraState();
		}

	}

	// TODO: find a better way to pass the display width
	public static int getDisplayWidth()
	{
		return mWidth;
	}

}
