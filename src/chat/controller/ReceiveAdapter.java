/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.controller;

import chat.view.ChatView;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

/**
 *
 * @author Christian
 */
public class ReceiveAdapter implements Subscriber
{
  private ChatView view;
  private Transmitter tm;
  
  public ReceiveAdapter(ChatView view, Transmitter tm)
  {
    this.view = view;
    this.tm = tm;
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription)
  {
  }

  @Override
  public void onNext(Object item)
  {
  }

  @Override
  public void onError(Throwable thrwbl)
  {
  }

  @Override
  public void onComplete()
  {
  }
  
    
//  public String leseInhalt(String urlAdresse)
//  {
//    URL urlQuelle;
//    try
//    {
//      urlQuelle = new URL(urlAdresse);
//    }
//    catch(MalformedURLException urlex)
//    {
//      //JOptionPane.showMessageDialog(frm, urlex);
//    }
//    
//    StringBuffer puffer = new StringBuffer();
//    String zeile;
//    
//    try
//    {
//      InputStream is = urlQuelle.openStream();
//      InputStreamReader isr = new InputStreamReader(is);
//      BufferedReader buffin = new BufferedReader(isr);
//      while((zeile = buffin.readLine()) != null)
//      {
//        puffer.append(zeile);
//        puffer.append("\n");
//      }
//      buffin.close();
//      is.close();
//    }
//    catch(IOException ioex)
//    {
//      //JoptionPane.showMessageDialog(frm, ioex);
//    }
//    return (puffer.toString());
//  }
}


