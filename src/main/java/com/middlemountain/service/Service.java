package com.middlemountain.service;

import com.middlemountain.model.CreationJob;
import com.middlemountain.model.Employee;
import com.middlemountain.model.Good;
import com.middlemountain.model.Order;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.List;

public interface Service {
  Good getGood(Integer id) throws Exception;
  Good getGood(String name) throws Exception;
  Order getOrder(Integer id) throws Exception;
  Order getOrder(String clientName) throws Exception;
  Employee getEmployee(Integer id) throws Exception;
  Employee getEmployee(String name) throws Exception;
  void shipOrder(Order order) throws Exception;
  void updateGood(Good good) throws Exception;
  void updateOrder(Order order) throws Exception;
  void deleteGood(Good good) throws Exception;
  void deleteEmployee(Employee employee) throws Exception;
  CreationJob getCreationJob(Integer id) throws Exception;
  void updateCreationJob(CreationJob creationJob) throws Exception;
  List<Order> getOrders(Employee employee) throws Exception;
  List<CreationJob> getCreationJobs(Employee employee) throws Exception;
  Employee login(String username, String password) throws Exception;
  void createOrder(Order order) throws Exception;

  /**
   *
   * @param employee
   * @param username
   * @return employee's password
   * @throws Exception
   */
  String createEmployee(Employee employee, String username) throws Exception;
  void createGood(Good good) throws Exception;
  void createCreationJob(CreationJob creationJob) throws Exception;
}
