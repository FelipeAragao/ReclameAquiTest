package com.reclameaqui.api.service;

import java.util.List;
import java.util.Optional;

import com.reclameaqui.api.documents.Company;
import com.reclameaqui.api.documents.Complain;

public interface ComplainService {

	List<Complain> listarTodos();

	Optional<Complain> listarPorId(String id);

	Complain cadastrar(Complain complain);

	Complain atualizar(Complain complain);

	void remover(String id);

	List<Complain> listarPorCidade(String cidade);

	List<Complain> listarPorEstado(String Estado);

	List<Complain> listarPorEmpresa(Company company);

	List<Complain> listarPorEmpresaCidade(Company company, String locale);

	List<Complain> listarPorEmpresaEstado(Company company, String locale);

}
