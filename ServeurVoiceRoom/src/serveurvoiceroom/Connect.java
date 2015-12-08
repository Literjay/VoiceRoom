package serveurvoiceroom;

//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {

    public static void main(Client unclient) {
        String url = "jdbc:mysql://192.168.1.101:3306/voiceroom";
        String user = "admin";
        String passwd = "Formation";
        
        String nameClient = unclient.getName();
        String pwdClient = unclient.getPassword();

        try {
            System.out.println("chargement du driver");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("récupération de la connexion");
            Connection con = DriverManager.getConnection(url, user, passwd);
            System.out.println("création d'un statement");
            Statement requete = con.createStatement();
            System.out.println("execution d'une requete");
            ResultSet resultat = requete.executeQuery("select * from users where nom="+nameClient+" and password="+pwdClient);
            while (resultat.next()) {
                System.out.println(resultat.getString(1) + "  " + resultat.getString(2));
            };
            System.out.println("fin");
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
}
