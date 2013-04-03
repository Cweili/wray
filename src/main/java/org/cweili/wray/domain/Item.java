package org.cweili.wray.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Item Model
 * 
 * @author cweili
 * @version 2012-8-16 下午5:12:40
 * 
 */
@Document(collection = "item")
@CompoundIndex(def = "{'permalink': 1, 'itemType': 1}", unique = true)
public class Item implements Serializable, Cloneable, Comparable<Item> {

	private static final long serialVersionUID = 644800170130656153L;
	@Id
	private String itemId = "";
	private String itemName = "";
	private String permalink = "";
	private String description = "";
	private int count = 0;
	private byte itemOrder = 0;
	private byte itemType = TYPE_CATEGORY;
	private long parrentId = 0;
	@Indexed
	private byte stat = STAT_ON;

	public static final byte TYPE_CATEGORY = 0;
	public static final byte TYPE_TAG = 1;
	public static final byte TYPE_LINK = 2;
	public static final byte TYPE_MENU = 3;

	public static final byte STAT_OFF = 0;
	public static final byte STAT_ON = 1;
	public static final byte STAT_SELECTED = 2;

	public Item() {
		super();
	}

	/**
	 * @param itemId
	 * @param itemName
	 * @param permalink
	 * @param description
	 * @param count
	 * @param itemOrder
	 * @param itemType
	 * @param parrentId
	 * @param stat
	 */
	public Item(String itemId, String itemName, String permalink, String description, int count,
			byte itemOrder, byte itemType, long parrentId, byte stat) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.permalink = permalink;
		this.description = description;
		this.count = count;
		this.itemOrder = itemOrder;
		this.itemType = itemType;
		this.parrentId = parrentId;
		this.stat = stat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", permalink=" + permalink
				+ ", description=" + description + ", count=" + count + ", itemOrder=" + itemOrder
				+ ", itemType=" + itemType + ", parrentId=" + parrentId + ", stat=" + stat + "]";
	}

	@Override
	public int compareTo(Item item) {
		if (this.getItemOrder() > item.getItemOrder()) {
			return 1;
		} else if (this.getItemOrder() < item.getItemOrder()) {
			return -1;
		}
		return 0;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public byte getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(byte itemOrder) {
		this.itemOrder = itemOrder;
	}

	public byte getItemType() {
		return itemType;
	}

	public void setItemType(byte itemType) {
		this.itemType = itemType;
	}

	public long getParrentId() {
		return parrentId;
	}

	public void setParrentId(long parrentId) {
		this.parrentId = parrentId;
	}

	public byte getStat() {
		return stat;
	}

	public void setStat(byte stat) {
		this.stat = stat;
	}

}