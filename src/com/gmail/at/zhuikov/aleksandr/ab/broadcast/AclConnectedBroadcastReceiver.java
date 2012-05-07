package com.gmail.at.zhuikov.aleksandr.ab.broadcast;

import static android.bluetooth.BluetoothDevice.EXTRA_DEVICE;
import static android.content.Context.ALARM_SERVICE;
import static com.gmail.at.zhuikov.aleksandr.ab.AutoBluetoothApplication.TAG;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gmail.at.zhuikov.aleksandr.ab.service.DisableBluetoothService;

public class AclConnectedBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Connected to " + intent.getParcelableExtra(EXTRA_DEVICE));
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
		alarmManager.cancel(DisableBluetoothService.getPendingIntent(context));
	}
}
