package org.cweili.wray.domain;

/**
 * Item Model
 * 
 * @author cweili
 * @version 2012-8-16 下午5:12:40
 *
 */
public class Item {

	private long itemId = 0;
	private String itemName = "";
	private String permalink = "";
	private String description = "";
	private int count = 0;
	private byte itemOrder = 0;
	private byte itemType = Item.TYPE_CATEGORY;
	private long parrentId = 0;
	private byte stat = Item.STAT_ON;
	
	public static final byte TYPE_CATEGORY = 0;
	public static final byte TYPE_TAG = 1;
	public static final byte TYPE_LINK = 2;
	public static final byte TYPE_MENU = 3;
	
	public static final byte STAT_ON = 1;
	public static final byte STAT_OFF = 0;
	
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
	public Item(long itemId, String itemName, String permalink, String description, int count,
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
		result = prime * result + (int) (itemId ^ (itemId >>> 32));
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
		if (itemId != other.itemId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", permalink=" + permalink
				+ ", description=" + description + ", count=" + count + ", itemOrder=" + itemOrder
				+ ", itemType=" + itemType + ", parrentId=" + parrentId + ", stat=" + stat + "]";
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
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