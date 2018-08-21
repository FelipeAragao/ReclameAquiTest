/**
 * 
 */
package com.reclameaqui.api.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reclameaqui.api.documents.Company;
import com.reclameaqui.api.documents.Complain;
import com.reclameaqui.api.documents.Locale;
import com.reclameaqui.api.service.ComplainService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ComplainControllerTest {

	private MockMvc mockMvc;
	private List<Complain> complains;
	String uri = "/complains";

	@InjectMocks
	private ComplainController complainController;

	@Mock
	private ComplainService complainService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(complainController).build();
		complains = Arrays.asList(
				new Complain("5b7123caca9f7e30258c0d3c", "Teste A", new Locale("Sao Luis", "Maranhao"),
						new Company("5b71cc2bca9f7e3f4cffa3df", "Empresa jUnit 2", "38880471000104"),
						"Descrição Teste"),
				new Complain("5b7123e4ca9f7e30258c0d3e", "Teste B", new Locale("Sao Luis", "Maranhao"),
						new Company("5b71cc60ca9f7e3f4cffa3e2", "Empresa jUnit 3", "23168569000166"),
						"Descrição Teste 2"));

	}

	@Test
	public final void testListarTodos() throws Exception {
		when(complainService.listarTodos()).thenReturn(complains);

		mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(status().isOk());

		verify(complainService, times(1)).listarTodos();
		verifyNoMoreInteractions(complainService);
	}

	@Test
	public final void testListarPorId() throws Exception {
		Complain complain = new Complain();
		complain = complains.get(0);

		when(complainService.listarPorId(complain.getId())).thenReturn(Optional.of(complain));

		mockMvc.perform(get(uri + "/{id}", complain.getId()).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print()).andExpect(status().isOk());

		verify(complainService, times(1)).listarPorId(complain.getId());
		verifyNoMoreInteractions(complainService);
	}

	@Test
	public final void testCadastrar() throws Exception {
		Complain complain = new Complain();
		complain = complains.get(0);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(complain);

		when(complainService.cadastrar(complain)).thenReturn(complain);
		mockMvc.perform(post(uri).accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public final void testAtualizar() throws Exception {
		Complain complain = new Complain();
		Complain complainUp = new Complain();

		complain = complains.get(0);
		complainUp = complains.get(1);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(complain);

		when(complainService.atualizar(complain)).thenReturn(complainUp);
		when(complainService.listarPorId(complain.getId())).thenReturn(Optional.of(complain));

		mockMvc.perform(put(uri + "/{id}", complain.getId()).accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public final void testRemover() throws Exception {
		Complain complain = new Complain();
		complain = complains.get(0);

		mockMvc.perform(delete(uri + "/{id}", complain.getId()).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public final void testListarPorCidade() throws Exception {
		Complain complain = new Complain();
		complain = complains.get(0);

		when(complainService.listarPorCidade(complain.getLocale().getCity())).thenReturn(complains);

		mockMvc.perform(
				get(uri + "/{city}/city", complain.getLocale().getCity()).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print()).andExpect(status().isOk());

		verify(complainService, times(1)).listarPorCidade(complain.getLocale().getCity());
		verifyNoMoreInteractions(complainService);

	}

	@Test
	public final void testListarPorEstado() throws Exception {
		Complain complain = new Complain();
		complain = complains.get(0);

		when(complainService.listarPorEstado(complain.getLocale().getState())).thenReturn(complains);

		mockMvc.perform(get(uri + "/{state}/state", complain.getLocale().getState())
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print()).andExpect(status().isOk());

		verify(complainService, times(1)).listarPorEstado(complain.getLocale().getState());
		verifyNoMoreInteractions(complainService);
	}

}
