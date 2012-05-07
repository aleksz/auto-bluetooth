package com.gmail.at.zhuikov.aleksandr.ab.util;

import android.content.SharedPreferences;

/**
 * Wraps interactions with {@link SharedPreferences} and provides simpler
 * interface.
 */
public interface Preferences {

	String CONNECTION_TIMEOUT_PREFERENCE_KEY = "connection_timeout";
	String CONNECTION_RETRY_INTERVAL_PREFERENCE_KEY = "connection_retry_interval";
	String ON_OFF_PREFERENCE_KEY = "on_off";

	String getPreferencesFileName();

	long getConnectionTimeoutInMillis();

	long getConnectionRetryIntervalInMillis();

	boolean isOn();
}
