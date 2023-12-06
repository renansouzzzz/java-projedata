package br.com.testeprojedata.repository;

import br.com.testeprojedata.models.Employee;
import br.com.testeprojedata.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Person findByName(String name);
}
