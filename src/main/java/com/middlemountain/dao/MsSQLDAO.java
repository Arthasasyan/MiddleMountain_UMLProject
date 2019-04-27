package com.middlemountain.dao;
import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class MsSQLDAO implements DatabaseDAO {
  private String userName;
  private String passWord;
  private String connectionString;
  private Connection conn;

  public MsSQLDAO() throws Exception
  {
    FileInputStream fileInputStream = new FileInputStream("database.properties");
    Properties prop = new Properties();
    prop.load(fileInputStream);
    fileInputStream.close();
    userName = prop.getProperty("username");
    passWord = prop.getProperty("password");
    connectionString = prop.getProperty("connectionString");
    System.out.println(userName);
    System.out.println(passWord);
    System.out.println(connectionString);
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    conn = DriverManager.getConnection(connectionString, userName, passWord);
  }
  private List<String> getListOfString(String query) throws Exception {
    Set<List<String>> good = executeQuery(query);
    List<String> result = new ArrayList<String>();
    for(List<String> list: good) {
      result=list;
    }
    return result;
  }
  public Set<List<String>> executeQuery(String query) throws Exception {
    Set<List<String>> result = new HashSet<List<String>>();
    Statement statement;
    ResultSet rs;
    statement = conn.createStatement();
    rs = statement.executeQuery(query);
    ResultSetMetaData rsmd = rs.getMetaData();
    while (rs.next())
    {
      ArrayList<String> list = new ArrayList<String>();
      for(int i = 1; i <= rsmd.getColumnCount(); i++){
        list.add(rs.getString(i));
      }
      result.add(list);
    }
    return result;
  }

  public List<String> getGood(Integer id) throws Exception {
    String query = "Select * from Good where GoodId = " + id;
    return getListOfString(query);
  }

  public List<String> getGood(String name) throws Exception {
    String query = "Select * from Good where Name = '" + name + "'";
    return getListOfString(query);
  }

  public List<String> getOrder(Integer id) throws Exception {
    String query = "Select * from Order where OrderID = " + id;
    return getListOfString(query);
  }

  public List<String> getOrder(String clientName) throws Exception {
    String query = "Select * from Order where ClientName = '" + clientName + "'";
    return getListOfString(query);
  }

  public List<String> getEmployee(Integer id) throws Exception {
    String query = "Select * from Employee where EmployeeID = " + id;
    return getListOfString(query);
  }

  public List<String> getEmployee(String name) throws Exception {
    String query = "Select * from Employee where Username = '" + name + "'";
    return getListOfString(query);
  }

  public List<String> getEmployee(String username, String password) throws Exception {
    String query = "Select * from Employee where Username = '" + username +  "' and Password = '" + password + "'";
    return getListOfString(query);
  }

  public List<String> getItem(Integer id) throws Exception {
    String query = "Select * from Item where ItemID = " + id;
    return getListOfString(query);
  }

  public List<String> getEnchantmentJob(Integer id) throws Exception {
    String query = "Select * from EnchantmentJob where EnchantmentJobID = " + id;
    return getListOfString(query);
  }

  public List<String> getCreationJob(Integer id) throws Exception {
    String query = "Select * from CreationJob where CreationJobID = " + id;
    return getListOfString(query);
  }

  public Set<List<String>> getCreationJobs(Integer employeeID) throws Exception {
    String query = "Select * from CreationJobs inner join\n" +
            "Employee on CreationJobs.EmployeeID = Employee.EmployeeID where Employee.EmployeeID = " + employeeID;
    return executeQuery(query);
  }
  public Set<List<String>> getOrders(Integer employeeID) throws Exception {
    String query = "Select * from Order inner join\n" +
            "Employee on Order.AssignedEmployeeID = Employee.EmployeeID where Employee.EmployeeID = '" + employeeID;
    return executeQuery(query);
  }

  public void updateTable(String table, Integer id, List<String> payload) throws Exception {
    ResultSetMetaData rsmd = conn.createStatement().executeQuery("select * from " + table).getMetaData();
    String query = "update " + table + " set ";
    for(int i = 2; i <= rsmd.getColumnCount(); i++)
    {
      query += rsmd.getColumnName(i)+" = "+payload.get(i - 1) + ", ";
    }
    query = query.substring(0, query.length() - 2);
    query +=" where " + rsmd.getColumnName(1) + " = " + id;
    conn.prepareStatement(query).execute();
  }

  public void deleteFromTable(String table, Integer id) throws Exception {
    ResultSetMetaData rsmd = conn.createStatement().executeQuery("select * from " + table).getMetaData();
    String query = "delete from " + table + "where " + rsmd.getColumnName(1) + " = " + id;
    conn.prepareStatement(query).execute();
  }

  public Integer insertInto(String table, List<String> payload) throws Exception {
    Statement statement = conn.createStatement();
    String val = "";
    String selectWhere = "";
    ResultSetMetaData rsmd = conn.createStatement().executeQuery("select * from " + table).getMetaData();
    for (int i = 2; i < rsmd.getColumnCount(); i++)
    {
      val += payload.get(i - 2) + ",";
      selectWhere += rsmd.getColumnName(i) + " = " + payload.get(i - 2) + ",";
    }
    val = val.substring(0, val.length() - 1); //removing last coma
    selectWhere = selectWhere.substring(0, selectWhere.length() - 1);
    String query = "insert into [" + table + "] values(" + val + ")";
    conn.prepareStatement(query).execute();
    //statement.executeQuery("insert into " + table + " values(" + val + ")");
    return Integer.parseInt(getListOfString("select * from " + table + " where " + selectWhere).get(0));
  }

  public Set<List<String>> getOrderGoods(Integer orderID) throws Exception {
    String query = "Select * from OrderGood where OrderID = " + orderID;
    return executeQuery(query);
  }

  public Set<List<String>> getOrderEnchantmentJobs(Integer orderID) throws Exception {
    String query = "Select * from OrderEnchantmentJob where OrderID = " + orderID;
    return executeQuery(query);
  }


}
