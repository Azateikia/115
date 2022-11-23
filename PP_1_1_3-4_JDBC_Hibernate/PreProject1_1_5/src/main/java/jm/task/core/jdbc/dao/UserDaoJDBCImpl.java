package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "create table if not exists Users\n" +
                    "(   id       INT auto_increment not null,\n" +
                    "    name     TEXT not null,\n" +
                    "    lastName TEXT not null,\n" +
                    "    age      INT  null,\n" +
                    "    constraint users_pk\n" +
                    "        primary key (id)\n" + ");";
            System.out.println("Table created");
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("createUsersTable need fix");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "DROP TABLE if EXISTS Users";
            statement.executeUpdate(sql);
            System.out.println("Table  dropped");
        } catch (SQLException e) {
            System.out.println("dropUserTable need fix");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pps = Util.getConnection()
                .prepareStatement("INSERT INTO Users VALUES (id,?,?,?)")) {
            pps.setString(1, name);
            pps.setString(2, lastName);
            pps.setByte(3, age);
            pps.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("saveUser need fix");
        }
    }

    public void removeUserById(long id) { //delete from user where id = 1
        try (PreparedStatement pps = Util.getConnection()
                .prepareStatement("DELETE FROM Users WHERE id = ?")) {
            pps.setLong(1, id);
            pps.executeUpdate();
            System.out.println("User with id:" + id + " just gone");
        } catch (SQLException e) {
            System.out.println("removeUserById need fix");
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "SELECT * FROM Users";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                allUsers.add(user);
            }
            System.out.println("We got all users");
        } catch (SQLException e) {
            System.out.println("getAllUsers need fix");
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "TRUNCATE Users";
            statement.executeUpdate(sql);
            System.out.println("Table was cleaned");
        } catch (SQLException e) {
            System.out.println("cleanUsersTable need fix");
        }
    }
}
