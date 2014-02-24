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
public class LogicalTopologyJsonGetOut extends BaseJsonOut {

	@SerializedName("result")
	private ResultData resultData;
	
	public ResultData getResultData() {
		return resultData;
	}

	public void setResultData(final ResultData resultData) {
		this.resultData = resultData;
	}
	
	public class ResultData {
		private List<Node> nodes;
		private List<LinkDevice> links;
		
		public List<Node> getNode() {
			return nodes;
		}

		public void setNode(final List<Node> nodes) {
			this.nodes = nodes;
		}
		
		public List<LinkDevice> getLink() {
			return links;
		}

		public void setLink(final List<LinkDevice> links) {
			this.links = links;
		}
	}
}
