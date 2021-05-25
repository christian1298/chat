/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * @author Christian
 */
public class Transmitter implements Runnable
{
  private SubmissionPublisher<Integer> iPublisher;
  private ExecutorService eService;
  
  public Transmitter()
  {
    eService = Executors.newSingleThreadExecutor();
    iPublisher = new SubmissionPublisher<>();
  }
  
  public void addSubscription(Flow.Subscriber<Integer> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }

  @Override
  public void run()
  {
    
    //iPublisher.submit();
  }
}
