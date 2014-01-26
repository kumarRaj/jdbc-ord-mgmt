import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class JdbcTest {
    public static final String SQL_URL = "jdbc:mysql://localhost/";
    static String userName = "travis";
    static String password = "";
    @BeforeClass
    public static void setUpClass() throws SQLException {
        Connection con = DriverManager.getConnection(SQL_URL, userName, password);
        PreparedStatement statement = con.prepareStatement("create database jdbc_order_mgmt");
        statement.execute();
    }
    @Test
    public void testInsertProduct() throws SQLException {
        OrderManagement om = new OrderManagement(userName,password);
        assertEquals(0,om.createProduct());

        assertEquals(1, om.addProduct("1", "rice", 20, 20));
        assertEquals(1, om.addProduct("2", "Dove", 30, 30));

        om.dropTable("product");
    }

    @Test
    public void testInsertCustomer() throws SQLException {
        OrderManagement om = new OrderManagement(userName,password);
        assertEquals(0, om.createCustomer());

        assertEquals(1,om.addCustomer("1234","Raja",9123456));

        om.dropTable("customer");
    }
    @Test
    public void testInsertOrderAndCustomer() throws SQLException {
        OrderManagement om = new OrderManagement(userName,password);
        om.createCustomer();
        om.createOrders();
        om.createProduct();

        assertEquals(0,om.createOrderItems());
        assertEquals(1,om.addCustomer("1234","Raja",9123456));
        assertEquals(1,om.addOrder("123","1234",500));

        om.dropTable("order_items");
        om.dropTable("orders");
        om.dropTable("customer");
        om.dropTable("product");
    }
    @AfterClass
    public static void tearDownClass() throws SQLException {
        Connection con = DriverManager.getConnection(SQL_URL, userName, password);
        PreparedStatement statement = con.prepareStatement("drop database jdbc_order_mgmt");
        statement.execute();
    }

}
