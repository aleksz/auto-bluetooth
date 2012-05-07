package com.gmail.at.zhuikov.aleksandr.ab.broadcast;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;
import static android.bluetooth.BluetoothDevice.EXTRA_DEVICE;
import static android.content.Context.ALARM_SERVICE;
import static com.gmail.at.zhuikov.aleksandr.ab.AutoBluetoothApplication.TAG;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.gmail.at.zhuikov.aleksandr.ab.service.DisableBluetoothService;
import com.gmail.at.zhuikov.aleksandr.ab.util.Preferences;
import com.gmail.at.zhuikov.aleksandr.ab.util.PreferencesImpl;

public class AclDisconnectedBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Disconnected from " + intent.getParcelableExtra(EXTRA_DEVICE));

		Preferences preferences = PreferencesImpl.getInstance(context);

		if (preferences.isOn()) {
			AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
			alarmManager.set(
					ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + 60 * 1000,
					DisableBluetoothService.getPendingIntent(context));
		}
	}
}
