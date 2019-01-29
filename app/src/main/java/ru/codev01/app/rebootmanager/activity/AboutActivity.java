/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Ashab Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager.activity;

import android.content.*;
import android.net.*;
import android.os.*;
import android.preference.*;
import android.widget.*;
import ru.codev01.app.rebootmanager.*;

public class AboutActivity extends PreferenceActivity
{
	private static String $mAppVersion = "mAppVersion";
	private static String $mGithubReleases = "mGithubReleases";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
		setPreferenceScreen(rootScreen);

		// создаем пункты (Preferences)
		// пункт с версией приложения
		Preference mAppVersion = new Preference(this);
		mAppVersion.setKey($mAppVersion);
		mAppVersion.setTitle(R.string.app_name);
		mAppVersion.setSummary(App.getApplicationVersion(AboutActivity.this));

		// пункт Github Releases
		Preference mGithubReleases = new Preference(this);
		mGithubReleases.setKey($mGithubReleases);
		mGithubReleases.setTitle(R.string.github_releases);
		mGithubReleases.setSummary(R.string.github_releases_summary);

		// порядок элементов на экране
		/* 1 */ rootScreen.addPreference(mAppVersion);
		/* 2 */ rootScreen.addPreference(mGithubReleases);
	}

	@Override // реакция на нажатие пунктов
	public boolean onPreferenceTreeClick(PreferenceScreen prefScreen, Preference pref) {
		String itemKey = pref.getKey();
		if ($mAppVersion.equals(itemKey)) { // реакция на нажатие на версию приложения
			Toast mToast = Toast.makeText(getApplicationContext(), "You use a stable version!", Toast.LENGTH_LONG);
			mToast.show();
		} else if ($mGithubReleases.equals(itemKey)) { // реакция на нажатие Github Releases
			Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/codev01/RebootManager"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
		} return true;
	}
}
