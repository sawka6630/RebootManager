/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Ashab Kudzaev, 2018
 */

package ru.codev01.app.rebootmanager.preferences;

import android.content.*;
import android.preference.*;
import android.util.*;
import ru.codev01.app.rebootmanager.*;

public class RebootBootloaderPreference extends Preference {
    public RebootBootloaderPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
		setTitle(R.string.reboot_bootloader);
		setSummary(R.string.reboot_bootloader_summary);
    }
	
	@Override
	protected void onClick() {
		super.onClick();
		try {
			Runtime.getRuntime().exec("su -c reboot bootloader");
		} catch (Exception e) {
            Exception exception = e;
        }
	}
}
