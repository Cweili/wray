package org.cweili.wray.test;

import org.bson.types.ObjectId;

public class TestObjectId {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(new ObjectId());
		}

	}

}
