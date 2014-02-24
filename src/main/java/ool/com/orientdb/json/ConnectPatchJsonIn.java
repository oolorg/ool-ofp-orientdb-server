/**
 * @author OOL 1131080355959
 * @date 2014/02/06
 * @TODO TODO
 */
package ool.com.orientdb.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author 1131080355959
 *
 */
public class ConnectPatchJsonIn {

	@SerializedName("deviceName")
	private List<String> deviceNameList;

	public List<String> getLinks() {
		return deviceNameList;
	}

	public void setLinks(final List<String> deviceNameList) {
		this.deviceNameList = deviceNameList;
	}
	
	
}
