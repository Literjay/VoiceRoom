package graphique_interface;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pdelmotte
 */
public class Room {
    
    private ArrayList<Client> Clients = new ArrayList<Client>();
    private String name;

    public ArrayList<Client> getClients() {
        return Clients;
    }

    public void setClients(ArrayList<Client> Clients) {
        this.Clients = Clients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room(String name) {
        this.name = name;
    }
    }
