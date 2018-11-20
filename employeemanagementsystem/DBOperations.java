/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class DBOperations {

    String url = "jdbc:mysql://localhost:3306/login";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    boolean addEmployee(EmployeeDetails em) {
        try {

            con = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO employeeDetails VALUES(?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, em.getRegid());
            pst.setString(2, em.getFirstName());
            pst.setString(3, em.getLastName());
            pst.setInt(4, em.getAge());
            pst.setString(5, em.getCountry());
            pst.setString(6, em.getEmail());
            pst.setString(7, em.getUserName());
            pst.setString(8, em.getPassword());

            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    int checkUserName(String userName) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT username FROM employeedetails ";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (userName.equals(rs.getString(1))) {
                    return 0;
                }
            }
            return 1;

        } catch (Exception e) {
            System.out.print(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }

    int checkPassword(String pw) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT password FROM employeedetails ";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (pw.equals(rs.getString(1))) {
                    return 3;
                }
            }
            return 4;

        } catch (Exception e) {
            System.out.print(e);
            return 5;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }

    }
}
