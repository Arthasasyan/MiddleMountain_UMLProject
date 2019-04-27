package com.middlemountain.service;

import com.middlemountain.enums.MagicType;
import com.middlemountain.enums.OrderStatus;
import com.middlemountain.enums.Permission;
import com.middlemountain.model.CreationJob;
import com.middlemountain.model.Employee;
import com.middlemountain.model.Good;
import com.middlemountain.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestService implements Service {

  private List<Good> goodList;
  private Map<String, Good> goodMap;
  private List<Employee> employeeList;
  private Map<String, Employee> employeeMap;
  private Map<String, String> usernameAndPassword;

  public TestService() {
    goodList = new ArrayList<>();
    goodMap = new HashMap<>();
    employeeList = new ArrayList<>();
    employeeMap = new HashMap<>();
    usernameAndPassword = new HashMap<>();
    Good good = new Good(1, MagicType.FIRE, "Fireball", "It's a fucking fireball", 100f);
    goodList.add(good);
    goodMap.put(good.getName(), good);
    Employee employee = new Employee(1, "John Doe", 100f, Permission.MANAGER);
    employeeList.add(employee);
    employeeMap.put("johndoe", employee);
    usernameAndPassword.put("johndoe", "password");
  }

  @Override
  public Good getGood(Integer id) throws Exception {
    return goodList.get(id);
  }

  @Override
  public Good getGood(String name) throws Exception {
    return goodMap.get(name);
  }

  @Override
  public Order getOrder(Integer id) throws Exception {
    return null;
  }

  @Override
  public Order getOrder(String clientName) throws Exception {
    return null;
  }

  @Override
  public Employee getEmployee(Integer id) throws Exception {
    return employeeList.get(id);
  }

  @Override
  public Employee getEmployee(String name) throws Exception {
    return employeeMap.get(name);
  }

  @Override
  public void shipOrder(Order order) throws Exception {
    if(order.getStatus().equals(OrderStatus.SHIPPING)){
      System.out.println("shipping");
    }
    else {
      throw new Exception("Order status must be SHIPPING");
    }
  }

  @Override
  public void updateGood(Good good) throws Exception {
    System.out.println(good.getName() + " was updated");
  }

  @Override
  public void updateOrder(Order order) throws Exception {
    System.out.println(order.getId() + " was updated");
  }

  @Override
  public void deleteGood(Good good) throws Exception {

  }

  @Override
  public void deleteEmployee(Employee employee) throws Exception {
  }

  @Override
  public CreationJob getCreationJob(Integer id) throws Exception {
    return null;
  }

  @Override
  public void updateCreationJob(CreationJob creationJob) throws Exception {

  }

  @Override
  public List<Order> getOrders(Employee employee) throws Exception {
    return null;
  }

  @Override
  public List<CreationJob> getCreationJobs(Employee employee) throws Exception {
    return null;
  }

  @Override
  public Employee login(String username, String password) throws Exception {
    if(usernameAndPassword.get(username).equals(password)){
      return employeeMap.get(username);
    } else {
      throw new Exception("Login failed");
    }
  }

  @Override
  public void createOrder(Order order) throws Exception {

  }

  @Override
  public String createEmployee(Employee employee, String username) throws Exception {
    if(!employeeList.contains(employee)) {
      employeeList.add(employee);
      employee.setId(employeeList.size());
      employeeMap.put(username, employee);
      usernameAndPassword.put(username, "password");
      return "password";
    }
    throw  new Exception(employee.getName() + " already exists");
  }

  @Override
  public void createGood(Good good) throws Exception {
    if(!goodList.contains(good)) {
      goodList.add(good);
      good.setId(goodList.size());
      goodMap.put(good.getName(), good);
    }
    else
    {
      throw  new Exception(good.getName() + " already exists");
    }
  }

  @Override
  public void createCreationJob(CreationJob creationJob) throws Exception {

  }
}
