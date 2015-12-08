/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurvoiceroom;


import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pdelmotte
 */
public class ServeurVoiceRoom {

    /**
     * @param args the command line arguments
     */
    static final int PORT = 1777;
    public static void main(String args[]) throws ClassNotFoundException {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        Room room = new Room("Accueil");
        Map <String,Client> Clients=new HashMap <String, Client> ();
        while (true) {
            try {
                socket = serverSocket.accept();/*
                if(Clients.containsKey(socket.getRemoteSocketAddress().toString())){
                    Clients.get(socket.getRemoteSocketAddress().toString()).setSocketdata(socket);
                    Clients.get(socket.getRemoteSocketAddress().toString()).rundata(room);
                }
                else {*/
                    Client client=new Client(socket);
                    Clients.put(socket.getRemoteSocketAddress().toString(), client);
                    client.run(room);
                //}
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            

        }
    }
    
}
