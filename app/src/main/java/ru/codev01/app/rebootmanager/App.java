/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Ashab Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.view.*;
import android.widget.*;

public class App {
	
	public static String $cmdRebootSystem = "su -c reboot";
	public static String $cmdRebootRecovery = "su -c reboot recovery";
	public static String $cmdRebootBootloader = "su -c reboot bootloader";
	public static String $cmdCheckRoot = "su";
	
	public static String getApplicationVersion(Context ctx) {
		try {
			PackageManager pacman = ctx.getPackageManager();
			PackageInfo pacinf = pacman.getPackageInfo(ctx.getPackageName(), 0);
			String app_version_name = pacinf.versionName;
			int app_version_code = pacinf.versionCode;
			return app_version_name + "." + app_version_code;
		} catch (Exception exc) {
			exc.printStackTrace();
			return "log: error#App.java>getApplicationVersion();";
		}
	}

	public static String getApplicationPackage(Context ctx) {
		try {
			PackageManager pacman = ctx.getPackageManager();
			PackageInfo pacinf = pacman.getPackageInfo(ctx.getPackageName(), 0);
			String app_package_name = pacinf.packageName;
			return app_package_name;
		} catch (Exception exc) {
			exc.printStackTrace();
			return "log: error#App.java>getApplicationPackage();";
		}
	}
	
	public static void actionRebootSystem() {
		try {
			Runtime.getRuntime().exec($cmdRebootSystem);
		} catch (Exception exc) {
            exc.printStackTrace();
        }
	}

	public static void actionRebootRecovery() { 
		try {
			Runtime.getRuntime().exec($cmdRebootRecovery);
		} catch (Exception exc) {
            exc.printStackTrace();
        }
	}

	public static void actionRebootBootloader() {
		try {
			Runtime.getRuntime().exec($cmdRebootBootloader);
		} catch (Exception exc) {
            exc.printStackTrace();
        }
	}
	
}

