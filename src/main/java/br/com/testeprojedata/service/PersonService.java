package br.com.testeprojedata.service;

import br.com.testeprojedata.models.Employee;
import br.com.testeprojedata.models.Person;
import br.com.testeprojedata.repository.EmployeeRepository;
import br.com.testeprojedata.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final BigDecimal MINIMUM_SALARY = new BigDecimal("1212.00");


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

    public List<Employee> applySalaryBonus() {

        List<Employee> employees = employeeRepository.findAll();

        List<Employee> updatedEmployees = employees.stream()
                .map(employee -> {

                    BigDecimal currentSalary = employee.getSalary();
                    BigDecimal bonus = currentSalary.multiply(new BigDecimal("0.10"));
                    BigDecimal updatedSalary = currentSalary.add(bonus);
                    employee.setSalary(updatedSalary);
                    return employee;
                })
                .collect(Collectors.toList());

        employeeRepository.saveAll(updatedEmployees);

        return updatedEmployees;
    }

    public Map<String, List<Employee>> groupEmployeesByJobRole() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getJobRole));
    }

    public List<Employee> employeesBirthDay() {

        List<Employee> employees = employeeRepository.findAll();

        List<Employee> selectedEmployees = employees.stream()
                .filter(employee -> {
                    int birthMonth = employee.getBirthDate().getMonthValue();
                    return birthMonth == 10 || birthMonth == 12;
                })
                .toList();

        selectedEmployees.forEach(employee -> {
            System.out.println("Nome: " + employee.getName() +
                    ", Data de Aniversário: " + employee.getBirthDate());
        });

        return selectedEmployees;
    }

    public String employeesOlder() {

        List<Employee> employees = employeeRepository.findAll();

        Optional<Employee> oldestEmployee = employees.stream()
                .max(Comparator.comparingInt(employee ->
                        LocalDate.now().getYear() - employee.getBirthDate().getYear()));

        int yearsOld = LocalDate.now().getYear() - oldestEmployee.get().getBirthDate().getYear();

        return "Nome: " + oldestEmployee.get().getName() + " | " + " Idade: " + yearsOld;
    }

    public List<Employee> employeesByAlphabeticalOrder() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .toList();
    }

    public BigDecimal employeesTotalSalary() {

        List<Employee> employees = employeeRepository.findAll();

        List<BigDecimal> salaries = employees.stream()
                .map(Employee::getSalary)
                .toList();

        return salaries.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String employeesByMinimumSalary() {

        List<Employee> employees = employeeRepository.findAll();

        StringBuilder result = new StringBuilder();


        employees.forEach(employee -> {
            BigDecimal salary = employee.getSalary();
            BigDecimal quantitySalaryMinimum = salary.divide(MINIMUM_SALARY, 2, BigDecimal.ROUND_HALF_UP);

            result.append("Nome: ").append(employee.getName())
                    .append(" | Salário: ").append(salary)
                    .append(" | Salários Mínimos: ").append(quantitySalaryMinimum)
                    .append("\n");

        });

        return result.toString();

    }
}