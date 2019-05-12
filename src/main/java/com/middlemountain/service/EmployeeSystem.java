package com.middlemountain.service;

import com.middlemountain.dao.DatabaseDAO;
import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSystem {
  private DatabaseDAO dao;
  private Float minSalary;

  public EmployeeSystem(DatabaseDAO dao) {
    this.dao = dao;
    this.minSalary = 100f;
  }

  public List<Employee> getEmployees() throws Exception {
    List<Employee> employees = new ArrayList<>();
    var employeesInDatabase = dao.getEmployees();

    for(List<String> list : employeesInDatabase) {
      employees.add(fromListString(list));
    }
    return employees;
  }

  public void hireEmployee(Employee employee, String username, String password) throws Exception {
    if(employee.getSalary() < minSalary) {
      throw new Exception("Salary too low");
    }
    Integer id = dao.insertInto("Employee",toListString(employee, username, password));
    employee.setId(id);
  }

  public void fireEmployee(Employee employee) throws Exception {
    employee.setFired(1);
    dao.updateTable("Employee", employee.getId(), toListString(employee));
  }

  public Employee getEmployee(String username) throws Exception {
    return fromListString(dao.getEmployee(username));
  }

  public Employee getEmployee(Integer id) throws Exception {
    return fromListString(dao.getEmployee(id));
  }

  private String encloseInQuotes(String str) {
    return "'" + str + "'";
  }

  private List<String> toListString(Employee employee) {
    List<String> result = new ArrayList<>();
    result.add(employee.getId().toString());
    result.add(encloseInQuotes(employee.getName()));
    result.add(employee.getSalary().toString());
    result.add(Permission.toInteger(employee.getPermission()).toString());
    result.add(employee.getOnVacation().toString());
    result.add(employee.getFired().toString());
    return result;
  }

  private List<String> toListString(Employee employee, String username, String password) {
    List<String> result = new ArrayList<>();
    result.add(employee.getId().toString());
    result.add(encloseInQuotes(employee.getName()));
    result.add(employee.getSalary().toString());
    result.add(Permission.toInteger(employee.getPermission()).toString());
    result.add(employee.getOnVacation().toString());
    result.add(employee.getFired().toString());
    result.add(encloseInQuotes(username));
    result.add(encloseInQuotes(password));
    return result;
  }

  private Employee fromListString(List<String> employeeInDatabase) {
    Employee employee = new Employee()
            .setId(Integer.parseInt(employeeInDatabase.get(0)))
            .setName(employeeInDatabase.get(1))
            .setSalary(Float.parseFloat(employeeInDatabase.get(2)))
            .setPermission(Permission.fromInteger(employeeInDatabase.get(3)))
            .setOnVacation(Integer.parseInt(employeeInDatabase.get(4)))
            .setFired(Integer.parseInt(employeeInDatabase.get(5)));
    return employee;
  }

}
