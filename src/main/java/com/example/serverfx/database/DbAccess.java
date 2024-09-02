package com.example.serverfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La classe DbAccess gestisce l'accesso al database per la lettura dei dati di training.
 * Si occupa dell'inizializzazione della connessione al database, della gestione della connessione e della chiusura della connessione.
 */
public class DbAccess {

	private String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private  String DBMS = "jdbc:mysql";
    private Connection conn;
    private String Stringc;

    /**
     * Imposta la stringa di connessione per il database.
     *
     * @param stringc La stringa di connessione al database.
     */
    public void setStringc(String stringc) {
        Stringc = stringc;
    }

    /**
     * Costruttore che inizializza la connessione al database usando la stringa di connessione fornita.
     * Lancia un'eccezione {@link DatabaseConnectionException} se si verifica un problema durante la connessione.
     *
     * @param stringconnection La stringa di connessione al database.
     * @throws DatabaseConnectionException Se si verifica un errore durante la connessione al database.
     */
    public DbAccess(String stringconnection) throws DatabaseConnectionException {
        setStringc(stringconnection);
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
        String connectionString =  DBMS + "://" +Stringc;

        System.out.println("Connection's String: " + connectionString);
        try {
            DriverManager.setLoginTimeout(5);
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
     * Lancia un'eccezione {@link DatabaseConnectionException} se si verifica un problema durante l'inizializzazione della connessione.
     *
     * @throws DatabaseConnectionException Se si verifica un errore durante l'inizializzazione della connessione.
     */
    public void initConnection() throws DatabaseConnectionException
    {
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch(ClassNotFoundException e) {
            System.out.println("[!] Driver not found: " + e.getMessage());
            throw new DatabaseConnectionException(e.toString());
        }
        String connectionString = DBMS + "://" +Stringc;

        
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch(SQLException e) {
            throw new DatabaseConnectionException(e.toString());
        }
    }

    /**
     * Restituisce la connessione al database.
     * Se la connessione non è già inizializzata, la inizializza prima di restituirla.
     *
     * @return La connessione al database.
     * @throws DatabaseConnectionException Se si verifica un errore durante l'ottenimento della connessione.
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
