/**
 * 
 */
package org.cweili.wray.conversion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author cweili
 * @version 2013-3-28 下午5:12:44
 * 
 */
public class SQL2JSON {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader bw = new BufferedReader(new FileReader(StringUtils.substring(
					new SQL2JSON().getClass().getResource("/").toString(), 6) + "config.txt"));
			String line;
			String[] params;
			while ((line = bw.readLine()) != null) {
				params = line.split("'");
				System.out.println("db.getCollection(\"config\").insert({\"_id\":\"" + params[1]
						+ "\",\"_class\":\"org.cweili.wray.domain.Config\",\"value\":\""
						+ params[3] + "\"});");
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
