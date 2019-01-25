/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Ashab Kudzaev, 2019
 */

package ru.codev01.app.rebootmanager;

import android.content.*;
import android.content.pm.*;

public class App {

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
	
}

