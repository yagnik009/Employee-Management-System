package com.Backend.Employee.Management.System.Repositry;

import com.Backend.Employee.Management.System.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositry extends JpaRepository<Employee,Long> {

}

