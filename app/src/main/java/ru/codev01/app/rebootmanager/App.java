/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Haba Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import java.io.*;
import ru.codev01.app.rebootmanager.*;

public class App {
	
	public static String $cmdRebootSystem = "su -c reboot";
	public static String $cmdRebootRecovery = "su -c reboot recovery";
	public static String $cmdRebootBootloader = "su -c reboot bootloader";
	public static String $cmdCheckRoot = "su";
	
	public static String getAppVersion(Context c) {
		try {
			PackageManager pm = c.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(c.getPackageName(), 0);
			String verName = pi.versionName;
			int verCode = pi.versionCode;
			return verName + "." + verCode;
		} catch (Exception exc) {
			exc.printStackTrace();
			return "error: getAppVersion();";
		}
	}

	public static void suCmd(String cmd) {
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (Exception exc) {
            exc.printStackTrace();
        }
	}
	
}

