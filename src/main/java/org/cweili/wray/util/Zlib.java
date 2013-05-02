package org.cweili.wray.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Zlib压缩
 * 
 * @author cweili
 * @version 2013-4-2 下午5:28:20
 * 
 */
public class Zlib {

	private static final Log log = LogFactory.getLog(Zlib.class);

	/**
	 * 压缩
	 * 
	 * @param data
	 *            需要压缩的数据
	 * @return 压缩后数据
	 */
	public static byte[] compress(byte[] data) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DeflaterOutputStream dos = new DeflaterOutputStream(bos);

		try {
			dos.write(data);
			dos.close();
		} catch (IOException e) {
			log.error(e);
			return data;
		}

		byte[] result = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			log.error(e);
			return result;
		}
		return result;
	}

	/**
	 * 解压
	 * 
	 * @param data
	 *            需要解压的数据
	 * @return 解压后的数据
	 */
	public static byte[] decompress(byte[] data) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		InflaterOutputStream ios = new InflaterOutputStream(bos);

		try {
			ios.write(data);
			ios.close();
		} catch (IOException e) {
			log.error(e);
			return data;
		}

		byte[] result = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			log.error(e);
			return result;
		}
		return result;
	}
}
