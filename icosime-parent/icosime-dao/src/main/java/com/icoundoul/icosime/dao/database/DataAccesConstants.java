package com.icoundoul.icosime.dao.database;

public interface DataAccesConstants {
	 /**
     * Database error code when we want to insert an id that already exists.
     */
    int DATA_ALREADY_EXIST = 1062;

    /**
     * JDBC Driver class to instanciate.
     */
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * URL of where the database is located.
     */
    String URL_DB = "jdbc:mysql://localhost:3306/icostoresimeDB";

    /**
     * Username to access the database.
     */
    String USER_DB = "root";

    /**
     * Password to access the database.
     */
    String PASSWD_DB = "";
}
