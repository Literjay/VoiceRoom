/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique_interface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vagam
 */
public class ThreadClient extends Thread{
    protected Socket mSocket;
    protected ObjectOutputStream mOut;
    protected ObjectInputStream mInt;
    protected PrincipaleFrame mFrame;
    
    
    public ThreadClient(Socket socket, PrincipaleFrame frame){
        mSocket = socket;
        mFrame = frame;
    }
    
    @Override
    public void run(){
        try {
            mOut = new ObjectOutputStream(mSocket.getOutputStream());
            mInt = new ObjectInputStream(mSocket.getInputStream());
            
            while(true)
                        {
                            String data =mInt.readUTF();
                            if(data=="Nouveau Client"){
                                mFrame.setNameClient(mInt.readUTF());
                            }
                        }
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
    
    
}
