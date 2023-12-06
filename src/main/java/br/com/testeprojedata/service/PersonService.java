package br.com.testeprojedata.service;

import br.com.testeprojedata.models.Employee;
import br.com.testeprojedata.models.Person;
import br.com.testeprojedata.repository.EmployeeRepository;
import br.com.testeprojedata.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Person createEmployee(String name, LocalDate birthDate, String jobRole, BigDecimal salary) {
        return new Employee(name, birthDate, jobRole, salary);
    }

    public void insertAllPerson() {

        List<Person> employees = Arrays.asList(
                createEmployee("Maria", LocalDate.of(2000, 10, 18), "Operador", new BigDecimal("2009.44")),
                createEmployee("João", LocalDate.of(1990, 5, 12), "Operador", new BigDecimal("2284.38")),
                createEmployee("Caio", LocalDate.of(1961, 5, 2), "Coordenador", new BigDecimal("9836.14")),
                createEmployee("Miguel", LocalDate.of(1988, 10, 14), "Diretor", new BigDecimal("19119.88")),
                createEmployee("Alice", LocalDate.of(1995, 1, 5), "Recepcionista", new BigDecimal("2234.68")),
                createEmployee("Heitor", LocalDate.of(1999, 11, 19), "Operador", new BigDecimal("1582.72")),
                createEmployee("Arthur", LocalDate.of(1993, 3, 31), "Contador", new BigDecimal("4071.84")),
                createEmployee("Laura", LocalDate.of(1994, 7, 8), "Gerente", new BigDecimal("3017.45")),
                createEmployee("Heloísa", LocalDate.of(2003, 5, 24), "Eletricista", new BigDecimal("1606.85")),
                createEmployee("Helena", LocalDate.of(1996, 9, 2), "Gerente", new BigDecimal("2799.93"))
        );

    personRepository.saveAll(employees);
    }

    public String removeUser() {

        Person joao = personRepository.findByName("João");

        personRepository.delete(joao);

        return MessageFormat.format("Usuário {0} deletado com sucesso!", joao.getName());
    }


    public List<Person> getAll() {

        return personRepository.findAll();
    }

    public List<Employee> salaryTenPorcent() {

        List<Employee> persons = employeeRepository.findAll();

        List<Employee> employees = null;

        persons.stream().map(employee -> employees.add(employee));

        employees.stream().map(employee -> )

        return ;
    }
}