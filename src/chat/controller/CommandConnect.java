/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.controller;

import chat.view.ChatView;

/**
 *
 * @author Christian
 */
public class CommandConnect 
{
  private ChatView view;
  private Transmitter tm;
  
  public CommandConnect(ChatView view, Transmitter tm)
  {
    this.view = view;
    this.tm = tm;
  }
}
