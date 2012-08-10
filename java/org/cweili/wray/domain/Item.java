package org.cweili.wray.domain;

public class Item {

	private long item_id;
	private String item_name;
	private String permalink;
	private String description;
	private int count;
	private int item_order;
	private byte item_type;
	private long parrent_id;
	
	public static final byte CATEGORY = 0;
	public static final byte TAG = 1;
	public static final byte LINK = 2;
	public static final byte MENU = 3;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (item_id ^ (item_id >>> 32));
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
		Item other = (Item) obj;
		if (item_id != other.item_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name + ", permalink=" + permalink
				+ ", item_type=" + item_type + ", parrent_id=" + parrent_id + "]";
	}
	
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getItem_order() {
		return item_order;
	}
	public void setItem_order(int item_order) {
		this.item_order = item_order;
	}
	public byte getItem_type() {
		return item_type;
	}
	public void setItem_type(byte item_type) {
		this.item_type = item_type;
	}
	public long getParrent_id() {
		return parrent_id;
	}
	public void setParrent_id(long parrent_id) {
		this.parrent_id = parrent_id;
	}
}
