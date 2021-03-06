/**
 * 
 */
package org.cweili.wray.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 配置
 * 
 * @author Cweili
 * @version 2013-3-28 下午4:29:04
 * 
 */
@Document(collection = "config")
public class Config implements Serializable, Cloneable {

	private static final long serialVersionUID = 5918198530378494132L;

	/**
	 * 键
	 */
	@Id
	private String key;

	/**
	 * 值
	 */
	private String value;

	public Config() {
		this("", "");
	}

	public Config(String key, int value) {
		this(key, String.valueOf(value));
	}

	public Config(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Config: " + key + " => " + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Config other = (Config) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            要设置的 key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            要设置的 value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
