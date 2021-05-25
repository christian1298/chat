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
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */
public class CommandSend implements ActionListener
{
  private static Logger lg = OhmLogger.getLogger();
  private ChatView view;
  private Transmitter tm;
  
  public CommandSend(ChatView view, Transmitter tm)
  {
    this.view = view;
    this.tm = tm;
  }

  public void registerEvents()
  {
    view.getBtnSend().addActionListener(this);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    System.out.println("Sending");
    lg.info("Sending Message");
  }
}
