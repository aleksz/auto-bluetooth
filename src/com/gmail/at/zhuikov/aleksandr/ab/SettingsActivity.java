package com.gmail.at.zhuikov.aleksandr.ab;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.TextUtils.isEmpty;
import static com.gmail.at.zhuikov.aleksandr.ab.util.Preferences.CONNECTION_RETRY_INTERVAL_PREFERENCE_KEY;
import static com.gmail.at.zhuikov.aleksandr.ab.util.Preferences.CONNECTION_TIMEOUT_PREFERENCE_KEY;
import static com.gmail.at.zhuikov.aleksandr.ab.util.Preferences.ON_OFF_PREFERENCE_KEY;

import com.gmail.at.zhuikov.aleksandr.ab.service.CancelPendingIntentsService;
import com.gmail.at.zhuikov.aleksandr.ab.service.EntryPointService;
import com.gmail.at.zhuikov.aleksandr.ab.util.Preferences;
import com.gmail.at.zhuikov.aleksandr.ab.util.PreferencesImpl;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity implements
		SharedPreferences.OnSharedPreferenceChangeListener {

	private Preferences preferences;
	private EditTextPreference connectionTimeoutPreference;
	private EditTextPreference connectionRetryIntervalPreference;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		preferences = PreferencesImpl.getInstance(this);

		getPreferenceManager().setSharedPreferencesName(preferences.getPreferencesFileName());
		addPreferencesFromResource(R.xml.settings);

		connectionTimeoutPreference = (EditTextPreference) getPreferenceScreen()
				.findPreference(CONNECTION_TIMEOUT_PREFERENCE_KEY);
		connectionTimeoutPreference.getEditText().setInputType(TYPE_CLASS_NUMBER);

		connectionRetryIntervalPreference = (EditTextPreference) getPreferenceScreen()
				.findPreference(CONNECTION_RETRY_INTERVAL_PREFERENCE_KEY);
		connectionRetryIntervalPreference.getEditText().setInputType(TYPE_CLASS_NUMBER);
	}

	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		updatePreferenceSummaries();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		updatePreferenceSummaries();

		if (ON_OFF_PREFERENCE_KEY.equals(key)) {
			if (preferences.isOn()) {
				startService(EntryPointService.getIntent(this));
			} else {
				startService(CancelPendingIntentsService.getIntent(this));
			}
		}
	}

	private void updatePreferenceSummaries() {
		if (!isEmpty(connectionTimeoutPreference.getText())) {
			connectionTimeoutPreference.setSummary(connectionTimeoutPreference.getText() + "s");
		}

		if (!isEmpty(connectionRetryIntervalPreference.getText())) {
			connectionRetryIntervalPreference.setSummary(connectionRetryIntervalPreference.getText() + "s");
		}
	}

	EditTextPreference getConnectionTimeoutPreference() {
		return connectionTimeoutPreference;
	}

	EditTextPreference getConnectionRetryIntervalPreference() {
		return connectionRetryIntervalPreference;
	}
}