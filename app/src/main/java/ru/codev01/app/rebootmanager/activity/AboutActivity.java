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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
		setPreferenceScreen(rootScreen);

		// создаем пункты (Preferences)
		// пункт с версией приложения
		Preference mAppVersion = new Preference(this);
		mAppVersion.setKey(getString(R.string.app_name));
		mAppVersion.setTitle(R.string.app_name);
		mAppVersion.setSummary(App.getAppVersion(this));

		// пункт Github Releases
		Preference mGithubReleases = new Preference(this);
		mGithubReleases.setKey(getString(R.string.github_releases));
		mGithubReleases.setTitle(R.string.github_releases);
		mGithubReleases.setSummary(R.string.github_releases_desc);

		// пункт Play Store
		Preference mPlayStore = new Preference(this);
		mPlayStore.setKey(getString(R.string.playstore));
		mPlayStore.setTitle(R.string.playstore);
		mPlayStore.setSummary(R.string.playstore_desc);

		// пункт Scan root
		SwitchPreference mCheckRoot = new SwitchPreference(this);
		mCheckRoot.setKey(getString(R.string.check_root));
		mCheckRoot.setTitle(R.string.check_root);
		mCheckRoot.setSummary(R.string.check_root_desc);
		mCheckRoot.setDefaultValue(true);
		
		// категория настройки
		PreferenceCategory mSettings = new PreferenceCategory(this);
		mSettings.setKey(getString(R.string.settings));
		mSettings.setTitle(R.string.settings);
		
		// порядок элементов на экране
		/* 1 */ rootScreen.addPreference(mAppVersion);
		/* 2 */ rootScreen.addPreference(mPlayStore);
		/* 3 */ rootScreen.addPreference(mGithubReleases);
		/* 4 */ rootScreen.addPreference(mSettings);
		/* 5 */ rootScreen.addPreference(mCheckRoot);
		
	}

	@Override // реакция на нажатие пунктов
	public boolean onPreferenceTreeClick(PreferenceScreen prefScreen, Preference pref) {
		String itemKey = pref.getKey();
		if (getString(R.string.app_name).equals(itemKey)) { // реакция на нажатие на версию приложения
			Toast.makeText(getApplicationContext(), "developed by codev01 for " + Build.MANUFACTURER, Toast.LENGTH_LONG).show();
			jumpBrowser("https://codev01.github.io");
		} else if (getString(R.string.github_releases).equals(itemKey)) { // реакция на нажатие Github Releases
			jumpBrowser("https://github.com/codev01/RebootManager/releases");
		} else if (getString(R.string.playstore).equals(itemKey)) { // реакция на нажатие Github Releases
			jumpBrowser("https://play.google.com/store/apps/details?id=ru.codev01.app.rebootmanager");
		} return true;
	}
	
	private void jumpBrowser(String l) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setData(Uri.parse(l));
		startActivity(i);
	}
	
}
