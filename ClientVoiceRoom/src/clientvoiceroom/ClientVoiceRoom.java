package clientvoiceroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author pdelmotte
 */
public class ClientVoiceRoom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            Socket mSocket = new Socket(InetAddress.getLocalHost(), 1777);

            ObjectOutputStream mOut = new ObjectOutputStream(mSocket.getOutputStream());
            ObjectInputStream mInt = new ObjectInputStream(mSocket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    
}
