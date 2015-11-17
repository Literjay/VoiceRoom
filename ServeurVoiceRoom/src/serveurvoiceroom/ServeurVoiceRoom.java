/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurvoiceroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author pdelmotte
 */
public class ServeurVoiceRoom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {

            ServerSocket listener = new ServerSocket(1777);
            System.out.println("Attente d'une reponse");
            
            Socket socket = listener.accept();
            System.out.println("reponse recu");
            
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            

        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    
}
