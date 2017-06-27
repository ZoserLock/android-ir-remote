package com.zoser.app.powermote;

import java.util.ArrayList;
import java.util.List;

public class IRMessages
{
    // Sony Speaker
    public static IRMessage HOME_SONY_SPEAKER_ON;

    // Sony HT
    public static IRMessage HOME_SONY_HT_ON;

    // HDMI SPLITTER
    public static IRMessage HDMI_SPLITTER_ON;
    public static IRMessage HDMI_SPLITTER_SET_1;
    public static IRMessage HDMI_SPLITTER_SET_2;
    public static IRMessage HDMI_SPLITTER_SET_3;
    public static IRMessage HDMI_SPLITTER_SET_4;
    public static IRMessage HDMI_SPLITTER_SET_5;

    // LG TV
    public static IRMessage HOME_LG_TV_ON;

    public static void Initialize()
    {
        // Sony Speaker
        HOME_SONY_SPEAKER_ON = IRSonyFactory.Create(IRSonyFactory.TYPE_12_BITS,84,1,6);

        // Sony HT
        HOME_SONY_HT_ON      = IRSonyFactory.Create(IRSonyFactory.TYPE_15_BITS,84,10,6);

        // LG TV
        HOME_LG_TV_ON       = IRNecFactory.Create(16,32,3);

        //HDMI SPLITTER
        HDMI_SPLITTER_ON    = IRNecFactory.Create(98,0,1);
        HDMI_SPLITTER_SET_1 = IRNecFactory.Create(2,0,0);
        HDMI_SPLITTER_SET_2 = IRNecFactory.Create(224,0,0);
        HDMI_SPLITTER_SET_3 = IRNecFactory.Create(168,0,0);
        HDMI_SPLITTER_SET_4 = IRNecFactory.Create(144,0,0);
        HDMI_SPLITTER_SET_5 = IRNecFactory.Create(152,0,0);
    }
}
