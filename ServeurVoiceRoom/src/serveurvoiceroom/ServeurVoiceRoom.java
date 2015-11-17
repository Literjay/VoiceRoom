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
    private static final int PORT_NUM = 1777;
    static final int PORT = 1978;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new threa for a client
            new EchoThread(socket).start();
        }
    }
    
}
