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
	public static final int HTTP_STATUS_CODE_FORBIDDEN = 403;
	public static final int HTTP_STATUS_CODE_NOT_FOUND = 404;
	public static final int HTTP_STATUS_CODE_CONFLICT = 409;
	public static final int HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR = 500;

	public static final int DIJKSTRA_WEIGHT_NO_ROUTE = 100;
	public static final int DIJKSTRA_WEIGHT_AVAILABLE_ROUTE = 1;

	public static final String NODE_TYPE_SERVER = "Server";
	public static final String NODE_TYPE_SWITCH = "Switch";

	public static final int DB_RESPONSE_STATUS_OK = 200;
	public static final int DB_RESPONSE_STATUS_EXIST = 210;
	public static final int DB_RESPONSE_STATUS_NOT_FOUND = 404;
	public static final int DB_RESPONSE_STATUS_USED = 220;
	public static final int DB_RESPONSE_STATUS_FORBIDDEN = 403;
	
	public static final String SQL_NODE_KEY_NAME = "name";
	public static final String SQL_NODE_KEY_TYPE = "type";
	public static final String SQL_NODE_KEY_FLAG = "ofpFlag";

	// select
	public static final String SQL_GET_DEVICE = "select from node where name='%s'";
	public static final String SQL_GET_DEVICE_LIST = "select from node %s";
	public static final String SQL_GET_CONNECTED_NODE = "select from (traverse * from %s) where @class='node' and $depth=6";
	public static final String SQL_GET_PATCHPORT_RID = "select from (traverse * from %s) where @class='port' and $depth=4";
	public static final String SQL_GET_PATCH_WIRING = "select from patchWiring where out = %s and in = %s";
	public static final String SQL_GET_DIJKSTRA_PATH = "select dijkstra(%s,%s,'weight').asString() from V limit 1";
	public static final String SQL_GET_PATCH_WIRING2 = "select from patchWiring where outDeviceName = '%s' and inDeviceName = '%s'";
	public static final String SQL_GET_PATCH_CONNECTED_NODE = "select from patchWiring";
	public static final String SQL_GET_PORT = "select from port where @RID = %s";
	public static final String SQL_GET_PORT_INFO = "select from port where name = '%s' and deviceName = '%s'";
	public static final String SQL_GET_PORT_INFO2 = "select from port where number = %s and deviceName = '%s'";
	public static final String SQL_GET_LINK = "select from link where out = %s and in = %s";

	public static final String SQL_GET_CONNECTED_LINK = "select from link where in = %s";
	public static final String SQL_IS_HAD_PATCH_WIRING = "select from patchWiring where parent = %s";
	public static final String SQL_IS_CONNECTED_PATCH_WIRING = "select from patchWiring where out = %s or in = %s";
	public static final String SQL_IS_CONTAINS_PATCH_WIRING = "select from patchWiring where outDeviceName = '%s' or inDeviceName = '%s'";
	public static final String SQL_GET_PATCH_CONNECTED_DEVICE_NAME = "select from patchWiring where inDeviceName = '%s'";
	
	public static final String SQL_GET_PORT_LIST = "select from port where deviceName = '%s'";

	// insert
	public static final String SQL_INSERT_PATCH_WIRING = "insert into patchWiring(out, in, parent, outDeviceName, inDeviceName) values (%s, %s, %s, '%s', '%s')";
	public static final String SQL_INSERT_NODE = "create vertex node set name = '%s', type = '%s', ofpFlag = %s";
	public static final String SQL_INSERT_PORT = "create vertex port set name = '%s', number = %s, type = '%s', deviceName = '%s'";
	public static final String SQL_INSERT_LINK = "create edge link from %s to %s set weight = 1";

	// delete
	public static final String SQL_DELETE_PATCH_WIRING = "delete from patchWiring where outDeviceName = '%s' and inDeviceName = '%s'";
	public static final String SQL_DELETE_LINK = "delete edge link where out = %s and in = %s";
	public static final String SQL_DELETE_LINK_CONNECTED_PORT = "delete edge link where out = %s or in = %s";
	public static final String SQL_DELETE_PORT = "delete vertex port where name = '%s' and deviceName = '%s'";
	public static final String SQL_DELETE_PORT_DEViCE_NAME = "delete vertex port where deviceName = '%s'";
	public static final String SQL_DELETE_NODE = "delete vertex node where name = '%s'";

	// update
	public static final String SQL_UPDATE_WEIGHT_TO_LINK = "update link set weight = %s where out = %s and in = %s";
	public static final String SQL_UPDATE_NODE = "update node set name = '%s', ofpFlag = %s where @RID = %s";
	public static final String SQL_UPDATE_PORT_DEVICE_NAME = "update port set deviceName = '%s' where deviceName = '%s'";
	public static final String SQL_UPDATE_PATCH_WIRING_IN_DEVICE  = "update patchWiring set  inDeviceName = '%s' where  inDeviceName = '%s'";
	public static final String SQL_UPDATE_PATCH_WIRING_OUT_DEVICE = "update patchWiring set outDeviceName = '%s' where outDeviceName = '%s'";
	public static final String SQL_UPDATE_PORT = "update port set name = '%s', number = %s, type = '%s' where @RID = %s";

	public static final String OFP_FLAG_TRUE  = "true";
	public static final String OFP_FLAG_FALSE = "false";



}
