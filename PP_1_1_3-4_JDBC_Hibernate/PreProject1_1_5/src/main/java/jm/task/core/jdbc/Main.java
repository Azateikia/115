package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Zaur", "Trigulov", (byte) 28);
        userDao.saveUser("Nail", "Alishev", (byte) 27);
        userDao.saveUser("Semion", "Fomichiod", (byte) 31);
        userDao.saveUser("Azat", "Yulmukhametov", (byte) 30);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
