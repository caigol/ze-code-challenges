package br.com.caique.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Point;

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
	
	public Partner search(Point adress) {
		
			List<Partner> partners = repository.findAll();
			List<Partner> partnersCoverage = new ArrayList<Partner>();
			
			partners.forEach(partner->{
				 if(partner.getCoverageArea().contains(adress)) {
					 partnersCoverage.add(partner);
				 } 
				});
			
			partnersCoverage.stream().mapToDouble(partner -> partner.getCoverageArea().distance(adress)).sorted().findFirst();
			if(partnersCoverage.size() > 0)
				return partnersCoverage.get(0);
			else
				throw new ResourceNotFoundException("Outside the coverage area");
		
	}
}
