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
public class ReceiveAdapter implements Subscriber<String>
{
  private ChatView view;
  private Transmitter tm;
  private Flow.Subscription subscription;
  
  public ReceiveAdapter(ChatView view, Transmitter tm)
  {
    this.view = view;
    this.tm = tm;
  }

  public void onSubscription()
  {
    tm.addSubscription(this);
  }
  
  @Override
  public void onSubscribe(Flow.Subscription subscription)
  {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onError(Throwable thrwbl)
  {
  }

  @Override
  public void onComplete()
  {
  }
  
  @Override
  public void onNext(String item)
  {
    
    view.getTAUser().setText(view.getTAUser().getText().concat("\n"));
    view.getTAExtern().setText(view.getTAExtern().getText().concat("\n" + item));
    
    
    
    subscription.request(1);
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


