package com.zoser.app.powermote;

import java.util.ArrayList;
import java.util.List;

public class IRNecFactory
{
    public static final int HDR_MARK  = 9000;

    public static final int HDR_SPACE = 4500;

    public static final int ONE_MARK  = 1687;
    public static final int ZERO_MARK = 562;
    public static final int SPACE     = 562;

    public static final int REPEAT_TIME = 110000;
    public static final int REPEAT_SPACE  = 2250;

    public static IRMessage create(int command, int addr, int repeats)
    {
        List<Integer> message = new ArrayList<>();

        message.add(HDR_MARK);
        message.add(HDR_SPACE);

        List<Integer> header1 = decodeInt(addr,8);
        List<Integer> header2 = decodeInt(~addr,8);

        List<Integer> data1 = decodeInt(command,8);
        List<Integer> data2 = decodeInt(~command,8);

        message.addAll(header1);
        message.addAll(header2);
        message.addAll(data1);
        message.addAll(data2);
        message.add(SPACE);

        int messageTime=0;
        for(int a=0;a<message.size();++a)
        {
            messageTime += message.get(a).intValue();
        }

        message.add(REPEAT_TIME - messageTime);

        for(int a=0;a<repeats;a++)
        {
            message.add(HDR_MARK);
            message.add(REPEAT_SPACE);
            message.add(SPACE);
            message.add(REPEAT_TIME - (HDR_MARK + REPEAT_SPACE +SPACE));
        }


        int [] finalCode = new int[message.size()];

        for(int a=0;a<message.size();++a)
        {
            finalCode[a] = message.get(a).intValue();
        }

        return new IRMessage(IRMessage.FREQ_38_KHZ,finalCode);
    }

    public static IRMessage createRepeat()
    {
        List<Integer> message = new ArrayList<>();

        message.add(HDR_MARK);
        message.add(REPEAT_SPACE);
        message.add(SPACE);
        message.add(REPEAT_TIME - (HDR_MARK + REPEAT_SPACE +SPACE));

        int [] finalCode = new int[message.size()];

        for(int a=0;a<message.size();++a)
        {
            finalCode[a] = message.get(a).intValue();
        }

        return new IRMessage(IRMessage.FREQ_38_KHZ,finalCode);
    }

    private static List<Integer> decodeInt(int num, int bits)
    {
        List<Integer> values = new ArrayList<>();
        for (int i = bits - 1; i >= 0; i--)
        {
            values.add(SPACE);
            values.add(((num & (1 << i)) == 0)?ZERO_MARK:ONE_MARK);
        }
        return values;
    }
}
