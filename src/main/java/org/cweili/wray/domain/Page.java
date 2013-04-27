package org.cweili.wray.domain;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Page<T> extends PageImpl<T> implements org.springframework.data.domain.Page<T> {

	private static final long serialVersionUID = -2437806196142686379L;

	public Page(List<T> content) {
		super(content);
	}

	public Page(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public Page(org.springframework.data.domain.Page<T> page) {
		super(page.getContent(), new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
				page.getTotalElements());
	}

	public Page(List<T> content, org.springframework.data.domain.Page<T> page) {
		super(content, new PageRequest(page.getNumber(), page.getSize(), page.getSort()), page
				.getTotalElements());
	}

	/**
	 * 取得记录总数
	 * 
	 * @return
	 */
	public int getTotal() {
		return (int) super.getTotalElements();
	}

}
