package com.leekwars.generator.fight.julienStats.SendPython;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.net.ConnectException;
import java.net.ServerSocket;


public class MyThread extends Thread {

    private String host ;
    private int port ;
    private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    private int codeStop ;
    private String id_connection;

    public MyThread(String id_connection , String host , int port , BlockingQueue<String> queue , int codeStop) {
        this.id_connection = id_connection;
        this.host  = host ;
        this.port  = port ;
        this.queue= queue;
        this.codeStop = codeStop;        
    }

        @Override
        public void run() {
            Socket socket = null;
         
            try {

                boolean notConnected = true;
                while(notConnected){
                    try {
                    System.out.println("SOCKET : "+this.host +" "+this.port +"");  
                    socket = new Socket(this.host , this.port );
                    System.out.println("Connecté au serveur");     
                    notConnected = false;
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
                return;
            }
            // Ouverture des flux de communication
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println(this.id_connection); 
            
            while (true) {
                try {
                    String data = queue.take();
                    if (data.equals(String.valueOf(this.codeStop))) {
                        System.out.println("Stop ");
                        socket.close();
                        break;
                    }
                    //System.out.println("Data Q : "+data);
                    out.println(data);
                } catch (InterruptedException e) {
                    System.out.println("Exception 1");
                    System.err.println("Error occurred:" + e);
                }
            }
        
            } catch (Exception e) {
                System.out.println("Exception 2 ");
            e.printStackTrace();
        } // Remplacez "localhost" par l'adresse IP du serveur si nécessaire
        
    }

    public static String embedMessage (String header , String message){
        return header + getDelimToken(TokenMessage.TOK_FILENAME) + message + getDelimToken(TokenMessage.TOK_END);

    }

    public  enum TokenMessage {
        TOK_FILENAME ,
        TOK_END
    }

    public static String getDelimToken(TokenMessage token){
        switch(token){
            case TOK_FILENAME:
                return getDelimFileName();
            case TOK_END:
                return getDelimEnd();
            default:
                return getEmptyFilename();
        }
    }
    private static String getDelimFileName(){
        return "|";
    }

    private static String  getDelimEnd(){
        return "#" ;
    }
        
    private static String getEmptyFilename(){
        return "";
    }
        
}
    