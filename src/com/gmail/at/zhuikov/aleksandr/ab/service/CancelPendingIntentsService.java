package com.gmail.at.zhuikov.aleksandr.ab.service;

import static android.app.PendingIntent.getService;
import static com.gmail.at.zhuikov.aleksandr.ab.AutoBluetoothApplication.TAG;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CancelPendingIntentsService extends IntentService {

	public CancelPendingIntentsService() {
		super(CancelPendingIntentsService.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "Cancelling pending intents");
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.cancel(EnableBluetoothService.getPendingIntent(this));
		alarmManager.cancel(DisableBluetoothService.getPendingIntent(this));
	}

	public static Intent getIntent(Context context) {
		return new Intent(context, CancelPendingIntentsService.class);
	}

	public static PendingIntent getPendingIntent(Context context) {
		return getService(context, 0, new Intent(context, CancelPendingIntentsService.class), 0);
	}
}