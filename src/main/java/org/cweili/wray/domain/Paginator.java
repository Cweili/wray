package org.cweili.wray.domain;

import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.util.Function;

/**
 * 分页导航条
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:33:14
 * 
 */
public class Paginator {

	private long total = 0;
	private long size = 0;
	private long current = 0;
	private long last = 0;
	private long previous = 0;
	private long next = 0;

	private boolean pageBarOn = false;
	private boolean previousOn = false;
	private boolean nextOn = false;

	private List<Long> pageList = new ArrayList<Long>();

	public Paginator() {

	}

	/**
	 * @param total
	 * @param size
	 * @param current
	 */
	public Paginator(long total, long size, long current) {
		this.total = total;
		this.size = size;
		this.current = current;
		calculate();
	}

	/**
	 * 计算分页
	 */
	public void calculate() {
		size = size > 0 ? size : 1;
		current = current > 0 ? current : 1;
		total = total > 0 ? total : 1;
		last = Function.round(total, size);
		if (total > size) {
			pageBarOn = true;

			size = size > 0 ? size : 1;
			current = current > 0 ? current : 1;
			last = Function.round(total, size);
			long fix = last - current > 4 ? 4 : 9 - last + current;
			long begin = current - fix < 1 ? 1 : current - fix;
			long end = begin + 9 > last ? last : begin + 9;
			for (long i = begin; i <= end; ++i) {
				pageList.add(i);
			}

			if (current > 1) {
				previous = current - 1;
				previousOn = true;
			}
			if (current < last) {
				next = current + 1;
				nextOn = true;
			}

		}
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getLast() {
		return last;
	}

	public void setLast(long last) {
		this.last = last;
	}

	public long getPrevious() {
		return previous;
	}

	public void setPrevious(long previous) {
		this.previous = previous;
	}

	public long getNext() {
		return next;
	}

	public void setNext(long next) {
		this.next = next;
	}

	public List<Long> getPageList() {
		return pageList;
	}

	public void setPageList(List<Long> pageList) {
		this.pageList = pageList;
	}

	public boolean isPageBarOn() {
		return pageBarOn;
	}

	public void setPageBarOn(boolean pageBarOn) {
		this.pageBarOn = pageBarOn;
	}

	public boolean isPreviousOn() {
		return previousOn;
	}

	public void setPreviousOn(boolean previousOn) {
		this.previousOn = previousOn;
	}

	public boolean isNextOn() {
		return nextOn;
	}

	public void setNextOn(boolean nextOn) {
		this.nextOn = nextOn;
	}
}
