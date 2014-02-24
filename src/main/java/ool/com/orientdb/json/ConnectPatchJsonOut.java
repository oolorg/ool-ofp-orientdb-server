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
public class ConnectPatchJsonOut extends BaseJsonOut {

	@SerializedName("result")
	private List<LinkPatchPort> links;

	public List<LinkPatchPort> getLinks() {
		return links;
	}

	public void setLinks(final List<LinkPatchPort> links) {
		this.links = links;
	}
	
	
}
