package ru.otus.HW06;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by mix on 07.04.2018.
 */
public class ExecutorTest {

    private Connection connection;
    private QueryHelper queryHelper;

    @Before
    public void setUp() throws SQLException {
        connection = ConnectionHelper.getConnection();
        queryHelper = new QueryHelper(connection);
        //queryHelper.insert("insert into user (name, age) values ('Vasia', 50)");
    }

    @After
    public void down() throws SQLException {
        queryHelper.truncate("user");
        connection.close();
    }

    @Test
    public void save() throws Exception {
        Executor e = new Executor(connection);

        UserDataSet u = new UserDataSet();
        u.setName("Ivan");
        u.setAge(30);
        e.save(u);
        ResultSet rs = queryHelper.select("select * from user");
        rs.next();
        assertEquals("Ivan", rs.getString("name"));
        assertEquals(30, rs.getInt("age"));

    }

    @Test
    public void saveUpdatesExisting() throws Exception {
        Executor e = new Executor(connection);

        UserDataSet u = new UserDataSet();
        u.setName("Ivan");
        u.setAge(30);
        e.save(u);

        u.setAge(50);
        e.save(u);

        ResultSet rs = queryHelper.select("select * from user");
        rs.next();
        assertEquals("Ivan", rs.getString("name"));
        assertEquals(50, rs.getInt("age"));

        ResultSet rs2 = queryHelper.select("select count(*) c from user");
        rs2.next();
        assertEquals(1, rs2.getInt(1));

    }

    @Test
    public void load() throws Exception {

    }
}