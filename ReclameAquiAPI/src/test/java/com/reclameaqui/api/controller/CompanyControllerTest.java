/**
 * 
 */
package com.reclameaqui.api.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.reclameaqui.api.documents.Company;
import com.reclameaqui.api.documents.Complain;
import com.reclameaqui.api.documents.Locale;
import com.reclameaqui.api.service.CompanyService;
import com.reclameaqui.api.util.CompanyFilter;

@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyControllerTest {

	private MockMvc mockMvc;
	private List<Company> companies;
	private Complain complain;
	String uri = "/companies";

	@InjectMocks
	CompanyController companyController;

	@Mock
	CompanyService companyService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();

		companies = Arrays.asList(new Company("5b71cc2bca9f7e3f4cffa3df", "Empresa jUnit 2", "38880471000104"),
				new Company("5b71cc60ca9f7e3f4cffa3e2", "Empresa jUnit 3", "23168569000166"));

		complain = new Complain("5b7123caca9f7e30258c0d3c", "Teste A", new Locale("Sao Luis", "Maranhao"),
				companies.get(0), "Descrição Teste");
	}

	@Test
	public final void testListarTodos() throws Exception {

		when(companyService.listarTodos()).thenReturn(companies);

		mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(status().isOk());

		verify(companyService, times(1)).listarTodos();
		verifyNoMoreInteractions(companyService);
	}

	@Test
	public final void testListarPorId() throws Exception {

		Company company = new Company();
		company = companies.get(0);

		when(companyService.listarPorId(company.getId())).thenReturn(Optional.of(company));

		mockMvc.perform(get(uri + "/{id}", company.getId()).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print()).andExpect(status().isOk());

		verify(companyService, times(1)).listarPorId(company.getId());
		verifyNoMoreInteractions(companyService);

	}

	@Test
	public final void testObterReclamacaoPorEmpresa() throws Exception {

		when(companyService.obterCNPJ(complain.getCompany().getCnpj())).thenReturn(Optional.of(complain.getCompany()));
		when(companyService.obterReclamacaoPorCnpj(complain.getCompany()))
				.thenReturn(new CompanyFilter(complain.getCompany(), 1));

		mockMvc.perform(get(uri + "/{cnpj}/cnpj", complain.getCompany().getCnpj())
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print()).andExpect(status().isOk());

		verify(companyService, times(1)).obterCNPJ(complain.getCompany().getCnpj());
		verify(companyService, times(1)).obterReclamacaoPorCnpj(complain.getCompany());
		verifyNoMoreInteractions(companyService);
	}

	@Test
	public final void testObterReclamacaoPorEmpresaCidade() throws Exception {
		when(companyService.obterCNPJ(complain.getCompany().getCnpj())).thenReturn(Optional.of(complain.getCompany()));
		when(companyService.obterReclamacaoPorCnpj(complain.getCompany(), complain.getLocale().getCity(), "cidade"))
				.thenReturn(new CompanyFilter(complain.getCompany(), 1));

		mockMvc.perform(
				get(uri + "/{cnpj}/cnpj/{city}/city", complain.getCompany().getCnpj(), complain.getLocale().getCity())
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print()).andExpect(status().isOk());

		verify(companyService, times(1)).obterCNPJ(complain.getCompany().getCnpj());
		verify(companyService, times(1)).obterReclamacaoPorCnpj(complain.getCompany(), complain.getLocale().getCity(),
				"cidade");
		verifyNoMoreInteractions(companyService);

	}

	@Test
	public final void testObterReclamacaoPorEmpresaEstado() throws Exception {
		when(companyService.obterCNPJ(complain.getCompany().getCnpj())).thenReturn(Optional.of(complain.getCompany()));
		when(companyService.obterReclamacaoPorCnpj(complain.getCompany(), complain.getLocale().getCity(), "estado"))
				.thenReturn(new CompanyFilter(complain.getCompany(), 1));

		mockMvc.perform(get(uri + "/{cnpj}/cnpj/{state}/state", complain.getCompany().getCnpj(),
				complain.getLocale().getState()).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(status().isOk());

		verify(companyService, times(1)).obterCNPJ(complain.getCompany().getCnpj());
		verify(companyService, times(1)).obterReclamacaoPorCnpj(complain.getCompany(), complain.getLocale().getState(),
				"estado");
		verifyNoMoreInteractions(companyService);
	}

}
