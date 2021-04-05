package com.magneto.mutant.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.magneto.mutant.entity.Human;

public interface IHumanDao extends JpaRepository<Human, Long> {
	@Query("SELECT COUNT(H) FROM Human H WHERE H.mutant= :mutant")
	double findTotal(@Param("mutant") boolean mutant);
	
	@Query("SELECT H FROM Human H WHERE H.dna= :dna")
	Optional<Human> findHumanDna(@Param("dna") String dna);

}
