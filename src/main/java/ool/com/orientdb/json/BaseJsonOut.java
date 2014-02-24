/**
 * @author OOL 1131080355959
 * @date 2014/02/13
 * @TODO 
 */
package ool.com.orientdb.json;

import com.google.gson.annotations.SerializedName;

/**
 * @author 1131080355959
 *
 */
public class BaseJsonOut {
	@SerializedName("status")
	private int statusCode;
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode (final int statusCode) {
		this.statusCode = statusCode;
	}

}
