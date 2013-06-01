package com.example.androidsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.LinearLayout;
import android.widget.TextView;

// Purpose of this class is so that each event listener class
// does not need to redefine these commonly used methods and fields
public class GenericSensorListener implements SensorEventListener
{

	protected TextView tv;
	protected String SensorName = "";

	double xValueHigh = 0;
	double yValueHigh = 0;
	double zValueHigh = 0;
	double lightSensorHigh = 0;

	public void onAccuracyChanged(Sensor sensor, int accuracy){}

	public void onSensorChanged(SensorEvent se)
	{
		if (se.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR
				|| se.sensor.getType() == Sensor.TYPE_ACCELEROMETER
				|| se.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
		{

			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];

			if (xValueHigh < Math.abs(x))
			{
				xValueHigh = Math.abs(x);
			}
			if (yValueHigh < Math.abs(y))
			{
				yValueHigh = Math.abs(y);
			}

			if (zValueHigh < Math.abs(z))
			{
				zValueHigh = Math.abs(z);
			}
			String values = String.format("(%.4f, %.4f, %.4f)", x, y, z);
			String highValues =String.format("(%.4f, %.4f, %.4f)", xValueHigh, yValueHigh, zValueHigh);
			
			tv.setText(SensorName+": " + values + "\n  High:" +highValues);

		}
		else if (se.sensor.getType() == Sensor.TYPE_LIGHT)
		{
			

			if (lightSensorHigh < Math.abs(se.values[0]))
			{
				lightSensorHigh = Math.abs(se.values[0]);
			}
			String value = String.format("%.4f", se.values[0]);
			String highValue=String.format("%.4f", lightSensorHigh);

			tv.setText(SensorName+": " + value + "\n High: " + highValue);

		}

	}

	public GenericSensorListener(Context context, String SensorName)
	{
		this.SensorName = SensorName;
		addLabel(MainActivity.layout, null, context);
	}

	public void addLabel(LinearLayout layout, String value, Context context)
	{

		tv = new TextView(context);
		tv.setText(SensorName + value);
		layout.addView(tv);

	}

}
