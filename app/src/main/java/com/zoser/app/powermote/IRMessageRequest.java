package com.zoser.app.powermote;

import java.util.ArrayList;

public class IRMessageRequest
{
    private ArrayList<IRMessage> _messages = new ArrayList<IRMessage>();

    public IRMessageRequest(IRMessage... args)
    {
        for(int a=0;a<args.length;++a)
        {
            _messages.add(args[a]);
        }
    }

    public ArrayList<IRMessage> getMessages()
    {
        return _messages;
    }

    public long getRequestTime()
    {
        long time = 0;
        for(int a=0;a<_messages.size();++a)
        {
            time += _messages.get(a).getMessageTime();
        }
        return time;
    }
}
