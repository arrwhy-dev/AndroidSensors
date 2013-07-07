package com.ryogarat.androidsensors;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class CameraFlashManager
{
	private static Camera mCamera;
	private static Parameters mCameraParameters;
	private static boolean mCameraState = false;

	public static boolean cameraIsPresent(Context context)
	{
		return context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA_FLASH);

	}

	public static void connectToCamera()
	{
		mCamera = Camera.open();
		mCameraParameters = mCamera.getParameters();
		mCameraParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
		mCamera.setParameters(mCameraParameters);
	}

	public static void disconnectCamera()
	{
		if (mCamera != null)
		{
			mCamera.release();
		}
	}

	public static boolean cameraIsRunning()
	{
		return mCameraState;
	}

	public static void toggleCameraState()
	{
		mCameraState = !mCameraState;
	}

}
