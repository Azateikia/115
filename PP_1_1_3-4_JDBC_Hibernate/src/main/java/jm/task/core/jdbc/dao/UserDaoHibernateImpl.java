package jm.task.core.jdbc.dao;



import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            String sql = "CREATE TABLE if not exists Users(id int primary key auto_increment, name text not null, lasName text not null, age int)";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            System.out.println("just created");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("creating is fucked up");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table if exists Users").executeUpdate();
            session.getTransaction().commit();
            System.out.println("just dropped");
        } catch (Exception e) {
            System.out.println("dripping is fucked up");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            User user = new User(name,lastName,age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("saving is fucked up");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("just removed");
        } catch (Exception e) {
            System.out.println("removing is fucked up");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            List allUsers = session.createQuery("from User")
                    .getResultList();

            session.getTransaction().commit();
            System.out.println("just listed");
            return allUsers;
        } catch (Exception e) {
            System.out.println("getting is fucked up");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("just cleared");
        } catch (Exception e) {
            System.out.println("clearing is fucked up");
        }
    }
}
