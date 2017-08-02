package com.zoser.app.powermote;

public class IRMessages
{
    // MISC
    public static IRMessage NEC_REPEAT;
    // Sony Speaker
    public static IRMessage HOME_SONY_SPEAKER_ON;

    // Sony HT
    public static IRMessage HOME_SONY_HT_ON;
    public static IRMessage HOME_SONY_HT_VOLUME_UP;
    public static IRMessage HOME_SONY_HT_VOLUME_DOWN;
    // HDMI SPLITTER
    public static IRMessage HDMI_SPLITTER_ON;
    public static IRMessage HDMI_SPLITTER_SET_1;
    public static IRMessage HDMI_SPLITTER_SET_2;
    public static IRMessage HDMI_SPLITTER_SET_3;
    public static IRMessage HDMI_SPLITTER_SET_4;
    public static IRMessage HDMI_SPLITTER_SET_5;

    // LG TV
    public static IRMessage HOME_LG_TV_ON;
    public static IRMessage HOME_LG_TV_VOLUME_UP;
    public static IRMessage HOME_LG_TV_VOLUME_DOWN;

    public static void initialize()
    {
        // MISC
        NEC_REPEAT = IRNecFactory.createRepeat();

        // Sony Speaker
        HOME_SONY_SPEAKER_ON = IRSonyFactory.create(IRSonyFactory.TYPE_12_BITS,84,1,3);

        // Sony HT
        HOME_SONY_HT_ON      = IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,84,10,3);
        HOME_SONY_HT_VOLUME_UP = IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,36,10,3);
        HOME_SONY_HT_VOLUME_DOWN = IRSonyFactory.create(IRSonyFactory.TYPE_15_BITS,100,10,3);

        // LG TV
        HOME_LG_TV_ON           = IRNecFactory.create(16,32,2);
        HOME_LG_TV_VOLUME_UP    = IRNecFactory.create(64,32,2);
        HOME_LG_TV_VOLUME_DOWN  = IRNecFactory.create(192,32,2);

        //HDMI SPLITTER
        HDMI_SPLITTER_ON    = IRNecFactory.create(98,0,0);
        HDMI_SPLITTER_SET_1 = IRNecFactory.create(2,0,0);
        HDMI_SPLITTER_SET_2 = IRNecFactory.create(224,0,0);
        HDMI_SPLITTER_SET_3 = IRNecFactory.create(168,0,0);
        HDMI_SPLITTER_SET_4 = IRNecFactory.create(144,0,0);
        HDMI_SPLITTER_SET_5 = IRNecFactory.create(152,0,0);
    }
}
