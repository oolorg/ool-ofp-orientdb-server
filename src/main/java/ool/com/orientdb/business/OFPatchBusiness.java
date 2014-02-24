/**
 * @author OOL 1131080355959
 * @date 2014/02/05
 * @TODO TODO
 */
package ool.com.orientdb.business;

import ool.com.orientdb.json.ConnectPatchJsonIn;
import ool.com.orientdb.json.ConnectPatchJsonOut;

/**
 * @author 1131080355959
 *
 */
public interface OFPatchBusiness {

	ConnectPatchJsonOut connectPatch(ConnectPatchJsonIn inPara);
	
	ConnectPatchJsonOut disConnectPatch(ConnectPatchJsonIn inPara);
}
