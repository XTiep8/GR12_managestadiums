package com.soa_.ManageFootballStadium.repository;

import com.soa_.ManageFootballStadium.model.Stadium;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {

}
