/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.controller;

import chat.util.OhmLogger;
import chat.view.ChatView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */
public class CommandConnect implements ActionListener
{
  private static Logger lg = OhmLogger.getLogger();
  private ChatView view;
  private Transmitter tm;
  
  public CommandConnect(ChatView view, Transmitter tm)
  {
    this.view = view;
    this.tm = tm;
  }
  
  public void registerEvents()
  {
    view.getBtnConnect().addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if(view.getSelectSC().getSelectedItem() == "Server" && tm.isConnected() == false)
    {
      lg.info("Server");
      tm.setServer();
      tm.start();
      
    }
    else if(view.getSelectSC().getSelectedItem() == "Client" && tm.isConnected() == false)
    {
      lg.info("Client");
      tm.setClient();
      tm.setIP(view.getTfIP().getText());
      tm.start();
    }
    
    //System.out.println("Connecting");
    //lg.info("Connecting");
  }
}
