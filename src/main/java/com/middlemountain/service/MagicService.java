package com.middlemountain.service;

import com.middlemountain.dao.DatabaseDAO;
import com.middlemountain.dao.MsSQLDAO;
import com.middlemountain.enums.MagicType;
import com.middlemountain.enums.OrderStatus;
import com.middlemountain.enums.Permission;
import com.middlemountain.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MagicService implements Service {
  private DatabaseDAO dao;
  private EmployeeSystem employeeSystem;

  public MagicService(DatabaseDAO dao) {
    this.dao = dao;
  }

  public MagicService() throws Exception{
    dao = new MsSQLDAO();
    employeeSystem = new EmployeeSystem(dao);
  }

  public Good getGood(Integer id) throws Exception {
    List<String> goodInDatabase = dao.getGood(id);
    Good good = new Good()
            .setId(Integer.parseInt(goodInDatabase.get(0)))
            .setMagicType(MagicType.fromInteger(goodInDatabase.get(1)))
            .setName(goodInDatabase.get(2))
            .setDescription(goodInDatabase.get(3))
            .setPrice(Float.parseFloat(goodInDatabase.get(4)))
            .setAmount(Integer.parseInt(goodInDatabase.get(5)))
            .setDeleted(Integer.parseInt(goodInDatabase.get(6)));
    return good;
  }

  public Good getGood(String name) throws Exception {
    List<String> goodInDatabase = dao.getGood(name);
    Good good = new Good()
            .setId(Integer.parseInt(goodInDatabase.get(0)))
            .setMagicType(MagicType.fromInteger(goodInDatabase.get(1)))
            .setName(goodInDatabase.get(2))
            .setDescription(goodInDatabase.get(3))
            .setPrice(Float.parseFloat(goodInDatabase.get(4)))
            .setAmount(Integer.parseInt(goodInDatabase.get(5)))
            .setDeleted(Integer.parseInt(goodInDatabase.get(6)));
    return good;
  }

  public Order getOrder(Integer id) throws Exception {
    List<String> orderInDatabase = dao.getOrder(id);
    return orderFromListString(orderInDatabase);
  }

  public Order getOrder(String clientName) throws Exception {
    List<String> orderInDatabase = dao.getOrder(clientName);
    return orderFromListString(orderInDatabase);
  }

  public Employee getEmployee(Integer id) throws Exception {
    return employeeSystem.getEmployee(id);
  }

  public Employee getEmployee(String name) throws Exception {
    return employeeSystem.getEmployee(name);
  }

  public void shipOrder(Order order) throws Exception {
    if(order.getStatus().equals(OrderStatus.SHIPPING)){
      System.err.println("Sipping " + order.toString());
    }
    else {
      throw new Exception("Order status must be SHIPPING");
    }
  }

  public void updateGood(Good good) throws Exception {
    dao.updateTable("Good", good.getId(), toListString(good));
  }

  public void updateOrder(Order order) throws Exception {
    dao.updateTable("Order", order.getId(), toListString(order));
    if(!(order.getGoods() == null)) {
      for (Good good : order.getGoods()) {
        updateGood(good);
      }
    }
    if(!(order.getEnchantmentJobs() == null)) {
      for (EnchantmentJob enchantmentJob : order.getEnchantmentJobs()) {
        dao.updateTable("EnchantmentJob", enchantmentJob.getId(), toListString(enchantmentJob));
      }
    }
  }

  public void deleteGood(Good good) throws Exception {
    good.setDeleted(1);
    dao.updateTable("Good", good.getId(), toListString(good));
  }

  public void deleteEmployee(Employee employee) throws Exception {
    employeeSystem.fireEmployee(employee);
  }

  public CreationJob getCreationJob(Integer id) throws Exception {
    List<String> creationJobInDatabase = dao.getCreationJob(id);
    return creationJobFromListString(creationJobInDatabase);
  }

  public void updateCreationJob(CreationJob creationJob) throws Exception {
    dao.updateTable("CreationJob", creationJob.getId(), toListString(creationJob));
  }

  public List<Order> getOrders(Employee employee) throws Exception {
    Set<List<String>> ordersInDatabase = dao.getOrders(employee.getId());
    List<Order> orders = new ArrayList<>();
    for(List<String> list : ordersInDatabase) {
      orders.add(orderFromListString(list));
    }
    return orders;
  }

  public List<CreationJob> getCreationJobs(Employee employee) throws Exception {
    Set<List<String>> creationJobsInDatabase = dao.getCreationJobs(employee.getId());
    List<CreationJob> creationJobs = new ArrayList<>();
    for(List<String> list : creationJobsInDatabase) {
      creationJobs.add(creationJobFromListString(list));
    }
    return creationJobs;
  }

  public Employee login(String username, String password) throws Exception {
    try {
      List<String> employeeInDatabase = dao.getEmployee(username, password);
      Employee employee = new Employee()
              .setId(Integer.parseInt(employeeInDatabase.get(0)))
              .setName(employeeInDatabase.get(1))
              .setSalary(Float.parseFloat(employeeInDatabase.get(2)))
              .setPermission(Permission.fromInteger(employeeInDatabase.get(3)))
              .setOnVacation(Integer.parseInt(employeeInDatabase.get(4)))
              .setFired(Integer.parseInt(employeeInDatabase.get(5)));
      if(employee.getFired()==1) {
        throw new Exception();
      }
      return employee;
    } catch (Exception e) {
      throw new Exception("Incorrect username or password");
    }
  }

  public void createOrder(Order order) throws Exception {
    Integer orderID = dao.insertInto("Order", toListString(order));
    order.setId(orderID);
    if(!(order.getEnchantmentJobs() == null)) {
      for (EnchantmentJob enchantmentJob : order.getEnchantmentJobs()) {
        Integer itemID = dao.insertInto("Item", toListString(enchantmentJob.getItem()));
        enchantmentJob.getItem().setId(itemID);
        Integer enchantmentID = dao.insertInto("EnchantmentJob", toListString(enchantmentJob));
        enchantmentJob.setId(enchantmentID);
        List<String> res = new ArrayList<>();
        res.add("0");
        res.add(order.getId().toString());
        res.add(enchantmentJob.getId().toString());
        dao.insertInto("OrderEnchantmentJob", res);
      }
    }
    if(!(order.getGoods()== null)) {
      for (Good good : order.getGoods()) {
        List<String> res = new ArrayList<>();
        res.add("0");
        res.add(good.getId().toString());
        res.add(order.getId().toString());
        dao.insertInto("OrderGood", res);
      }
    }
  }


  public String createEmployee(Employee employee, String username) throws Exception {
    try {
     Employee existent = getEmployee(username); //if employee does not exist, exception is thrown
     throw new Exception(username + " already registered");
    } catch (Exception e) {
      String password = generatePassword();
      employeeSystem.hireEmployee(employee,username, password);
      return password;
    }
  }

  public void createGood(Good good) throws Exception {
    try {
      Good existent = getGood(good.getName()); //if good does not exist, exception will be thrown
      throw new Exception("Good with name " + good.getName() + " already exists with ID: " + existent.getId());
    } catch (Exception e) {
      Integer id = dao.insertInto("Good",toListString(good));
      good.setId(id);
    }
  }

  public void createCreationJob(CreationJob creationJob) throws Exception {
    Integer id =dao.insertInto("CreationJob", toListString(creationJob));
    creationJob.setId(id);
  }

  private String generatePassword() {
    String letters =  "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
    String password = "";
    int length = 10;
    Random rng = new Random();
    for(int i=0;i<length;i++){
      password+=letters.charAt(rng.nextInt(letters.length()));
    }
    return password;
  }

  @Override
  public void updateEmployee(Employee employee) throws Exception{
    dao.updateTable("Employee", employee.getId(), toListString(employee));
  }

  @Override
  public List<Employee> getEmployees() throws Exception {
    return employeeSystem.getEmployees();
  }

  private EnchantmentJob getEnchantmentJob(Integer id) throws Exception {
    List<String> enchantmentJobInDataBase = dao.getEnchantmentJob(id);
    EnchantmentJob enchantmentJob = new EnchantmentJob()
            .setId(Integer.parseInt(enchantmentJobInDataBase.get(0)))
            .setMagicType(MagicType.fromInteger(enchantmentJobInDataBase.get(2)))
            .setDescription(enchantmentJobInDataBase.get(3))
            .setCompleted(Integer.parseInt(enchantmentJobInDataBase.get(4)));
    List<String> itemInDatabase = dao.getItem(Integer.parseInt(enchantmentJobInDataBase.get(1)));
    Item item = new Item().setId(Integer.parseInt(itemInDatabase.get(0))).setDescription(itemInDatabase.get(1));
    enchantmentJob.setItem(item);
    return enchantmentJob;
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

  private List<String> toListString(Good good)
  {
    List<String> result = new ArrayList<>();
    result.add(good.getId().toString());
    result.add(MagicType.toInteger(good.getMagicType()).toString());
    result.add(encloseInQuotes(good.getName()));
    result.add(encloseInQuotes(good.getDescription()));
    result.add(good.getPrice().toString());
    result.add(good.getAmount().toString());
    result.add(good.getDeleted().toString());
    return result;
  }

  private List<String> toListString(Order order) {
    List<String> result = new ArrayList<>();
    result.add(order.getId().toString());
    result.add(encloseInQuotes(order.getClientName()));
    result.add(OrderStatus.toInteger(order.getStatus()).toString());
    result.add(order.getAssignedEmployee().getId().toString());
    result.add(encloseInQuotes(order.getShippingAddress().getCountry()));
    result.add(encloseInQuotes(order.getShippingAddress().getCity()));
    result.add(encloseInQuotes(order.getShippingAddress().getAddress()));
    return result;
  }

  private List<String> toListString(EnchantmentJob enchantmentJob) {
    List<String> result = new ArrayList<>();
    result.add(enchantmentJob.getId().toString());
    result.add(enchantmentJob.getItem().getId().toString());
    result.add(MagicType.toInteger(enchantmentJob.getMagicType()).toString());
    result.add(encloseInQuotes(enchantmentJob.getDescription()));
    result.add(enchantmentJob.getCompleted().toString());
    return result;
  }

  private List<String> toListString(CreationJob creationJob) {
    List<String> result = new ArrayList<>();
    result.add(creationJob.getId().toString());
    result.add(creationJob.getGood().getId().toString());
    result.add(creationJob.getEmployee().getId().toString());
    result.add(creationJob.getAmountRemaining().toString());
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

  private List<String> toListString(Item item) {
    List<String> result = new ArrayList<>();
    result.add(item.getId().toString());
    result.add(encloseInQuotes(item.getDescription()));
    return result;
  }

  private Order orderFromListString(List<String> orderInDatabase) throws Exception {
    Order order = new Order()
            .setId(Integer.parseInt(orderInDatabase.get(0)))
            .setClientName(orderInDatabase.get(1))
            .setStatus(OrderStatus.fromInteger(orderInDatabase.get(2)))
            .setAssignedEmployee(this.getEmployee(Integer.parseInt(orderInDatabase.get(3))));
    Address address = new Address(orderInDatabase.get(4), orderInDatabase.get(5), orderInDatabase.get(6));
    order.setShippingAddress(address);
    Set<List<String>> goods = dao.getOrderGoods(order.getId());
    for(List<String> list : goods) { //adding all goods to order
      order.addGood(getGood(Integer.parseInt(list.get(1))));
    }
    Set<List<String>> enchantmentJobs = dao.getOrderEnchantmentJobs(order.getId());
    for(List<String> list : enchantmentJobs) {
      order.addEnchantmentJob(getEnchantmentJob(Integer.parseInt(list.get(2))));
    }
    return order;
  }

  private CreationJob creationJobFromListString(List<String> list) {
    CreationJob creationJob = new CreationJob();
    Good good = new Good();
    Employee employee = new Employee();
    creationJob.setGood(good.setId(Integer.parseInt(list.get(0))));
    creationJob.setEmployee(employee.setId(Integer.parseInt(list.get(1))));
    creationJob.setAmountRemaining(Integer.parseInt(list.get(2)));
    return creationJob;
  }
}
