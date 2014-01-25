package test.java;


import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;


public class tableCreationTest {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/dbms_assignments";
    @Test
    public void test_fail() throws ClassNotFoundException, SQLException {
        Connection con = DriverManager.getConnection(DB_URL,"raj","P@ssw0rd");
        PreparedStatement statement = con.prepareStatement("select * from orders");
        ResultSet rs = statement.executeQuery();
        rs.next();
        System.out.println(rs.getInt(1));
        assertEquals(1, 1, 1);
    }
}
