/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Ashab Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.preference.*;
import android.view.*;
import ru.codev01.app.rebootmanager.*;

import java.lang.Process;
import android.text.*;
import android.widget.*;

public class RebootManager extends PreferenceActivity
 {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setTitle(R.string.reboot_options);
		addPreferencesFromResource(R.xml.preference_main);
		try {
            Process exec = Runtime.getRuntime().exec("su");
        } catch (Exception e) {
            Exception exception = e;
			dlgRootNoAccess(RebootManager.this);
        }
	}
	
	void init() { finishAndRemoveTask(); }
	
	public void dlgRootNoAccess(RebootManager rebootManager) {
		AlertDialog.Builder builder = new AlertDialog.Builder(RebootManager.this);
		builder.setTitle(R.string.root_noaccess_title);
		builder.setMessage(R.string.root_noaccess_message);
		builder.setCancelable(false);
		builder.setPositiveButton(R.string.exit_app, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					init();
				}
			});
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	protected void onStop() {
		super.onStop();
		init();
	}

	@Override
	protected void onPause() {
		super.onPause();
		init();
	}
}
