package com.reclameaqui.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reclameaqui.api.documents.Company;
import com.reclameaqui.api.documents.Complain;
import com.reclameaqui.api.repository.CompanyRepository;
import com.reclameaqui.api.util.CompanyFilter;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	ComplainService complainService;

	@Override
	public Optional<Company> obterCNPJ(String cnpj) {
		return companyRepository.findByCnpj(cnpj);
	}

	@Override
	public Company cadastrar(Company complain) {
		return companyRepository.save(complain);
	}

	@Override
	public Company atualizar(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Optional<Company> listarPorId(String id) {
		return companyRepository.findById(id);
	}

	@Override
	public List<Company> listarTodos() {
		return companyRepository.findAll();
	}

	@Override
	public CompanyFilter obterReclamacaoPorCnpj(Company company) {
		List<Complain> complains = complainService.listarPorEmpresa(company);
		return new CompanyFilter(company, complains.size());
	}

	@Override
	public CompanyFilter obterReclamacaoPorCnpj(Company company, String locale, String type) {

		List<Complain> complains = new ArrayList<>();

		if (type.equals("cidade"))
			complains = complainService.listarPorEmpresaCidade(company, locale);

		if (type.equals("estado"))
			complains = complainService.listarPorEmpresaEstado(company, locale);

		return new CompanyFilter(company, complains.size());

	}

	@Override
	public void deletar(String id) {
		companyRepository.deleteById(id);
	}

}
