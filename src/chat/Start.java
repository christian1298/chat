/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import chat.view.ChatView;

/**
 *
 * @author Christian
 */
public class Start 
{
  public Start()
  {
    ChatView view = new ChatView();
    view.setSize(800,600);
    view.setVisible(true);
  }

  public static void main(String[] args) 
  {
    new Start();
  }

}
