/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurvoiceroom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author pdelmotte
 */
public class Client {
    
    private String Identifiant;
    private String password;
    protected Socket socket;
    
    public String getIdentifiant() {
        return Identifiant;
    }

    public void setIdentifiant(String Identifiant) {
        this.Identifiant = Identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Client(Socket socket) {
        this.socket = socket;
    }
    
    public void run() throws IOException, ClassNotFoundException{
        ObjectInputStream Int = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream Out =  new ObjectOutputStream(socket.getOutputStream());
        /*InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }*/
        String line;
        while (true) {
            try {
                line = (String) Int.readObject();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    Out.writeBytes(line + "\n\r");
                    Out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
