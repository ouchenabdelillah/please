/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import hotel.classes.UserInfo;

/**
 *
 * @author Faysal Ahmed
 */
public class CustomerDb {
    Connection conn;
    PreparedStatement statement = null;
    ResultSet result = null;
    
    public CustomerDb()
    {
        conn = DataBaseConnection.connectTODB();
    }
      public void insertCustomer(UserInfo user)  {
        try {
            String insertQuery = "INSERT INTO userInfo (name, address, phone, type) VALUES (?, ?, ?, ?)";
            statement = conn.prepareStatement(insertQuery);
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getPhoneNo());
            statement.setString(4, user.getType());


            statement = conn.prepareStatement(insertQuery);

            statement.execute();

            JOptionPane.showMessageDialog(null, "successfully inserted new Customer");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "InsertQuery Failed");
        }
        finally
        {
            flushStatementOnly();
        }
        
        
    }
    
    public void updateCustomer(UserInfo user) {
        try {
            String updateQuery = "UPDATE userInfo SET name = ?, address = ?, phone = ?, type = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getPhoneNo());
            statement.setString(4, user.getType());
            statement.setInt(5, user.getCustomerId()); // assuming it's an int


            statement = conn.prepareStatement(updateQuery);

            statement.execute();

            JOptionPane.showMessageDialog(null, "successfully updated new Customer");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "Update query Failed");
        }
        
        finally
        {
            flushStatementOnly();
        }

    }

    public void deleteCustomer(int userId) throws SQLException {
        try {
            String deleteQuery = "DELETE FROM userInfo WHERE user_id = ?";
            statement = conn.prepareStatement(deleteQuery);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Deleted user");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "Delete query Failed");
        }
        finally
        {
            flushStatementOnly();
        }

    }

    public ResultSet getAllCustomer() {
        try {
            String query = "select * from userInfo";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning all customer DB Operation");
        }
        

        return result;
    }
     private void flushStatementOnly()
    {
        {
                        try
                        {
                            statement.close();
                        }
                        catch(SQLException ex)
                        {System.err.print(ex.toString()+" >> CLOSING DB");}
                    }
    }
     public void flushAll()
    {
        {
                        try
                        {
                            statement.close();
                            result.close();
                        }
                        catch(SQLException ex)
                        {System.err.print(ex.toString()+" >> CLOSING DB");}
                    }
    }

    
    
}
