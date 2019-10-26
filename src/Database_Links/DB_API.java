package Database_Links;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_API {
    public static final String dbDRIVER = "com.mysql.jdbc.Driver";
    public static final String DRIVER = "jdbc:mysql://localhost/Masomo";
    public static final String PASSWORD = "morris";
    public static final String USER = "root";
    static Connection kon;

    public static Connection getKonnection(){
        try{
            Class.forName(dbDRIVER);
            kon = DriverManager.getConnection(DRIVER,USER, PASSWORD);

        }catch (SQLException ex){
            System.out.println(ex);
            Logger.getLogger(DB_API.class.getName()).log(Level.SEVERE, null, ex);

        }catch (ClassNotFoundException ex){
            System.out.println(ex);
            Logger.getLogger(DB_API.class.getName()).log(Level.SEVERE, null, ex);
        }//end of try catch

        //System.out.println("Status of connection is ===> "+kon);
        return kon;
    }

}//end of class
