/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Haba Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager;

import android.content.*;
import android.content.pm.*;

public class App {
	
	public static String $cmdCheckRoot = "su";
	public static String $cmdRebootSystem = $cmdCheckRoot + " -c reboot";
	public static String $cmdRebootRecovery = $cmdCheckRoot + " -c reboot recovery";
	public static String $cmdRebootBootloader = $cmdCheckRoot + " -c reboot bootloader";
	
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

