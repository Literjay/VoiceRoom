/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurvoiceroom;


import java.io.*;
import java.net.*;

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
        while (true) {
            try {
                socket = serverSocket.accept();
                new Client(socket).run(room);
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            

        }
    }
    
}
