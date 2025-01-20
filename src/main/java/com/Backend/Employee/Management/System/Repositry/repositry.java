package com.Backend.Employee.Management.System.Repositry;

import com.Backend.Employee.Management.System.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface repositry extends JpaRepository<Employee,Long> {
    @Query("SELECT e.department AS department, COUNT(e) AS count FROM Employee e GROUP BY e.department")
    List<Map<String, Object>> getEmployeeCountByDepartment();
}
