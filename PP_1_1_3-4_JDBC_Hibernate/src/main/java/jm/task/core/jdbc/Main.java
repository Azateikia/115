package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

        Util.getSessionFactory();
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Zaur", "Trigulov", (byte) 28);
        userDaoHibernate.saveUser("Nail", "Alishev", (byte) 27);
        userDaoHibernate.saveUser("Semion", "Fomichiod", (byte) 31);
        userDaoHibernate.saveUser("Azat", "Yulmukhametov", (byte) 30);

        userDaoHibernate.removeUserById(1);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
