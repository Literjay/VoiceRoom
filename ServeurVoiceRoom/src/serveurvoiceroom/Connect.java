package serveurvoiceroom;

//

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
  public static void main(String[] args) {      
    try {
      Class.forName("org.postgresql.Driver"); // Changer de driver si BD autre que Postgres
      System.out.println("Driver O.K.");

      String url = "URL DE LA DB";
      String user = "USER";
      String passwd = "PASSWORD";

      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connexion effective !");         
         
    } catch (Exception e) {
      e.printStackTrace();
    }      
  }
}