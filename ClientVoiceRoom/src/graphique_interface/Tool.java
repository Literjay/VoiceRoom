/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package graphique_interface;

import java.net.Socket;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
*
* @author Betty
*/
public class Tool {
   protected String mIp;
   protected boolean Etatmicrophone;
   protected TargetDataLine micro;
   protected Socket mSocket;
   protected Thread inThread; 
   protected boolean Etatheadset;
   
   public void OpenMicrophone() throws LineUnavailableException {
       micro.start();
       this.Etatmicrophone=true;
   }

   public void StopMicrophone(){
       micro.stop();
       this.Etatmicrophone=false;
   }
   
   public void OpenHeadset() throws LineUnavailableException, Exception {
       inThread.start();
       this.Etatheadset=true;
   }

   public void StopHeadset(){
       inThread.interrupt();
       this.Etatheadset=false;
   }
   
   public void setIp(String ip){
       mIp = ip;
   }
   
   public String getIp(){
       return mIp;
   }

   public Tool(Socket mSocketdata) throws Exception,LineUnavailableException {
       this.mSocket = mSocketdata;
       inThread = new Thread(new SoundReceiver(mSocket));
       AudioFormat af=new AudioFormat(8000.0f,8,1,true,false);
       DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
       micro =(TargetDataLine)AudioSystem.getLine(info);
       micro.open(af);
       OpenMicrophone();
       OpenHeadset();
   }
   
                           
}