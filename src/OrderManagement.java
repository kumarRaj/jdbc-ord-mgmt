import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderManagement {
    public static final String DB_URL = "jdbc:mysql://localhost/jdbc_order_mgmt";
    Connection con;

    public OrderManagement(String user, String password) throws SQLException {
        con = DriverManager.getConnection(DB_URL, user, password);
    }

    public int createProduct() throws SQLException {
        String createProductTable = "CREATE TABLE product (\n" +
                "\tp_id VARCHAR(15) NOT NULL,\n" +
                "\tp_name VARCHAR(50) NULL DEFAULT NULL,\n" +
                "\tp_price SMALLINT(5) UNSIGNED NULL DEFAULT NULL,\n" +
                "\tQuantity SMALLINT(5) UNSIGNED NULL DEFAULT NULL,\n" +
                "\tPRIMARY KEY (p_id)\n" +
                ");";
        PreparedStatement statement = con.prepareStatement(createProductTable);
        return statement.executeUpdate();
    }

    public int createCustomer() throws SQLException {
        String createCustomerTable = "CREATE TABLE customer (\n" +
                "\tcust_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "\tcustomer_name VARCHAR(50) NOT NULL,\n" +
                "\tcontact_no BIGINT(20) NULL DEFAULT NULL,\n" +
                "\tPRIMARY KEY (cust_id)\n" +
                ")";
        PreparedStatement statement = con.prepareStatement(createCustomerTable);
        return statement.executeUpdate();
    }

    public int createOrders() throws SQLException {
        String createOrdersTable = "CREATE TABLE orders (\n" +
                "\torder_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "\tcust_id INT(10) UNSIGNED NULL DEFAULT '0',\n" +
                "\tAmount INT(10) UNSIGNED NULL DEFAULT NULL,\n" +
                "\tPRIMARY KEY (order_id),\n" +
                "\tINDEX cust_id_FK_ (cust_id),\n" +
                "\tCONSTRAINT cust_id_FK_ FOREIGN KEY (cust_id) REFERENCES customer (cust_id) ON UPDATE CASCADE ON DELETE CASCADE\n" +
                ")\n";
        PreparedStatement statement = con.prepareStatement(createOrdersTable);
        return statement.executeUpdate();
    }

    public int createOrderItems() throws SQLException {
        String createOrderItemsTable = "CREATE TABLE order_items (\n" +
                "\torder_id INT(10) UNSIGNED NULL DEFAULT NULL,\n" +
                "\tp_id VARCHAR(15) NULL DEFAULT NULL,\n" +
                "\tquantity SMALLINT(5) UNSIGNED NULL DEFAULT NULL,\n" +
                "\tINDEX order_FK_orderId (order_id),\n" +
                "\tINDEX product_FK_p_id (p_id),\n" +
                "\tCONSTRAINT order_FK_orderId FOREIGN KEY (order_id) REFERENCES orders (order_id) ON UPDATE CASCADE ON DELETE CASCADE,\n" +
                "\tCONSTRAINT product_FK_p_id FOREIGN KEY (p_id) REFERENCES product (p_id) ON UPDATE CASCADE ON DELETE CASCADE\n" +
                ")\n";
        PreparedStatement statement = con.prepareStatement(createOrderItemsTable);
        return statement.executeUpdate();
    }

    public int dropTable(String tableName) throws SQLException {
        String deleteQuery = "drop table " + tableName;
        PreparedStatement statement = con.prepareStatement(deleteQuery);
        return statement.executeUpdate();
    }

    public int addProduct(String p_id, String p_name, int p_price, int Quantity) throws SQLException {
        String product = "insert into product values ('" + p_id + "','" + p_name + "','" + p_price + "','" + Quantity + "')";
        PreparedStatement insertProduct = con.prepareStatement(product);
        return insertProduct.executeUpdate();
    }
    public int addCustomer(String cust_id, String customer_name, int contact_no) throws SQLException {
        String product = "insert into customer values ('" + cust_id + "','" + customer_name + "','" + contact_no + "')";
        PreparedStatement insertProduct = con.prepareStatement(product);
        return insertProduct.executeUpdate();
    }

    public int addOrder(String order_id, String cust_id, int Amount) throws SQLException {
        String product = "insert into customer values ('" + order_id + "','" + cust_id + "','" + Amount + "')";
        PreparedStatement insertProduct = con.prepareStatement(product);
        return insertProduct.executeUpdate();
    }


}