/**
 * @author OOL 1131080355959
 * @date 2014/02/05
 * @TODO TODO
 */
package ool.com.orientdb.utils;

/**
 * @author 1131080355959
 *
 */
public class Definition {
	public static final String DEFAULT_PROPERTIY_FILE = "orient.properties";
	
	public static final String QUERY_SPLIT_WORD_COMMA = ",";
	
	public static final String CONFIG_KEY_DB_URL = "db.url";
	public static final String CONFIG_KEY_DB_USER= "db.user";
	public static final String CONFIG_KEY_DB_PASSWORD = "db.password";
	public static final String DB_DEFAULT = "admin";
	
	public static final int HTTP_STATUS_CODE_OK = 200;
	public static final int HTTP_STATUS_CODE_CREATED = 201;
	public static final int HTTP_STATUS_CODE_BAD_REQUEST = 400;
	public static final int HTTP_STATUS_CODE_NOT_FOUND = 404;
	public static final int HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
	
	public static final int DIJKSTRA_WEIGHT_NO_ROUTE = 100;
	public static final int DIJKSTRA_WEIGHT_AVAILABLE_ROUTE = 1;
	
	public static final String SQL_GET_DEVICE = "select from node where name='%s'";
	public static final String SQL_GET_CONNECTED_NODE = "select from (traverse * from %s) where @class='node' and $depth=6";
	public static final String SQL_GET_PATCHPORT_RID = "select from (traverse * from %s) where @class='port' and $depth=4";
	public static final String SQL_GET_PATCH_WIRING = "select from patchWiring where out = %s and in = %s";
	//public static final String SQL_GET_SHORTEST_PATH = "select shortestPath(%s,%s,'BOTH').asString()";
	public static final String SQL_GET_DIJKSTRA_PATH = "select dijkstra(%s,%s,'weight').asString() from V limit 1";
	public static final String SQL_UPDATE_WEIGHT_TO_LINK = "update link set weight = %s where out = %s and in = %s";
	public static final String SQL_INSERT_PATCH_WIRING = "insert into patchWiring(out, in, parent, outDeviceName, inDeviceName) values (%s, %s, %s, '%s', '%s')";
	public static final String SQL_GET_PATCH_WIRING2 = "select from patchWiring where outDeviceName = '%s' and inDeviceName = '%s'";
	public static final String SQL_GET_PATCH_CONNECTED_NODE = "select from patchWiring";
	public static final String SQL_DELETE_PATCH_WIRING = "delete from patchWiring where outDeviceName = '%s' and inDeviceName = '%s'";
	public static final String SQL_GET_PORT = "select from port where @RID = %s";

}
