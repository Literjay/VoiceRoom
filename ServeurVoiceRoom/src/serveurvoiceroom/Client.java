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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
    protected Socket socketData;
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

    public Socket getSocketData() {
        return socketData;
    }

    public void setSocketData(Socket socketData) {
        this.socketData = socketData;
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
                    
                    if(authentification(this)){
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
                        ObjectOutputStream OutData = new ObjectOutputStream(this.getSocketData().getOutputStream());
                        //sendList(clients,this,OutData);
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
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
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
                try{
                    tempOut.write(byteArray, 0, q);}catch (IOException e){}
            }
        }
    }
    
    public static void sendList(List<Client> clients,Client client, ObjectOutputStream OutData)
    {
        Iterator<Client> sockIt = clients.iterator();
        while(sockIt.hasNext())
        {
            Client temp = sockIt.next();
            if(temp.getIdentifiant()!=client.getIdentifiant()){
                
                DataOutputStream tempOut = null;
                try{
                    OutData.writeUTF("Nouveau Client");
                    OutData.flush();
                    OutData.writeUTF(client.getIdentifiant());
                    OutData.flush();
                }catch (IOException e){
                    int i = 0;
                }
            }
        }
    }
    
    
        public boolean authentification(Client unclient) {
        String url = "jdbc:mysql://192.168.1.101:3306/voiceroom";
        String user = "admin";
        String passwd = "Formation";
        
        String nameClient = unclient.getIdentifiant();
        String pwdClient = unclient.getPassword();
        boolean authentified = true;
        try {
            System.out.println("chargement du driver");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("récupération de la connexion");
            Connection con = DriverManager.getConnection(url, user, passwd);
            System.out.println("création d'un statement");
            Statement requete = con.createStatement();
            System.out.println("execution d'une requete");
            ResultSet resultat = requete.executeQuery("select * from users where nom='"+nameClient+"' and password='"+pwdClient+"'");

            if(!resultat.next()){
                authentified = false;
            }
            System.out.println("fin");
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
        return authentified;
    }
    
}
