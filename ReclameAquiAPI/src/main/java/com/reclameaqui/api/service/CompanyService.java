package com.reclameaqui.api.service;

import java.util.List;
import java.util.Optional;

import com.reclameaqui.api.documents.Company;
import com.reclameaqui.api.util.CompanyFilter;

public interface CompanyService {

	Optional<Company> obterCNPJ(String cnpj);

	Optional<Company> listarPorId(String id);

	Company cadastrar(Company company);

	Company atualizar(Company company);

	List<Company> listarTodos();

	CompanyFilter obterReclamacaoPorCnpj(Company company);

	CompanyFilter obterReclamacaoPorCnpj(Company company, String locale, String type);

	void deletar(String id);

}
