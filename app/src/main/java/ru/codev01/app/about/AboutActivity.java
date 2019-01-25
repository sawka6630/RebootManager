/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Ashab Kudzaev, 2019
 */

package ru.codev01.app.about;

import android.content.*;
import android.net.*;
import android.os.*;
import android.preference.*;
import ru.codev01.app.rebootmanager.*;

public class AboutActivity extends PreferenceActivity
{
	Preference appVersion, appDeveloper, appLinkPlayStore;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference_about);
		
		appVersion = findPreference("appVersion");
		appVersion.setTitle(R.string.app_name);
		appVersion.setSummary(App.getApplicationVersion(AboutActivity.this));
		
		appDeveloper = findPreference("appDeveloper");
		appDeveloper.setTitle(R.string.app_developer);
		appDeveloper.setSummary("codev01");
		
		appLinkPlayStore = findPreference("appLinkPlayStore");
		appLinkPlayStore.setTitle(R.string.app_link_playstore);
		
	}
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen prefScreen, Preference pref) {
		String itemKey = pref.getKey();

		if ("appLinkPlayStore".equals(itemKey)) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("https://play.google.com/store/apps/details?id" + App.getApplicationPackage(AboutActivity.this)));      
			startActivity(intent);
		} return true;
	}
}
