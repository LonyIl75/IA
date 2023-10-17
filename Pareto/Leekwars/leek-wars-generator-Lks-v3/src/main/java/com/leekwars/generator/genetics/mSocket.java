package com.leekwars.generator.genetics;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.io.InputStreamReader;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class mSocket {

    private String host ;
    private int port ;
    private Socket socket = null;

    private boolean isConnected = false;
    private PrintWriter out = null;

    private BufferedReader in = null;



    
    public mSocket(String host , int port ){
        this.host  = host ;
        this.port  = port ;

        Signal.handle(new Signal("INT"), new SignalHandler() {
            public void handle(Signal sig) {
                System.out.println("Received signal " + sig.getName() + ", closing server socket...");

                // Fermer proprement le socket
                try {
                    if(socket != null)
                    socket.close();
                } catch (Exception e) {
                    System.err.println("Error closing server socket: " + e.getMessage());
                }
            }
        });
    }
    

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isConnected() {
        return isConnected;
    }



    

    public boolean m_connect(){

                while(!isConnected){
                    try {
                    System.out.println("SOCKET : "+this.host +" "+this.port +"");  
                    socket = new Socket(this.host , this.port );
                    System.out.println("Connecté au serveur");     
                    isConnected = true;
                    } catch(ConnectException e) {
                    System.out.println("Connect failed, waiting and trying again");
                    try {
                        System.out.println("Wait for serv ...");
                        Thread.sleep(2000);//2 seconds
                    } catch(InterruptedException ie){
                        System.out.println("Interruption");
                        ie.printStackTrace();
                        
                    }
                }catch (Exception e) {
                    System.out.println("Exception 0" + e.getMessage());
                    e.printStackTrace();
                }
            }

            if(socket == null){
                System.out.println("Socket is null");
                isConnected = false;
                return false;
            }
            return true;


    }
    public boolean ouvertureFluxOut() throws IOException{
                    if(socket!=null){
                    OutputStream outputStream = socket.getOutputStream();
                    out = new PrintWriter(outputStream, true);
                    return true;
                }
                return false ; 
                    
                
    }
    public boolean ouvertureFluxIn( ) throws IOException{
                    if(socket!=null ){
                        InputStream inputStream = socket.getInputStream();
                        in = new BufferedReader(new InputStreamReader(inputStream));
                        return true;
                    }
                    return false ; 
                
    }

public void msockclose() throws IOException{
    if(socket!=null){
        socket.close();
        socket = null;
        isConnected = false;
    }
}

public  void  msend(String data ){
    out.println(data);

}

public void mcloseOut(){
    out.close();

    out = null;
}

public void mcloseIn() throws IOException{
    in.close();
    in = null;
}


public int  mrecvall (StringBuilder stringBuilder) throws IOException{
    char[] buffer = new char[1024];
    int charsRead = 0 ;
    int numbyte =  in.read(buffer);
    while (numbyte != -1) {
        charsRead += numbyte;
        stringBuilder.append(buffer, 0, numbyte);
        numbyte =  in.read(buffer);
    }
    return charsRead;
    
}

public int mrecv (char[] mybytearray  ) throws IOException{
    int numbyte = in.read(mybytearray);
    return numbyte;
}

public String  mrecv () throws IOException{
    String res = in.readLine( );
    return res;
}




public boolean init() throws IOException{
    if(m_connect()){
        if(ouvertureFluxOut()){
            if(ouvertureFluxIn()){
                return true;
            }

            mcloseOut();
        }
        msockclose();

    }
    return false;
}


    
}
