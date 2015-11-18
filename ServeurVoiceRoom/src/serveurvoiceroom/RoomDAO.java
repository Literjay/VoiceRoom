package serveurvoiceroom;

import java.sql.Connection;
import java.sql.ResultSet;

public class RoomDAO extends DAO<Room> {
  public RoomDAO(Connection conn) {
    super(conn);
  }

  public boolean create(Room obj) {
    return false;
  }

  public boolean delete(Room obj) {
    return false;
  }

  public boolean update(Room obj) {
    return false;
  }

  public Room find(int id) {
      return null;
//    Room room = new Room();  
//
//    try {
//      ResultSet result = this.connect.createStatement(
//        ResultSet.TYPE_SCROLL_INSENSITIVE, 
//        ResultSet.CONCUR_READ_ONLY
//      ).executeQuery("SELECT * FROM room WHERE mat_id = " + id);
//        if(result.first())
//          room = new Room(id, result.getString("mat_nom"));         
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return room;
//  }
}
}