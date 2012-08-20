package org.cweili.wray.util;

import java.util.ArrayList;

/**
 * 分页导航
 * 
 * @author cweili
 * @version 2012-8-16 下午5:33:14
 * 
 */
public class Paginator {

	private int total = 0;
	private int limit = 0;
	private int current = 0;
	private int last = 0;
	private int previous = 0;
	private int next = 0;

	private boolean pageBarOn = false;
	private boolean previousOn = false;
	private boolean nextOn = false;

	private ArrayList<Integer> pageList = new ArrayList<Integer>();

	public Paginator() {

	}

	/**
	 * @param total
	 * @param limit
	 * @param current
	 */
	public Paginator(int total, int limit, int current) {
		this.total = total;
		this.limit = limit;
		this.current = current;
		calculate();
	}

	/**
	 * 计算分页
	 */
	public void calculate() {
		limit = limit > 0 ? limit : 1;
		current = current > 0 ? current : 1;
		total = total > 0 ? total : 1;
		last = round(total, limit);
		if (total > limit) {
			pageBarOn = true;

			limit = limit > 0 ? limit : 1;
			current = current > 0 ? current : 1;
			last = round(total, limit);
			int fix = last - current > 4 ? 4 : 9 - last + current;
			int begin = current - fix < 1 ? 1 : current - fix;
			int end = begin + 9 > last ? last : begin + 9;
			for (int i = begin; i <= end; ++i) {
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

	public int round(int a, int b) {
		return (((double) a / (double) b) > (a / b) ? a / b + 1 : a / b);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public ArrayList<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Integer> pageList) {
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
