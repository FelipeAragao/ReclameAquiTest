package com.reclameaqui.api.util;

import com.reclameaqui.api.documents.Company;

public class CompanyFilter {

	private Company compan;
	private Integer quantidade;

	public CompanyFilter(Company compan, Integer quantidade) {
		this.compan = compan;
		this.quantidade = quantidade;
	}

	public CompanyFilter() {
		super();
	}

	public Company getCompan() {
		return compan;
	}

	public void setCompan(Company compan) {
		this.compan = compan;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
