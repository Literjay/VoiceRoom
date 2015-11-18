package serveurvoiceroom;

import java.sql.Connection;
import java.sql.ResultSet;

public class ClientDAO extends DAO<Client> {
  public ClientDAO(Connection conn) {
    super(conn);
  }

  public boolean create(Client obj) {
    return false;
  }

  public boolean delete(Client obj) {
    return false;
  }

  public boolean update(Client obj) {
    return false;
  }

  public Client find(int id) {
      return null;
//    Client client = new Client();  
//
//    try {
//      ResultSet result = this.connect.createStatement(
//        ResultSet.TYPE_SCROLL_INSENSITIVE, 
//        ResultSet.CONCUR_READ_ONLY
//      ).executeQuery("SELECT * FROM client WHERE mat_id = " + id);
//        if(result.first())
//          client = new Client(id, result.getString("mat_nom"));         
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return client;
//  }
}
}
