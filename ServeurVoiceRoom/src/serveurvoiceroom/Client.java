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
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    
    public void run(Room room) throws IOException, ClassNotFoundException{
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
            try {
                line = (String) Int.readObject();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    this.setIdentifiant(line);
                    line = (String) Int.readObject();
                    this.setPassword(line);
                    List <Client> clients = room.getClients();
                    clients.add(this);
                    room.setClients(clients);
                    Out.writeBoolean(true);
                    Out.flush();
                    
                    JSONObject jsRoom = new JSONObject();
                    jsRoom.put("nom", room.getName());
                    JSONArray jsClients = new JSONArray();
                    for (Client c : clients){
                        String name = c.getIdentifiant();
                        jsClients.add(name);
                    }
                    
                    
                    jsRoom.put("clients", jsClients);
                    
                    Out.writeObject(jsRoom);
                    Out.flush();
                    while(true){
                        switch ((String) Int.readObject()) {
                            case "":
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        
    }
}
