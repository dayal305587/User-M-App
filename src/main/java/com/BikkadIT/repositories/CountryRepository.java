package com.BikkadIT.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIT.entities.CountryMasterEntity;
@Repository
public interface CountryRepository extends JpaRepository<CountryMasterEntity, Serializable> {

}
