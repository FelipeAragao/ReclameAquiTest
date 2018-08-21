package com.reclameaqui.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reclameaqui.api.documents.Complain;
import com.reclameaqui.api.service.ComplainService;
import com.reclameaqui.api.util.Response;

@RestController
@RequestMapping(path = "/complains")
public class ComplainController {

	@Autowired
	private ComplainService complainService;

	@GetMapping
	public ResponseEntity<Response<List<Complain>>> listarTodos() {
		return ResponseEntity.ok(new Response<List<Complain>>(complainService.listarTodos()));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Complain>> listarPorId(@PathVariable String id) {

		List<String> erros = new ArrayList<String>();
		Optional<Complain> optComplain = complainService.listarPorId(id);

		if (!optComplain.isPresent()) {
			erros.add("Reclamação não encontrada");
			return ResponseEntity.badRequest().body(new Response<Complain>(erros));
		}

		return ResponseEntity.ok(new Response<Complain>(optComplain.get()));
	}

	@PostMapping
	public ResponseEntity<Response<Complain>> cadastrar(@Valid @RequestBody Complain complain, BindingResult result) {

		if (!result.hasErrors())
			return ResponseEntity.ok(new Response<Complain>(complainService.cadastrar(complain)));

		List<String> erros = new ArrayList<String>();
		result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));

		return ResponseEntity.badRequest().body(new Response<Complain>(erros));

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Complain>> atualizar(@PathVariable String id, @Valid @RequestBody Complain complain,
			BindingResult result) {

		List<String> erros = new ArrayList<String>();
		Optional<Complain> optComplain = complainService.listarPorId(id);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Complain>(erros));
		}

		if (!optComplain.isPresent()) {
			erros.add("Reclamação não encontrada");
			return ResponseEntity.badRequest().body(new Response<Complain>(erros));
		}

		complain.setId(id);
		return ResponseEntity.ok(new Response<Complain>(complainService.atualizar(complain)));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> remover(@PathVariable String id) {
		complainService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}

	@GetMapping(path = "/{city}/city")
	public ResponseEntity<Response<List<Complain>>> listarPorCidade(@PathVariable String city) {
		return ResponseEntity.ok(new Response<List<Complain>>(complainService.listarPorCidade(city)));
	}

	@GetMapping(path = "/{state}/state")
	public ResponseEntity<Response<List<Complain>>> listarPorEstado(@PathVariable String state) {
		return ResponseEntity.ok(new Response<List<Complain>>(complainService.listarPorEstado(state)));
	}
}
