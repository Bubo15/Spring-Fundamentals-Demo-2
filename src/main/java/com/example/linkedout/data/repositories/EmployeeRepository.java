package com.example.linkedout.data.repositories;

import com.example.linkedout.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getEmployeeById(long id);

    void deleteEmployeeById(long id);

    List<Employee> getAllByCompanyId(long id);
}
