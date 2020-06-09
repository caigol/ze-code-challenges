package br.com.caique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caique.model.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>{

}
