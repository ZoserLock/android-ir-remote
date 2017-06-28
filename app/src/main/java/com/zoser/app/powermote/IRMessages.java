package com.zoser.app.powermote;

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

    public static void initialize()
    {
        // Sony Speaker
        HOME_SONY_SPEAKER_ON = IRSonyFactory.create(IRSonyFactory.TYPE_12_BITS,84,1,6);

        // Sony HT
        HOME_SONY_HT_ON      = IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,84,10,6);

        // LG TV
        HOME_LG_TV_ON       = IRNecFactory.create(16,32,3);

        //HDMI SPLITTER
        HDMI_SPLITTER_ON    = IRNecFactory.create(98,0,1);
        HDMI_SPLITTER_SET_1 = IRNecFactory.create(2,0,0);
        HDMI_SPLITTER_SET_2 = IRNecFactory.create(224,0,0);
        HDMI_SPLITTER_SET_3 = IRNecFactory.create(168,0,0);
        HDMI_SPLITTER_SET_4 = IRNecFactory.create(144,0,0);
        HDMI_SPLITTER_SET_5 = IRNecFactory.create(152,0,0);
    }
}
