package br.com.caique.controller;


import br.com.caique.model.Partner;
import br.com.caique.services.PartnerServices;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partner")
@Api(tags = "All apis related to partner")
public class PartnerController {
	
	@Autowired
	private PartnerServices services;
	
	@GetMapping(value="/{id}")
	@ApiOperation(notes = "Finds partner by id", value = "findById")
	public Partner findById(@PathVariable("id") Long id) {
		return services.findById(id);
	}
	
	@GetMapping("/lng/{lng}/lat/{lat}/")
	@ApiOperation(notes = "Searches closer partner by longitude and latitude", value = "search")
	public Partner search(@PathVariable double lng, @PathVariable double lat) {
		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate c = new Coordinate();
		c.x = lng;
		c.y = lat;
		Point myPoint = geometryFactory.createPoint(c);
		return services.search(myPoint);
	}
	
	@GetMapping
	@ApiOperation(notes = "Finds all partner", value = "findAll")
	public List<Partner> findAll() {
		return services.findAll();
	}
	
	@PostMapping
	@ApiOperation(notes = "Creates a new partner", value = "create")
	public Partner create(@RequestBody Partner partner) {
		return services.create(partner);
	}
}
