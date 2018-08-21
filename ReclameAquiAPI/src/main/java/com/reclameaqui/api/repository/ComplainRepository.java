package com.reclameaqui.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.reclameaqui.api.documents.Company;
import com.reclameaqui.api.documents.Complain;

public interface ComplainRepository extends MongoRepository<Complain, String> {

	List<Complain> findByLocaleCity(String cidade);

	List<Complain> findByLocaleState(String estado);

	List<Complain> findByCompany(Company company);

	List<Complain> findByCompanyAndLocaleCity(Company company, String city);

	List<Complain> findByCompanyAndLocaleState(Company company, String state);

}
