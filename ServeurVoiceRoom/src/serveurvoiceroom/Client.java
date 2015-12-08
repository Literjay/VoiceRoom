/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurvoiceroom;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author pdelmotte
 */
public class Client extends Thread{
    
    private String Identifiant;
    private String password;
    protected Socket socket;
    protected Room room;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    
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
    
    public Client(Socket socket, Room room) {
        this.socket = socket;
        this.room=room;
    }
    
    @Override
    public void run(){
        
            try {
                ObjectInputStream Int = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream Out =  new ObjectOutputStream(socket.getOutputStream());
                String line;
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
                    sendList(clients,this);
                    int bytesRead = 0;
                    byte[] inBytes = new byte[1];
                    while(bytesRead != -1)
                    {
                        try{bytesRead = Int.read(inBytes, 0, inBytes.length);}catch (IOException e){}
                        if(bytesRead >= 0)
                        {
                            sendToAll(inBytes, bytesRead,clients,this);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /*
    public void rundata(Room room) throws IOException, ClassNotFoundException{
        for(Client client : room.getClients()){
             ObjectOutputStream Out =  new ObjectOutputStream(client.socketdata.getOutputStream());
             ObjectInputStream Int = new ObjectInputStream(client.socketdata.getInputStream());
             try{
                    
                    Out.writeObject("Nouveau Client");
                    Out.flush();
                    Out.writeObject(this.Identifiant);
                    Out.flush();

                } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
        }
        while(true){
                ObjectOutputStream Out =  new ObjectOutputStream(this.socketdata.getOutputStream());
                ObjectInputStream Int = new ObjectInputStream(this.socketdata.getInputStream());
                try{
                    String line = (String) Int.readObject();
                      switch (line){
                          case "":
                              break;
                      }            

                   } catch (IOException e) {
                           e.printStackTrace();
                           return;
                       }
            }
        
    }
    */
    public static void sendToAll(byte[] byteArray, int q, List<Client> clients,Client client)
    {
        Iterator<Client> sockIt = clients.iterator();
        while(sockIt.hasNext())
        {
            Client temp = sockIt.next();
            if(temp.getIdentifiant()!=client.getIdentifiant()){
                
                DataOutputStream tempOut = null;
                try
                {
                    tempOut = new DataOutputStream(temp.getSocket().getOutputStream());
                } catch (IOException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try{tempOut.write(byteArray, 0, q);}catch (IOException e){}
            }
        }
    }
    
    public static void sendList(List<Client> clients,Client client)
    {
        Iterator<Client> sockIt = clients.iterator();
        while(sockIt.hasNext())
        {
            Client temp = sockIt.next();
            if(temp.getIdentifiant()!=client.getIdentifiant()){
                
                DataOutputStream tempOut = null;
                try
                {
                    tempOut = new DataOutputStream(temp.getSocket().getOutputStream());
                } catch (IOException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try{tempOut.writeUTF("Nouveau Client");tempOut.writeUTF(client.getIdentifiant());tempOut.flush();}catch (IOException e){}
            }
        }
    }
}
