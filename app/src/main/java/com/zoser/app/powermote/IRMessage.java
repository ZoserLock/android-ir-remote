package com.zoser.app.powermote;

public class IRMessage
{
    public static int FREQ_37_KHZ = 37000;
    public static int FREQ_38_KHZ = 38000;
    public static int FREQ_40_KHZ = 40000;

    private int _frequency;
    private int _messageTime;
    private int [] _message;

    public IRMessage(int frequency, int [] message)
    {
        _frequency   = frequency;
        _message     = message;

        calculateMessageTime();
    }

    public int getFrequency()
    {
        return _frequency;
    }

    public int[] getMessage()
    {
        return _message;
    }

    public int getMessageTime()
    {
        return _messageTime;
    }

    private void calculateMessageTime()
    {
        int time = 0;
        for(int a=0;a < _message.length;++a)
        {
            time += _message[a];
        }
        _messageTime = time;
    }
}

