/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurvoiceroom;

import javax.net.ssl.*;
import javax.net.*;
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
    public static void main(String[] args) {
        ServerSocketFactory serverSocketFactory =
        ServerSocketFactory.getDefault();
        ServerSocket serverSocket = null;
        try {
          serverSocket =
            serverSocketFactory.createServerSocket(PORT_NUM);
        } catch (IOException ignored) {
          System.err.println("Unable to create server");
          System.exit(-1);
        }
        System.out.printf("LogServer running on port: %s%n", PORT_NUM);
        while (true) {
          Socket socket = null;
          try {
            socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(
              new InputStreamReader(is, "US-ASCII"));
            String line = null;
            while ((line = br.readLine()) != null) {
              System.out.println(line);
            }
          } catch (IOException exception) {
            // Just handle next request.
          } finally {
            if (socket != null) {
              try {
                socket.close();
              } catch (IOException ignored) {
              }
            }
          }
        }
    }
    
}
