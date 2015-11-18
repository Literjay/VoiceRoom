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
    
    private List<Client> Clients = new ArrayList<Client>();
    private String name;

    public List<Client> getClients() {
        return Clients;
    }

    public void setClients(List<Client> Clients) {
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
