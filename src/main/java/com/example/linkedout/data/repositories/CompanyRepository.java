package com.example.linkedout.data.repositories;

import com.example.linkedout.data.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company getCompaniesByName(String name);

    Company getCompanyById(long id);

    void deleteCompanyById(long id);
}
