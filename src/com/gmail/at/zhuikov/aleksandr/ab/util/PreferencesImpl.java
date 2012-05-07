package com.gmail.at.zhuikov.aleksandr.ab.util;

import static android.content.Context.MODE_PRIVATE;
import static android.preference.PreferenceManager.setDefaultValues;
import android.content.Context;
import android.content.SharedPreferences;

import com.gmail.at.zhuikov.aleksandr.ab.R;

public class PreferencesImpl implements Preferences {

	private static String DEFAULT_PREFERENCES_FILE_NAME = PreferencesImpl.class.getName();
	private final String fileName;
	private SharedPreferences sharedPreferences;
	private static Preferences singleton;

	private PreferencesImpl(Context context) {
		this(context, DEFAULT_PREFERENCES_FILE_NAME);
	}

	private PreferencesImpl(Context context, String fileName) {
		this.fileName = fileName;
		setDefaultValues(context, fileName, MODE_PRIVATE, R.xml.settings, false);
		sharedPreferences = context.getSharedPreferences(fileName, MODE_PRIVATE);
	}

	public static Preferences getInstance(Context context) {

		if (singleton != null) {
			return singleton;
		}

		singleton = new PreferencesImpl(context);

		return singleton;
	}

	@Override
	public String getPreferencesFileName() {
		return fileName;
	}

	@Override
	public long getConnectionTimeoutInMillis() {
		return new Long(sharedPreferences.getString(
				CONNECTION_TIMEOUT_PREFERENCE_KEY, null)) * 1000;
	}

	@Override
	public long getConnectionRetryIntervalInMillis() {
		return new Long(sharedPreferences.getString(
				CONNECTION_RETRY_INTERVAL_PREFERENCE_KEY, null)) * 1000;
	}

	@Override
	public boolean isOn() {
		return sharedPreferences.getBoolean(ON_OFF_PREFERENCE_KEY, false);
	}
}
