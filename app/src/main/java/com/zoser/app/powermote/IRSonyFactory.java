package com.zoser.app.powermote;

import java.util.ArrayList;
import java.util.List;

public class IRSonyFactory
{
    public static final int TYPE_12_BITS = 1;
    public static final int TYPE_15_BITS = 2;
    public static final int TYPE_20_BITS = 3;

    public static final int HDR_MARK = 2400;
    public static final int HDR_SPACE = 600;

    public static final int ONE_MARK  = 1200;
    public static final int ZERO_MARK = 600;
    public static final int SPACE     = 600;

    public static final int REPEAT_TIME = 45000;

    public static IRMessage create(int mode, int command, int addr, int repeats)
    {
        List<Integer> tempMessage = new ArrayList<>();
        List<Integer> outMessage = new ArrayList<>();

        tempMessage.add(HDR_MARK);
        tempMessage.add(HDR_SPACE);

        List<Integer> cmdPulses = decodeInt(command,7);
        List<Integer> addrPulses = null;

        switch(mode)
        {
            case TYPE_12_BITS:
                addrPulses = decodeInt(addr,5);
                break;
            case TYPE_15_BITS:
                addrPulses = decodeInt(addr,8);
                break;
            case TYPE_20_BITS:
                addrPulses = decodeInt(addr,13);
                break;
            default:
                break;
        }

        tempMessage.addAll(cmdPulses);
        tempMessage.addAll(addrPulses);

        int messageTime=0;
        for(int a=0;a<tempMessage.size()-1;++a)
        {
            messageTime += tempMessage.get(a).intValue();
        }

        if(repeats > 1)
        {
            for(int i = 0; i < repeats; i++)
            {
                for(int a = 0; a < tempMessage.size() - 1; ++a)
                {
                    outMessage.add(tempMessage.get(a).intValue());
                }

                outMessage.add(REPEAT_TIME - messageTime);
            }
        }
        else
        {
            for(int a = 0; a < tempMessage.size(); ++a)
            {
                outMessage.add(tempMessage.get(a).intValue());
            }
        }

        int [] finalCode = new int[outMessage.size()];

        for(int a=0;a<outMessage.size();++a)
        {
            finalCode[a] = outMessage.get(a).intValue();
        }

        return new IRMessage(IRMessage.FREQ_40_KHZ,finalCode);
    }


    private static List<Integer> decodeInt(int num, int bits)
    {
        List<Integer> values = new ArrayList<>();
        for (int i = bits - 1; i >= 0; i--)
        {
            values.add(((num & (1 << i)) == 0)?ZERO_MARK:ONE_MARK);
            values.add(SPACE);
        }
        return values;
    }
}
