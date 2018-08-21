package com.reclameaqui.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reclameaqui.api.documents.Company;
import com.reclameaqui.api.documents.Complain;
import com.reclameaqui.api.repository.ComplainRepository;

@Service
public class ComplainServiceImpl implements ComplainService {

	@Autowired
	ComplainRepository complainRepository;

	@Autowired
	CompanyService companyService;

	@Override
	public List<Complain> listarTodos() {
		return complainRepository.findAll();
	}

	@Override
	public Optional<Complain> listarPorId(String id) {
		return complainRepository.findById(id);
	}

	@Override
	public Complain cadastrar(Complain complain) {

		Company company = new Company();
		Optional<Company> optCompany = companyService.obterCNPJ(complain.getCompany().getCnpj());

		if (!optCompany.isPresent()) {
			company = companyService.cadastrar(complain.getCompany());
		} else {
			complain.getCompany().setId(optCompany.get().getId());
			company = companyService.atualizar(complain.getCompany());
		}

		complain.setCompany(company);
		return complainRepository.save(complain);
	}

	@Override
	public Complain atualizar(Complain complain) {
		return complainRepository.save(complain);
	}

	@Override
	public void remover(String id) {
		complainRepository.deleteById(id);
	}

	@Override
	public List<Complain> listarPorCidade(String cidade) {
		return complainRepository.findByLocaleCity(cidade);
	}

	@Override
	public List<Complain> listarPorEstado(String estado) {
		return complainRepository.findByLocaleState(estado);
	}

	@Override
	public List<Complain> listarPorEmpresa(Company company) {
		return complainRepository.findByCompany(company);
	}

	@Override
	public List<Complain> listarPorEmpresaCidade(Company company, String city) {
		return complainRepository.findByCompanyAndLocaleCity(company, city);
	}

	@Override
	public List<Complain> listarPorEmpresaEstado(Company company, String state) {
		return complainRepository.findByCompanyAndLocaleState(company, state);
	}

}
