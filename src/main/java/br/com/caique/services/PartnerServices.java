package br.com.caique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caique.exception.ResourceNotFoundException;
import br.com.caique.model.Partner;
import br.com.caique.repository.PartnerRepository;

@Service
public class PartnerServices {
	
	@Autowired
	PartnerRepository repository;
	
	public Partner create(Partner partner) {
		return repository.save(partner);		
	}
	
	public Partner findById(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
	}
	
	public List<Partner> findAll(){
		return repository.findAll();
	}
}
