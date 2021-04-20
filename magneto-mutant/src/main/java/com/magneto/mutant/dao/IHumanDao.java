package com.magneto.mutant.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.magneto.mutant.entity.Human;

public interface IHumanDao extends JpaRepository<Human, Long> {
	/**
	 * Este metodo calcula el total de humanos y mutantes validados 
	 * @param mutant true para validar total de mutantes, false para validar total humanos
	 * @return el total de humanos o mutantes encontrados
	 */
	@Query("SELECT COUNT(H) FROM Human H WHERE H.mutant= :mutant")
	double findTotal(@Param("mutant") boolean mutant);
	/**
	 * Este metodo permite buscar a traves de su ADN, si un humano ya ha sido validado 
	 * @param dna contiene el ADN del humano que deseamos verificar
	 * @return si el humano ya ha sido validado, retorna el humano al que pertenece el ADN, sino retorna un Objeto null
	 */
	@Query("SELECT H FROM Human H WHERE H.dna= :dna")
	Optional<Human> findHumanDna(@Param("dna") String dna);

}
