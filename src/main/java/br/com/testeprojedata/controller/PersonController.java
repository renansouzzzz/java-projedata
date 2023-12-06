package br.com.testeprojedata.controller;

import br.com.testeprojedata.models.Employee;
import br.com.testeprojedata.models.Person;
import br.com.testeprojedata.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> insertAll() {

        personService.insertAllPerson();

        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Person>> getAll() {

        return ResponseEntity.ok(personService.getAll());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserJoao() {

        return ResponseEntity.ok(personService.removeUser());
    }

    @GetMapping("/get-employee-salary-bonus")
    public ResponseEntity<List<Employee>> applySalaryBonus() {

        return ResponseEntity.ok(personService.applySalaryBonus());
    }

    @GetMapping("/get-employee-by-jobrole")
    public ResponseEntity<Map<String, List<Employee>>> getEmployeesByJobRole() {

        return ResponseEntity.ok(personService.groupEmployeesByJobRole());
    }

    @GetMapping("/get-employee-birthday-10-12")
    public ResponseEntity<List<Employee>> getEmployeesByBirthDay() {

        return ResponseEntity.ok(personService.employeesBirthDay());
    }

    @GetMapping("/get-employee-older")
    public ResponseEntity<String> getEmployeesOlder() {

        return ResponseEntity.ok(personService.employeesOlder());
    }

    @GetMapping("/get-employee-order")
    public ResponseEntity<List<Employee>> employeesByAlphabeticalOrder() {

        return ResponseEntity.ok(personService.employeesByAlphabeticalOrder());
    }

    @GetMapping("/get-employee-total-salary")
    public ResponseEntity<BigDecimal> employeesTotalSalary() {

        return ResponseEntity.ok(personService.employeesTotalSalary());
    }

    @GetMapping("/get-each-employee-to-minimum-salary")
    public ResponseEntity<String> employeesByMinimumSalary() {

        return ResponseEntity.ok(personService.employeesByMinimumSalary());
    }
}