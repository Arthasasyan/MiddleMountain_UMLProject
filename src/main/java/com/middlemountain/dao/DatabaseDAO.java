package com.middlemountain.dao;

import java.util.List;
import java.util.Set;

public interface DatabaseDAO {
  List<String> getGood(Integer id) throws Exception;
  List<String> getGood(String name) throws Exception;
  List<String> getOrder(Integer id) throws Exception;
  List<String> getOrder(String clientName) throws Exception;
  List<String> getEmployee(Integer id) throws Exception;
  List<String> getEmployee(String name) throws Exception;
  List<String> getEmployee(String username, String password)  throws Exception;
  List<String> getItem(Integer id) throws Exception;
  List<String> getEnchantmentJob(Integer id) throws Exception;
  List<String> getCreationJob(Integer id) throws Exception;
  Set<List<String>> getCreationJobs(String employee) throws Exception;
  Set<List<String>> getOrders(String employee) throws Exception;
  Set<List<String>> executeQuery(String query) throws Exception;
  void updateTable(String table, Integer id, List<String> payload) throws Exception;
  void deleteFromTable(String table, Integer id) throws Exception;
  Integer insertInto(String table, List<String> payload) throws Exception;
}
