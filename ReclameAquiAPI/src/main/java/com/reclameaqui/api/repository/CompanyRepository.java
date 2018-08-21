package com.reclameaqui.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.reclameaqui.api.documents.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {

	Optional<Company> findByCnpj(String cnpj);

}
