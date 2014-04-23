/**
 * @author OOL 1131080355959
 * @date 2014/03/04
 * @TODO 
 */
package ool.com.orientdb.json;

import java.util.ArrayList;
import java.util.List;

import ool.com.orientdb.json.LogicalTopologyJsonGetOut.ResultData;

import com.google.gson.annotations.SerializedName;

/**
 * @author 1131080355959
 *
 */
public class DeviceManagerGetDeviceInfoJsonOut extends BaseJsonOut {
	
	@SerializedName("result")
	private List<Node> resultData = new ArrayList<Node>();
	
	public List<Node> getResultData() {
		return resultData;
	}

	public void setResultData(final List<Node> resultData) {
		this.resultData = resultData;
	}
}
