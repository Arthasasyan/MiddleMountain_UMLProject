package com.middlemountain.service;

import com.middlemountain.dao.DatabaseDAO;
import com.middlemountain.dao.MsSQLDAO;
import com.middlemountain.enums.MagicType;
import com.middlemountain.model.CreationJob;
import com.middlemountain.model.Employee;
import com.middlemountain.model.Good;
import com.middlemountain.model.Order;

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
    return null;
  }

  public Order getOrder(Integer id) throws Exception {
    return null;
  }

  public Order getOrder(String clientName) throws Exception {
    return null;
  }

  public Employee getEmployee(Integer id) throws Exception {
    return null;
  }

  public Employee getEmployee(String name) throws Exception {
    return null;
  }

  public void shipOrder(Order order) throws Exception {

  }

  public void updateGood(Good good) throws Exception {

  }

  public void updateOrder(Order order) throws Exception {

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

  private String generateString()
  {
    String letters =  "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
    String password = "";
    int length = 10;
    Random rng = new Random();
    for(int i=0;i<length;i++){
      password+=letters.charAt(rng.nextInt(letters.length()));
    }
    return password;
  }
}
