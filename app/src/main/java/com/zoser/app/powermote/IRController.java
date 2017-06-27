package com.zoser.app.powermote;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Vibrator;

public class IRController
{
    private ConsumerIrManager _irManager;

    private Vibrator _vibrator;

    private Context _context = null;

    private boolean _enabled = false;

    public IRController(Context context)
    {
        _context = context;
        _irManager = (ConsumerIrManager)context.getSystemService(Context.CONSUMER_IR_SERVICE);
        _vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if(_irManager != null)
        {
            if(_irManager.hasIrEmitter())
            {
                _enabled = true;
            }
        }
    }

    public boolean SendMessage(IRMessage message)
    {
        if(_enabled)
        {
            int frequency = message.GetFrequency();
            int [] codes = message.GetMessage();

            _vibrator.vibrate(50);

            _irManager.transmit(frequency, codes);
        }
        return false;
    }
}
