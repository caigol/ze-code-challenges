package br.com.caique.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

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
	
	@GetMapping("/lng/{lng}/lat/{lat}")
	public Partner search(@PathVariable double lng, @PathVariable double lat) {
		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate c = new Coordinate();
		c.x = lng;
		c.y = lat;
		Point myPoint = geometryFactory.createPoint(c);
		return services.search(myPoint);
	}
	
	@GetMapping
	public List<Partner> findAll() {
		return services.findAll();
	}
	
	@PostMapping
	public Partner create(@RequestBody Partner partner) {
		return services.create(partner);
	}
	
	
}
