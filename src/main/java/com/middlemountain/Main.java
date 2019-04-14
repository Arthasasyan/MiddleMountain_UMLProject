package com.middlemountain;

import com.middlemountain.dao.DatabaseDAO;
import com.middlemountain.dao.MsSQLDAO;

public class Main {
  public static void main(String[] args) throws Exception
  {
    DatabaseDAO databaseDAO = new MsSQLDAO();
  }

}
