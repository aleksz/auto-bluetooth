package com.gmail.at.zhuikov.aleksandr.ab.service;

import static android.app.PendingIntent.getService;
import static android.bluetooth.BluetoothAdapter.getDefaultAdapter;
import static com.gmail.at.zhuikov.aleksandr.ab.AutoBluetoothApplication.TAG;
import android.app.IntentService;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class EnableBluetoothService extends IntentService {

	public EnableBluetoothService() {
		super(EnableBluetoothService.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		BluetoothAdapter bluetoothAdapter = getDefaultAdapter();

		if (bluetoothAdapter.isEnabled()) {
			Log.i(TAG, "Bluetooth is already enabled");
			return;
		}

		Log.i(TAG, "Enabling bluetooth");
		bluetoothAdapter.enable();
	}

	public static PendingIntent getPendingIntent(Context context) {
		return getService(context, 0, new Intent(context, EnableBluetoothService.class), 0);
	}
}
