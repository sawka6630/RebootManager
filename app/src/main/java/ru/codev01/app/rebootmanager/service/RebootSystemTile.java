/*!
 * @author      codev01 <codev01.private@gmail.com>
 * @copyright   Copyright (c) Haba Kudzaev, 2019
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
	public void onTileAdded() {
		super.onTileAdded();
		Tile t = getQsTile();
		t.setState(Tile.STATE_ACTIVE);
	}
	
	@Override
	public void onClick() {
		super.onClick();
		App.suCmd(App.$cmdRebootSystem);
	}
	
}
