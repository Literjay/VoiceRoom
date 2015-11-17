/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurvoiceroom;

/**
 *
 * @author pdelmotte
 */
public class Client {
    
    private String Identifiant;
    private String password;
    private EchoThread thread; 
    
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

    public EchoThread getThread() {
        return thread;
    }

    public void setThread(EchoThread thread) {
        this.thread = thread;
    }
}
