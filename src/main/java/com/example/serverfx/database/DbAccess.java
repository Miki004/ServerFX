package com.example.serverfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce l'accesso al DB per la lettura dei dati di training
 * @author Map Tutor
 *
 */
public class DbAccess {

	private String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private  String DBMS = "jdbc:mysql";
    private  String SERVER = "localhost";
    private  String DATABASE = "MapDB";
    private  int PORT = 3306;
    private  String USER_ID = "MapUser";
    private  String PASSWORD = "Onepiece.1!";

    private Connection conn;

    public DbAccess() throws DatabaseConnectionException {
        try {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
        } catch(ClassNotFoundException e) {
            System.out.println("[!] Driver not found: " + e.getMessage());
            throw new DatabaseConnectionException();
        } catch(InstantiationException e){
            System.out.println("[!] Error during the instantiation : " + e.getMessage());
            throw new DatabaseConnectionException();
        } catch(IllegalAccessException e){
            System.out.println("[!] Cannot access the driver : " + e.getMessage());
            throw new DatabaseConnectionException();
        }
        String connectionString = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE
                + "?user=" + USER_ID + "&password=" + PASSWORD + "&serverTimezone=UTC";

        System.out.println("Connection's String: " + connectionString);
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch(SQLException e) {
            System.out.println("[!] SQLException: " + e.getMessage());
            System.out.println("[!] SQLState: " + e.getSQLState());
            System.out.println("[!] VendorError: " + e.getErrorCode());
            throw new DatabaseConnectionException();
        }
    }

    /**
     * Inizializza la connessione al database.
     *
     * @throws DatabaseConnectionException Eccezione lanciata se la connessione al database fallisce.
     */
    public void initConnection() throws DatabaseConnectionException
    {
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch(ClassNotFoundException e) {
            System.out.println("[!] Driver not found: " + e.getMessage());
            throw new DatabaseConnectionException(e.toString());
        }
        String connectionString = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE
                + "?user=" + USER_ID + "&password=" + PASSWORD + "&serverTimezone=UTC";

        
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch(SQLException e) {
           
            throw new DatabaseConnectionException(e.toString());
        }
    }

    /**
     * Restituisce la connessione al database.
     *
     * @return Connessione al database.
     * @throws DatabaseConnectionException Eccezione lanciata se la connessione al database fallisce.
     */
    public Connection getConnection() throws DatabaseConnectionException{
        this.initConnection();
        return conn;
    }

    /**
     * Chiude la connessione al database.
     *
     * @throws SQLException Eccezione lanciata se si verifica un errore durante la chiusura della connessione.
     */
    public void closeConnection() throws SQLException {
        conn.close();
    }

}
