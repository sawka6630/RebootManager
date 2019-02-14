/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Haba Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager;

import android.app.*;
import android.content.*;
import android.os.*;
import android.preference.*;
import ru.codev01.app.rebootmanager.*;

import java.lang.Process;

public class RebootManager extends PreferenceActivity {
	
	private static String $mRebootSystem = "mRebootSystem";
	private static String $mRebootRecovery = "mRebootRecovery";
	private static String $mRebootBootloader = "mRebootBootloader";
	
	SharedPreferences mSharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.reboot_options);
		PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
		setPreferenceScreen(rootScreen);
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		Boolean mCheckRoot = mSharedPreferences.getBoolean("mCheckRoot", true);
		
		if (mCheckRoot == true) {
			try { // проверка прав суперпользователя
				Process exec = Runtime.getRuntime().exec(App.$cmdCheckRoot);
			} catch (Exception e) {
				Exception exception = e;
				// если права не получены: отобразим AlertDialog с сообщением
				dlgRootNoAccess();
			}
		}
		
		// создаем пункты (Preferences)
		// пункт "Система"
		Preference mRebootSystem = new Preference(this);
		mRebootSystem.setKey($mRebootSystem);
		mRebootSystem.setTitle(R.string.reboot_system);
		mRebootSystem.setSummary(R.string.reboot_system_summary);
		
		// пункт "Режим восстановления"
		Preference mRebootRecovery = new Preference(this);
		mRebootRecovery.setKey($mRebootRecovery);
		mRebootRecovery.setTitle(R.string.reboot_recovery);
		mRebootRecovery.setSummary(R.string.reboot_recovery_summary);

		// пункт "Загрузчик"
		Preference mRebootBootloader = new Preference(this);
		mRebootBootloader.setKey($mRebootBootloader);
		mRebootBootloader.setTitle(R.string.reboot_bootloader);
		mRebootBootloader.setSummary(R.string.reboot_bootloader_summary);

		// порядок элементов на экране
		/* 1 */ rootScreen.addPreference(mRebootSystem);
		/* 2 */ rootScreen.addPreference(mRebootRecovery);
		/* 3 */ rootScreen.addPreference(mRebootBootloader);
	}
	
	@Override // реакция на нажатие пунктов
	public boolean onPreferenceTreeClick(PreferenceScreen prefScreen, Preference pref) {
		String itemKey = pref.getKey();
		if ($mRebootSystem.equals(itemKey)) { // реакция на нажатие "Система"
			App.actionRebootSystem();
		} else if ($mRebootRecovery.equals(itemKey)) { // реакция на нажатие "Режим восстановления"
			App.actionRebootRecovery();
		} else if ($mRebootBootloader.equals(itemKey)) { // реакция на нажатие "Загрузчик"
			App.actionRebootBootloader();
		} return true;
	}

	// если права рут не получены
	public void dlgRootNoAccess() {
		AlertDialog.Builder builder = new AlertDialog.Builder(RebootManager.this);
		builder.setTitle(R.string.root_noaccess_title);
		builder.setMessage(R.string.root_noaccess_message);
		builder.setCancelable(false);
		builder.setIcon(R.mipmap.ic_launcher);
		builder.setPositiveButton(R.string.exit_app, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					finishAndRemoveTask();
				}
			});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
}
