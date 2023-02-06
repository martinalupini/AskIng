package it.uniroma2.dicii.ispw.progetto.lupini.dao;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering.Credentials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*for this class I use the pattern singleton: in this way I will have only one connection to the DBMS*/
public class DBMSConnection {
    private static final String USER="root";
    private static final String CONNECTION_URL="jdbc:mysql://localhost:3306/ispwdatabase";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;
    private static DBMSConnection instanceConnection = null;

    private DBMSConnection(){}

    public static DBMSConnection getInstanceConnection() {
        if(instanceConnection == null){
            instanceConnection = new DBMSConnection();
        }

        return instanceConnection;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        if(conn == null) {

                Class.forName(DRIVER_CLASS_NAME);

                conn = DriverManager.getConnection(CONNECTION_URL, USER, Credentials.getCredentials().getPass());
        }

        return conn;
    }

}
