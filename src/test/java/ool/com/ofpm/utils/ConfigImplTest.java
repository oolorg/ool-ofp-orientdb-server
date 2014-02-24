/**
 * @author OOL 1131080355959
 * @date 2014/02/05
 * @TODO TODO
 */
package ool.com.ofpm.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import ool.com.orientdb.utils.ConfigImpl;

import org.junit.Test;

/**
 * @author 1131080355959
 *
 */
public class ConfigImplTest {

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#ConfigImpl()}.
	 */
	@Test
	public void testConfigImpl() {
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> data = new ArrayList<String>();
		data.add("a");
		data.add("b");
		list.add(data);
		data = new ArrayList<String>();
		data.add("c");
		data.add("d");
		list.add(data);
		data = new ArrayList<String>();
		data.add("b");
		data.add("d");
		
		boolean oneWordOverlapFlg = false;
		boolean result = false;
		for (List<String> dataSet : list) {
			oneWordOverlapFlg = false;
			for ( String str : data) {
				if (!dataSet.contains(str)) {
					break;
				} else {
					if (oneWordOverlapFlg) {
						result = true;
					} else {
						oneWordOverlapFlg = true;
					}
				}
			}
		}
		
		System.out.println(result);
	}

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#ConfigImpl(java.lang.String)}.
	 */
	@Test
	public void testConfigImplString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#ConfigImpl(org.apache.commons.configuration.Configuration)}.
	 */
	@Test
	public void testConfigImplConfiguration() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#getString(java.lang.String)}.
	 */
	@Test
	public void testGetStringString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#getString(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetStringStringString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#getInt(java.lang.String)}.
	 */
	@Test
	public void testGetIntString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#getInt(java.lang.String, int)}.
	 */
	@Test
	public void testGetIntStringInt() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#getContents()}.
	 */
	@Test
	public void testGetContents() {
		ConfigImpl config = new ConfigImpl();
		String contents = config.getContents();
		System.out.println(contents);
	}

	/**
	 * Test method for {@link ool.com.orientdb.utils.ConfigImpl#getConfiguration()}.
	 */
	@Test
	public void testGetConfiguration() {
//		fail("Not yet implemented");
	}

}
