package com.magneto.mutant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.magneto.mutant.entity.Human;

public interface IHumanDao extends JpaRepository<Human, Long> {
	@Query("SELECT COUNT(H) FROM Human H WHERE H.mutant= :mutant")
	int findTotal(@Param("mutant") boolean mutant);

}
