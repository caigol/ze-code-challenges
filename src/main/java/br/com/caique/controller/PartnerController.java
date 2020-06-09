package br.com.caique.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.caique.model.Partner;
import br.com.caique.services.PartnerServices;

@RestController
@RequestMapping("/partner")
public class PartnerController {
	
	@Autowired
	private PartnerServices services;
	
	@GetMapping(value="/{id}")
	public Partner findById(@PathVariable("id") Long id) {
		return services.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Partner> findAll() {
		return services.findAll();
	}
	
	@PostMapping
	public Partner create(@RequestBody Partner partner) {
		return services.create(partner);
	}
	
	
}
