/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.controller;

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
  private SubmissionPublisher<Integer> iPublisher;
  private ExecutorService eService;
  BufferedReader in;
  PrintWriter out;
  Boolean running;
  
  public Transmitter()
  {
    eService = Executors.newSingleThreadExecutor();
    iPublisher = new SubmissionPublisher<>();
    running = false;
  }
  
  public void addSubscription(Flow.Subscriber<Integer> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }
  
  public void start()
  {
    running = true;
  }
  
  @Override
  public void run()
  {
    while(running)
    {
      System.out.println("Starting Server");
    
    ServerSocket sSocket;
    try
    {
      sSocket = new ServerSocket(9000);
      Socket s = sSocket.accept(); //Blocks

      InputStream iStream = s.getInputStream();
      OutputStream oStream = s.getOutputStream();

      //keine Darstellungsschicht -> nur byte
      InputStreamReader isr = new InputStreamReader(iStream , "UTF-8");
      OutputStreamWriter osr = new OutputStreamWriter(oStream, "UTF-8");

      BufferedReader in = new BufferedReader(isr);
      //BufferedWriter out = new BufferedWriter(osr);
      //besser:
      PrintWriter out = new PrintWriter(osr);
      //iPublisher.submit();
    }
    catch (IOException ex)
    {
      Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    while(true)
    {
      
    }
    }
    
    
  }
}
