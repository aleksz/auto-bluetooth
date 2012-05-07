package com.gmail.at.zhuikov.aleksandr.ab.broadcast;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;
import static android.bluetooth.BluetoothAdapter.EXTRA_STATE;
import static android.bluetooth.BluetoothAdapter.STATE_OFF;
import static android.bluetooth.BluetoothAdapter.STATE_ON;
import static android.bluetooth.BluetoothAdapter.STATE_TURNING_OFF;
import static android.bluetooth.BluetoothAdapter.STATE_TURNING_ON;
import static android.content.Context.ALARM_SERVICE;
import static android.os.SystemClock.elapsedRealtime;
import static com.gmail.at.zhuikov.aleksandr.ab.AutoBluetoothApplication.TAG;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gmail.at.zhuikov.aleksandr.ab.service.DisableBluetoothService;
import com.gmail.at.zhuikov.aleksandr.ab.service.EnableBluetoothService;
import com.gmail.at.zhuikov.aleksandr.ab.util.Preferences;
import com.gmail.at.zhuikov.aleksandr.ab.util.PreferencesImpl;

public class BluetoothStateChangedBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		int state = intent.getIntExtra(EXTRA_STATE, 0);

		Log.d(TAG, "Bluetooth " + getStateStr(state));

		Preferences preferences = PreferencesImpl.getInstance(context);

		if (!preferences.isOn()) {
			return;
		}

		AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

		if (STATE_ON == state) {
			alarmManager.set(
					ELAPSED_REALTIME_WAKEUP,
					elapsedRealtime() + preferences.getConnectionTimeoutInMillis(),
					DisableBluetoothService.getPendingIntent(context));

		} else if (STATE_OFF == state) {
			alarmManager.set(
					ELAPSED_REALTIME_WAKEUP,
					elapsedRealtime() + preferences.getConnectionRetryIntervalInMillis(),
					EnableBluetoothService.getPendingIntent(context));
		}
	}

	private String getStateStr(int state) {
		switch (state) {
			case STATE_ON: return "ON";
			case STATE_TURNING_ON: return "TURNING_ON";
			case STATE_OFF: return "OFF";
			case STATE_TURNING_OFF: return "TURNING_OFF";
			default: return null;
		}
	}
}
