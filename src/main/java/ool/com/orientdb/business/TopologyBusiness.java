/**
 * @author OOL 1131080355959
 * @date 2014/02/05
 * @TODO TODO
 */
package ool.com.orientdb.business;

import ool.com.orientdb.json.LogicalTopologyJsonGetOut;

/**
 * @author 1131080355959
 *
 */
public interface TopologyBusiness {

	public LogicalTopologyJsonGetOut getTopology(String deviceNames);
	
	public String createWiring(String params);
	
	public String deleteWiring(String params);
}
