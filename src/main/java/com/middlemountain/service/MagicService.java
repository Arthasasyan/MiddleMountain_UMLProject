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

public class MagicService implements Service {
  private DatabaseDAO dao;

  public MagicService(DatabaseDAO dao) {
    this.dao = dao;
  }

  public MagicService() throws Exception{
    dao = new MsSQLDAO();
  }

  public Good getGood(Integer id) throws Exception {
    List<String> goodInDatabse = dao.getGood(id);
    Good good = new Good()
            .setId(Integer.parseInt(goodInDatabse.get(0)))
            .setMagicType(MagicType.fromInteger(goodInDatabse.get(1)))
            .setName(goodInDatabse.get(2))
            .setDescription(goodInDatabse.get(3))
            .setPrice(Float.parseFloat(goodInDatabse.get(4)));
    return good;
  }

  public Good getGood(String name) throws Exception {
    List<String> goodinDatabase = dao.getGood(name);
    Good good = new Good()
            .setId(Integer.parseInt(goodinDatabase.get(0)))
            .setMagicType(MagicType.fromInteger(goodinDatabase.get(1)))
            .setName(goodinDatabase.get(2))
            .setDescription(goodinDatabase.get(3))
            .setPrice(Float.parseFloat(goodinDatabase.get(4)));
    return good;
  }

  public Order getOrder(Integer id) throws Exception {
    List<String> orderInDatabase = dao.getOrder(id);
    Order order = new Order()
            .setId(Integer.parseInt(orderInDatabase.get(0)))
            .setClientName(orderInDatabase.get(1))
            .setStatus(OrderStatus.fromInteger(orderInDatabase.get(2)))
            .setAssignedEmployee(this.getEmployee(Integer.parseInt(orderInDatabase.get(3))));
    Address address = new Address(orderInDatabase.get(4), orderInDatabase.get(5), orderInDatabase.get(6));
    order.setShippingAddress(address);
    //TODO foreign keys
    return order;
  }

  public Order getOrder(String clientName) throws Exception {
    List<String> orderInDatabase = dao.getOrder(clientName);
    Order order = new Order()
            .setId(Integer.parseInt(orderInDatabase.get(0)))
            .setClientName(orderInDatabase.get(1))
            .setStatus(OrderStatus.fromInteger(orderInDatabase.get(2)))
            .setAssignedEmployee(this.getEmployee(Integer.parseInt(orderInDatabase.get(3))));
    Address address = new Address(orderInDatabase.get(4), orderInDatabase.get(5), orderInDatabase.get(6));
    order.setShippingAddress(address);
    //TODO foreign keys
    return order;
  }

  public Employee getEmployee(Integer id) throws Exception {
    List<String> employeeInDatabase = dao.getEmployee(id);
    Employee employee = new Employee()
            .setId(Integer.parseInt(employeeInDatabase.get(0)))
            .setName(employeeInDatabase.get(1))
            .setSalary(Float.parseFloat(employeeInDatabase.get(2)))
            .setPermission(Permission.fromInteger(employeeInDatabase.get(3)));
    return employee;
  }

  public Employee getEmployee(String name) throws Exception {
    List<String> employeeInDatabase = dao.getEmployee(name);
    Employee employee = new Employee()
            .setId(Integer.parseInt(employeeInDatabase.get(0)))
            .setName(employeeInDatabase.get(1))
            .setSalary(Float.parseFloat(employeeInDatabase.get(2)))
            .setPermission(Permission.fromInteger(employeeInDatabase.get(3)));
    return employee;
  }

  public void shipOrder(Order order) throws Exception {
    if(order.getStatus().equals(OrderStatus.SHIPPING)){
      System.out.println("Sipping " + order.toString());
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
    for(Good good : order.getGoods()) {
      updateGood(good);
    }
    for(EnchantmentJob enchantmentJob : order.getEnchantmentJobs()){
      dao.updateTable("EnchantmentJob", enchantmentJob.getId(), toListString(enchantmentJob));
    }
  }

  public void deleteGood(Good good) throws Exception {

  }

  public void deleteEmployee(Employee employee) throws Exception {

  }

  public CreationJob getCreationJob(Integer id) throws Exception {
    return null;
  }

  public void updateCreationJob(CreationJob creationJob) throws Exception {

  }

  public List<Order> getOrders(Employee employee) throws Exception {
    return null;
  }

  public List<CreationJob> getCreationJobs(Employee employee) throws Exception {
    return null;
  }

  public Employee login(String username, String password) throws Exception {
    return null;
  }

  public void createOrder(Order order) throws Exception {

  }

  public String createEmployee(Employee employee, String username) throws Exception {
    return null;
  }

  public void createGood(Good good) throws Exception {

  }

  public void createCreationJob(CreationJob creationJob) throws Exception {

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

  private List<String> toListString(Good good)
  {
    List<String> result = new ArrayList<>();
    result.add(MagicType.toInteger(good.getMagicType()).toString());
    result.add("'"+good.getName()+"'");
    result.add("'"+good.getDescription()+"'");
    result.add(good.getPrice().toString());
    return result;
  }

  private List<String> toListString(Order order) {
    List<String> result = new ArrayList<>();
    result.add("'" + order.getClientName() + "'");
    result.add(OrderStatus.toInteger(order.getStatus()).toString());
    result.add(order.getAssignedEmployee().getId().toString());
    result.add(order.getShippingAddress().getCountry());
    result.add(order.getShippingAddress().getCity());
    result.add(order.getShippingAddress().getAddress());
    return result;
  }

  private List<String> toListString(EnchantmentJob enchantmentJob) {
  return null; //TODO toListString
  }
}
