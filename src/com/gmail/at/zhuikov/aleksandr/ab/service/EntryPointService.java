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

public class EntryPointService extends IntentService {

	public EntryPointService() {
		super(EntryPointService.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Log.d(TAG, "EntryPointService started");

		startService(CancelPendingIntentsService.getIntent(this));

		BluetoothAdapter bluetoothAdapter = getDefaultAdapter();

		if (bluetoothAdapter.isEnabled()) {
			bluetoothAdapter.disable();
		} else {
			bluetoothAdapter.enable();
		}
	}

	public static Intent getIntent(Context context) {
		return new Intent(context, EntryPointService.class);
	}

	public static PendingIntent getPendingIntent(Context context) {
		return getService(context, 0, new Intent(context, EntryPointService.class), 0);
	}
}
