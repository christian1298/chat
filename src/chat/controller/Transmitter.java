/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.controller;

import chat.util.OhmLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */
public class Transmitter implements Runnable
{
  private static Logger lg = OhmLogger.getLogger();
  
  private SubmissionPublisher<String> iPublisher;
  private ExecutorService eService;
  private BufferedReader in;
  private PrintWriter out;
  private Boolean running;
  private Boolean mode; // true = Server, false = client
  private String IP;
  private static final int PORT = 35000;
  private Boolean connected;
  
  
  public Transmitter()
  {
    eService = Executors.newSingleThreadExecutor();
    iPublisher = new SubmissionPublisher<>();
    running = false;
    mode = false;
    IP = "";
    connected = false;
  }
  
  public Boolean isConnected()
  {
    return connected;
  }
  
  public void addSubscription(Flow.Subscriber<String> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }
  
 public synchronized void start()
  {
    running = true;
    lg.info("Start");
    // LSG 3 \\
    //notifyAll();
    // LSG 3 \\
    
    eService.submit(this);
  }
  
  public void setServer() // Set Mode to SERVER mode
  {
    mode = true;
  }
  
  public void setClient() // Set Mode to CLIENT mode
  {
    mode = false;
  }
  
  public void setIP(String newIP)
  {
    IP = newIP;
  }
  
  public void sendMSG(String msg)
  {
    out.println(msg);
    out.flush();
  }
  
  @Override
  public void run()
  {
    while(running)
    { 
    if(mode == true) // if Server
    {
      lg.info("Starting Server");
      System.out.println("Starting Server");
      ServerSocket sSocket;
      try
      {
        sSocket = new ServerSocket(PORT);
        Socket s = sSocket.accept(); //Blocks

        InputStream iStream = s.getInputStream();
        OutputStream oStream = s.getOutputStream();

        //keine Darstellungsschicht -> nur byte
        InputStreamReader isr = new InputStreamReader(iStream , "UTF-8");
        OutputStreamWriter osr = new OutputStreamWriter(oStream, "UTF-8");

        in = new BufferedReader(isr);
        //BufferedWriter out = new BufferedWriter(osr);
        //besser:
        out = new PrintWriter(osr);
        //iPublisher.submit();
        
        System.out.println("Server Initialized");
        lg.info("Server Initialized");
        
        connected = true;
        iPublisher.submit("--Connection Established, running as Server--");
      }
      catch (IOException ex)
      {
        Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    else if(mode == false) // if Client
    {
      lg.info("Starting Client");
      System.out.println("Starting Client");
      
      Socket s;
      try
      {
        s = new Socket(IP, PORT);  // ACHTUNG blockiert!!!
        
        InputStream iStream = s.getInputStream();
        OutputStream oStream = s.getOutputStream();

        //keine Darstellungsschicht -> nur byte
        InputStreamReader isr = new InputStreamReader(iStream , "UTF-8");
        OutputStreamWriter osr = new OutputStreamWriter(oStream, "UTF-8");

        in = new BufferedReader(isr);
        //BufferedWriter out = new BufferedWriter(osr);
        //besser:
        out = new PrintWriter(osr);
        
        
        System.out.println("Client Initialized");
        lg.info("Client Initialized");
        
        connected = true;
        iPublisher.submit("--Connection Established, running as Client--");
      }
      catch (IOException ex)
      {
        Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
      }
      

    }
    
    while(true)
    {
      try
      {
        String nachricht = in.readLine();  //warte auf eine Textzeile -> BLOCKIERT!
        lg.info("Nachricht: " + nachricht);
        iPublisher.submit(nachricht);
      }
      catch (IOException ex)
      {
        Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }
    
    }
  }
}
