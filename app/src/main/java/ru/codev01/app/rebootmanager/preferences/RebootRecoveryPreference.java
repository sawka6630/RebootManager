/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Ashab Kudzaev, 2018
 */

package ru.codev01.app.rebootmanager.preferences;

import android.content.*;
import android.preference.*;
import android.util.*;
import ru.codev01.app.rebootmanager.*;

public class RebootRecoveryPreference extends Preference {
    public RebootRecoveryPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
		setTitle(R.string.reboot_recovery);
		setSummary(R.string.reboot_recovery_summary);
    }

	@Override
	protected void onClick() {
		super.onClick();
		try {
			Runtime.getRuntime().exec("su -c reboot recovery");
		} catch (Exception e) {
            Exception exception = e;
        }
	}
}
