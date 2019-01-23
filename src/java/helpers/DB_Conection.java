/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
public class DB_Conection {
      public static Connection getConnection() throws NamingException, SQLException {
        Connection conn = null;
//        public static final String DB_URL="localhost:3306";
//        public static final String FULL_DB_URL="jdbc:mysql://" + DB_URL + "/chat_db?zeroDateTimeBehavior=convertToNull&useSSL=false";
//        public static final String DB_USER="root";
//        public static final String DB_PASSWORD="11/1/1990";       
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
            conn = ds.getConnection();
            
        } catch (NamingException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return conn;
    }
    
}
