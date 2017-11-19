package com.zoser.app.powermote;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IRController extends Thread
{
    private static int MAX_QUEUED_COUNT = 2;
    private static int MAX_WAIT_PER_PULSE_MS = 40;
    private static int MAX_WAIT_PER_MESSAGE_MS = 40;

    private ConsumerIrManager _irManager;

    private Context _context = null;

    private boolean _enabled = false;

    private Object _messageLock = new Object();
    private Queue<IRMessageRequest> _messageQueue = new LinkedList<IRMessageRequest>();

    public IRController(Context context)
    {
        _context = context;
        _irManager = (ConsumerIrManager)context.getSystemService(Context.CONSUMER_IR_SERVICE);

        if(_irManager != null)
        {
            if(_irManager.hasIrEmitter())
            {
                _enabled = true;
            }
        }
    }

    private void executeMessage(IRMessageRequest request)
    {
        if(_enabled)
        {
            ArrayList<IRMessage> messages = request.getMessages();
            for(int a = 0;a < messages.size();++a)
            {
                IRMessage message = messages.get(a);

                int frequency = message.getFrequency();
                int [] codes = message.getMessage();

                _irManager.transmit(frequency, codes);
                WaitPerMessage();
            }
        }
    }

    public long sendMessage(IRMessageRequest request)
    {
        synchronized(_messageLock)
        {
            if(_messageQueue.size() < MAX_QUEUED_COUNT)
            {
                _messageQueue.add(request);
                _messageLock.notify();
            }
        }

        if(request != null)
        {
            long time = request.getRequestTime();
            return time;
        }
        else
        {
            return 0;
        }
    }


    public void startWork()
    {
        this.start();
    }

    public void stopWork()
    {
        sendMessage(null);
        try
        {
            join();
        }
        catch(InterruptedException e)
        {
            Log.d("Zoser","Interupted");
        }
    }

    @Override
    public void run()
    {
        Log.d("Zoser","Worker Starts!");
        while(true)
        {

            IRMessageRequest message = null;
            synchronized(_messageLock)
            {
                if(_messageQueue.size() > 0)
                {
                    message = _messageQueue.poll();

                    if(message == null)
                    {
                        Log.d("Zoser", "Closing Worker!");
                        break;
                    }
                }
                else
                {
                    try
                    {
                        _messageLock.wait();
                    }
                    catch(InterruptedException e)
                    {
                        Log.d("Zoser","Interupted");
                    }
                }
            }

            if(message != null)
            {
                executeMessage(message);
            }

            WaitPerPulse();

        }
        Log.d("Zoser","Worker Stops!");
    }

    private void WaitPerPulse()
    {
        try
        {
            Thread.sleep(MAX_WAIT_PER_PULSE_MS);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    private void WaitPerMessage()
    {
        try
        {
            Thread.sleep(MAX_WAIT_PER_MESSAGE_MS);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
