package com.BikkadIT.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIT.entities.CityMasterEntity;
@Repository
public interface CityRepository extends JpaRepository<CityMasterEntity, Serializable> {

}
