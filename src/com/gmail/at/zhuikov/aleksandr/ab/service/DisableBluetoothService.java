package com.gmail.at.zhuikov.aleksandr.ab.service;

import static android.app.PendingIntent.getService;
import static com.gmail.at.zhuikov.aleksandr.ab.AutoBluetoothApplication.TAG;
import android.app.IntentService;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DisableBluetoothService extends IntentService {

	public DisableBluetoothService() {
		super(DisableBluetoothService.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		if (!bluetoothAdapter.isEnabled()) {
			Log.i(TAG, "Bluetooth already disabled");
			return;
		}

		Log.i(TAG, "Disabling bluetooth");
		bluetoothAdapter.disable();
	}

	public static PendingIntent getPendingIntent(Context context) {
		return getService(context, 0, new Intent(context, DisableBluetoothService.class), 0);
	}
}

