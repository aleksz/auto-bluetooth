package com.gmail.at.zhuikov.aleksandr.ab.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gmail.at.zhuikov.aleksandr.ab.service.EntryPointService;

public class BootCompletedBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		context.startService(EntryPointService.getIntent(context));
	}

}
