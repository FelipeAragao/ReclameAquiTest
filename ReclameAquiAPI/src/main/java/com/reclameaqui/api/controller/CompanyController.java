package com.reclameaqui.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reclameaqui.api.documents.Company;
import com.reclameaqui.api.service.CompanyService;
import com.reclameaqui.api.util.CompanyFilter;
import com.reclameaqui.api.util.Response;

@RestController
@RequestMapping(path = "/companies")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@GetMapping
	public ResponseEntity<Response<List<Company>>> listarTodos() {
		return ResponseEntity.ok(new Response<List<Company>>(companyService.listarTodos()));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Company>> listarPorId(@PathVariable String id) {

		List<String> erros = new ArrayList<String>();
		Optional<Company> optCompany = companyService.listarPorId(id);

		if (!optCompany.isPresent()) {
			erros.add("Empresa não encontrada");
			return ResponseEntity.badRequest().body(new Response<Company>(erros));
		}

		return ResponseEntity.ok(new Response<Company>(optCompany.get()));
	}

	@GetMapping(path = "/{cnpj}/cnpj")
	public ResponseEntity<Response<CompanyFilter>> obterReclamacaoPorEmpresa(@PathVariable String cnpj) {

		List<String> erros = new ArrayList<String>();
		Optional<Company> optCompany = companyService.obterCNPJ(cnpj);

		if (!optCompany.isPresent()) {
			erros.add("Empresa não encontrada");
			return ResponseEntity.badRequest().body(new Response<CompanyFilter>(erros));
		}

		return ResponseEntity.ok(new Response<CompanyFilter>(companyService.obterReclamacaoPorCnpj(optCompany.get())));
	}

	@GetMapping(path = "/{cnpj}/cnpj/{city}/city")
	public ResponseEntity<Response<CompanyFilter>> obterReclamacaoPorEmpresaCidade(@PathVariable String cnpj,
			@PathVariable String city) {
		List<String> erros = new ArrayList<String>();
		Optional<Company> optCompany = companyService.obterCNPJ(cnpj);

		if (!optCompany.isPresent()) {
			erros.add("Empresa não encontrada");
			return ResponseEntity.badRequest().body(new Response<CompanyFilter>(erros));
		}

		return ResponseEntity.ok(
				new Response<CompanyFilter>(companyService.obterReclamacaoPorCnpj(optCompany.get(), city, "cidade")));

	}

	@GetMapping(path = "/{cnpj}/cnpj/{state}/state")
	public ResponseEntity<Response<CompanyFilter>> obterReclamacaoPorEmpresaEstado(@PathVariable String cnpj,
			@PathVariable String state) {
		List<String> erros = new ArrayList<String>();
		Optional<Company> optCompany = companyService.obterCNPJ(cnpj);

		if (!optCompany.isPresent()) {
			erros.add("Empresa não encontrada");
			return ResponseEntity.badRequest().body(new Response<CompanyFilter>(erros));
		}

		return ResponseEntity.ok(
				new Response<CompanyFilter>(companyService.obterReclamacaoPorCnpj(optCompany.get(), state, "estado")));
	}

}
