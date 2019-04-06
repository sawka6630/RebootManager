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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
		
		setTitle(R.string.reboot_options);
		setPreferenceScreen(rootScreen);
		
		SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		Boolean mCheckRoot = mSharedPrefs.getBoolean(getString(R.string.check_root), true);
		
		// если настройка "Проверка рут" включена, то будет проходить проверка
		// рут-доступа на устро-ве.
		if (mCheckRoot) {
			checkRootAccess();
		}
		
		// создаем пункты (Preferences)
		// пункт "Система"
		Preference mRebootSystem = new Preference(this);
		mRebootSystem.setKey(getString(R.string.reboot_system));
		mRebootSystem.setTitle(R.string.reboot_system);
		mRebootSystem.setSummary(R.string.reboot_system_desc);
		
		// пункт "Режим восстановления"
		Preference mRebootRecovery = new Preference(this);
		mRebootRecovery.setKey(getString(R.string.reboot_recovery));
		mRebootRecovery.setTitle(R.string.reboot_recovery);
		mRebootRecovery.setSummary(R.string.reboot_recovery_desc);

		// пункт "Загрузчик"
		Preference mRebootBootloader = new Preference(this);
		mRebootBootloader.setKey(getString(R.string.reboot_bootloader));
		mRebootBootloader.setTitle(R.string.reboot_bootloader);
		mRebootBootloader.setSummary(R.string.reboot_bootloader_desc);

		// порядок элементов на экране
		/* 1 */ rootScreen.addPreference(mRebootSystem);
		/* 2 */ rootScreen.addPreference(mRebootRecovery);
		/* 3 */ rootScreen.addPreference(mRebootBootloader);
		
	}
	
	@Override // реакция на нажатие пунктов
	public boolean onPreferenceTreeClick(PreferenceScreen prefScreen, Preference pref) {
		String itemKey = pref.getKey();
		if (getString(R.string.reboot_system).equals(itemKey)) { // реакция на нажатие "Система"
			App.suCmd(App.$cmdRebootSystem);
		} else if (getString(R.string.reboot_recovery).equals(itemKey)) { // реакция на нажатие "Режим восстановления"
			App.suCmd(App.$cmdRebootRecovery);
		} else if (getString(R.string.reboot_bootloader).equals(itemKey)) { // реакция на нажатие "Загрузчик"
			App.suCmd(App.$cmdRebootBootloader);
		} return true;
	}
	
	// проверка на наличие рут
	void checkRootAccess() {
		try { // проверка прав суперпользователя
			Process exec = Runtime.getRuntime().exec(App.$cmdCheckRoot);
		} catch (Exception e) {
			// если права не получены: отобразим AlertDialog с сообщением
			dlgRootNoAccess();
		}
	}

	// если права рут не получены
	void dlgRootNoAccess() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle(R.string.root_noaccess);
		b.setMessage(R.string.root_noaccess_desc);
		b.setCancelable(false);
		b.setIcon(R.mipmap.ic_launcher);
		b.setPositiveButton(R.string.exit_app, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface di, int id) {
					finishAndRemoveTask();
				}
			});
		AlertDialog a = b.create();
		a.show();
	}
	
}
