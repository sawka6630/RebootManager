/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Ashab Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager.service;

import android.app.*;
import android.content.*;
import android.net.*;
import android.service.quicksettings.*;
import android.util.*;
import android.widget.*;
import ru.codev01.app.rebootmanager.*;

public class RebootSystemTile extends TileService {
	@Override
	public void onClick() {
		super.onClick();
		try {
            Process exec = Runtime.getRuntime().exec(App.$cmdRebootSystem);
        } catch (Exception e) {
            Exception exception = e;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.root_noaccess_title);
			builder.setMessage(R.string.root_noaccess_message);
			builder.setIcon(R.mipmap.ic_launcher);
			builder.setCancelable(false);
			builder.setPositiveButton(R.string.exit_app, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// wtf
					}
				});
			showDialog(builder.create());
        }
	}
}