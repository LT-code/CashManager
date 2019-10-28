package dao;

import java.util.ArrayList;
import java.util.Map;

public class SettingDao extends Dao {
	public ArrayList<Map<String, Object>> listSetting() {
		return query("Select * from Setting;");
	}
}
