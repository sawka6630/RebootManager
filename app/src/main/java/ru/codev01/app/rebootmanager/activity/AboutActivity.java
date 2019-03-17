/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Haba Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager.activity;

import android.content.*;
import android.net.*;
import android.os.*;
import android.preference.*;
import android.widget.*;
import ru.codev01.app.rebootmanager.*;

public class AboutActivity extends PreferenceActivity {
	
	private String $mAppVersion = "mAppVersion";
	private String $mGithubReleases = "mGithubReleases";
	private String $mCheckRoot = "mCheckRoot";
	private String $mSettings = "mSettings";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
		setPreferenceScreen(rootScreen);

		// создаем пункты (Preferences)
		// пункт с версией приложения
		Preference mAppVersion = new Preference(this);
		mAppVersion.setKey($mAppVersion);
		mAppVersion.setTitle(R.string.application_name);
		mAppVersion.setSummary(App.getApplicationVersion(this));

		// пункт Github Releases
		Preference mGithubReleases = new Preference(this);
		mGithubReleases.setKey($mGithubReleases);
		mGithubReleases.setTitle(R.string.github_releases);
		mGithubReleases.setSummary(R.string.github_releases_summary);

		// пункт Scan root
		SwitchPreference mCheckRoot = new SwitchPreference(this);
		mCheckRoot.setKey($mCheckRoot);
		mCheckRoot.setTitle(R.string.check_root);
		mCheckRoot.setSummary(R.string.check_root_summary);
		mCheckRoot.setDefaultValue(true);
		
		PreferenceCategory mSettings = new PreferenceCategory(this);
		mSettings.setKey($mSettings);
		mSettings.setTitle(R.string.settings);
		
		// порядок элементов на экране
		/* 1 */ rootScreen.addPreference(mAppVersion);
		/* 2 */ rootScreen.addPreference(mGithubReleases);
		/* 3 */ rootScreen.addPreference(mSettings);
		/* 4 */ rootScreen.addPreference(mCheckRoot);
		
	}

	@Override // реакция на нажатие пунктов
	public boolean onPreferenceTreeClick(PreferenceScreen prefScreen, Preference pref) {
		String itemKey = pref.getKey();
		if ($mAppVersion.equals(itemKey)) { // реакция на нажатие на версию приложения
			Toast.makeText(getApplicationContext(), "developed by codev01 for " + Build.MANUFACTURER, Toast.LENGTH_LONG).show();
			jumpBrowser("https://codev01.github.io");
		} else if ($mGithubReleases.equals(itemKey)) { // реакция на нажатие Github Releases
			jumpBrowser("https://github.com/codev01/RebootManager/releases");
		} return true;
	}
	
	private void jumpBrowser(String link) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setData(Uri.parse(link));
		startActivity(i);
	}
	
}
