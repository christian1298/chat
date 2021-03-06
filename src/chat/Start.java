/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import chat.controller.CommandConnect;
import chat.controller.CommandSend;
import chat.controller.ReceiveAdapter;
import chat.controller.Transmitter;
import chat.view.ChatView;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author Christian
 */
public class Start 
{
  public Start()
  {
    ChatView view = new ChatView();
    Transmitter tm = new Transmitter();
    CommandSend send = new CommandSend(view,tm);
    CommandConnect con = new CommandConnect(view,tm);
    ReceiveAdapter rec = new ReceiveAdapter(view,tm);
    rec.onSubscription();
    send.registerEvents();
    con.registerEvents();
    
    view.setSize(800,600);
    view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    view.setVisible(true);
    
  }

  public static void main(String[] args) 
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, e.toString());
    }
    new Start();
  }

}
