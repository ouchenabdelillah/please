import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hotel.classes.Order;
import hotel.databaseOperation.OrderDb;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class OrderDbTest {

    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private OrderDb orderDb;

    @BeforeEach
    public void setup() throws SQLException {
        // Mock the static DataBaseConnection.connectTODB()
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);

        // Use static mocking for connectTODB
        try (MockedStatic<hotel.databaseOperation.DataBaseConnection> dbConnectionMocked = mockStatic(hotel.databaseOperation.DataBaseConnection.class)) {
            dbConnectionMocked.when(hotel.databaseOperation.DataBaseConnection::connectTODB)
                    .thenReturn(mockConnection);

            when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

            orderDb = new OrderDb(); // Now it uses the mocked connection
        }
    }

    @Test
    public void testInsertOrderSuccess() throws SQLException {
        Order mockOrder = new Order();
        mockOrder.setBookingId(1);
        mockOrder.setFoodItem("Burger");
        mockOrder.setPrice(10.5);
        mockOrder.setQuantity(2);
        mockOrder.setTotal(21.0);

        orderDb.insertOrder(mockOrder);

        verify(mockConnection).prepareStatement(contains("insert into orderItem"));
        verify(mockStatement).execute();
        verify(mockStatement).close();
    }

    @Test
    public void testInsertOrderSQLException() throws SQLException {
        Order mockOrder = new Order();
        mockOrder.setBookingId(1);
        mockOrder.setFoodItem("Pizza");
        mockOrder.setPrice(15.0);
        mockOrder.setQuantity(1);
        mockOrder.setTotal(15.0);

        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));

        // Since JOptionPane will try to show an error popup, we mock it (optional).
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            orderDb.insertOrder(mockOrder);

            mockedJOptionPane.verify(() ->
                    JOptionPane.showMessageDialog(null, "java.sql.SQLException: DB Error\nOrder Failed")
            );
        }
    }

    @Test
    public void testFlushAllResources() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        OrderDb orderDb = new OrderDb();
        orderDb.result = mockResultSet;
        orderDb.statement = mockStatement;

        orderDb.flushAll();

        verify(mockResultSet).close();
        verify(mockStatement).close();
    }

    @Test
    public void testFlushStatementOnly() throws SQLException {
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        OrderDb orderDb = new OrderDb();
        orderDb.statement = mockStatement;

        orderDb.flushAll(); // indirectly tests flushStatmentOnly()

        verify(mockStatement).close();
    }
}
