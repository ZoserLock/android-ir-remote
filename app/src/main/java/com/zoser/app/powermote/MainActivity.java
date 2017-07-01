package com.zoser.app.powermote;

import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private int _clickWaitTime = 400;

    private IRController _irController;

    private ImageButton _buttonPowerAll;

    private LinearLayout [] _button = new LinearLayout[12];

    private LinearLayout [] _rows = new LinearLayout[4];

    private long _lastClickTimestamp=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        IRMessages.initialize();

        setContentView(R.layout.main_activity);

        getSupportActionBar().hide();

        _irController = new IRController(getApplicationContext());

        _lastClickTimestamp = SystemClock.uptimeMillis();

        getRowReferences();

        _buttonPowerAll = (ImageButton) findViewById(R.id.id_Button_ALL);

        _button[0] =  createIRButton("HDMI HUB",R.drawable.icon_04,_rows[0]);
        _button[1] =  createIRButton("LG TV",R.drawable.icon_01,_rows[0]);
        _button[2] =  createIRButton("HOME THEATER",R.drawable.icon_03,_rows[0]);

        _button[3] =  createIRButton("HDMI 1",R.drawable.icon_09,_rows[1]);
        _button[4] =  createIRButton("HDMI 2",R.drawable.icon_09,_rows[1]);
        _button[5] =  createIRButton("HDMI 3",R.drawable.icon_09,_rows[1]);

        _button[6] =  createIRButton("VOLUME UP",R.drawable.icon_02,_rows[2]);
        _button[7] =  createIRButton("CHROMECAST",R.drawable.icon_06,_rows[2]);
        _button[8] =  createIRButton("VOLUME UP",R.drawable.icon_02,_rows[2]);

        _button[9] =  createIRButton("VOLUME DOWN",R.drawable.icon_08,_rows[3]);
        _button[10] =  createIRButton("SWITCH",R.drawable.icon_07,_rows[3]);
        _button[11] =  createIRButton("VOLUME DOWN",R.drawable.icon_08,_rows[3]);


        for(int a=0;a<12;a++)
        {
            _button[a].setOnClickListener(this);
        }

        _buttonPowerAll.setOnClickListener(this);

    }

    private void getRowReferences()
    {
        _rows[0] = (LinearLayout)findViewById(R.id.id_Layout_Row_01);
        _rows[1] = (LinearLayout)findViewById(R.id.id_Layout_Row_02);
        _rows[2] = (LinearLayout)findViewById(R.id.id_Layout_Row_03);
        _rows[3] = (LinearLayout)findViewById(R.id.id_Layout_Row_04);

    }


    private LinearLayout createIRButton(String title, int imageResource, LinearLayout row)
    {
        View child = getLayoutInflater().inflate(R.layout.widget_button, null);

        RelativeLayout mainLayout = (RelativeLayout)child.findViewById(R.id.layout_main);
        LinearLayout buttonLayout = (LinearLayout)child.findViewById(R.id.layout_button);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        mainLayout.setLayoutParams(lp);

        ImageView imageView = (ImageView)child.findViewById(R.id.image_main);
        TextView titleView  = (TextView)child.findViewById(R.id.text_title);

        imageView.setImageResource(imageResource);
        titleView.setText(title);

        row.addView(child);

        return buttonLayout;
    }

    public void onClick(View v)
    {
        long currentTimestamp = SystemClock.uptimeMillis();
        Log.d("Zoser","Clicked");
        if(currentTimestamp - _lastClickTimestamp < _clickWaitTime)
        {
            Log.d("Zoser","Click canceled");
            return;
        }

        if(v == _buttonPowerAll)
        {
            try
            {
                _irController.sendMessage(IRMessages.HDMI_SPLITTER_ON);
                Thread.sleep(40);
                _irController.sendMessage(IRMessages.HOME_LG_TV_ON);
                Thread.sleep(40);
                _irController.sendMessage(IRMessages.HOME_SONY_HT_ON);

            }catch(InterruptedException e) {}
        }

        // Row 1
        if(v == _button[0])
        {
            _irController.sendMessage(IRMessages.HDMI_SPLITTER_ON);
        }

        if(v == _button[1])
        {
            _irController.sendMessage(IRMessages.HOME_LG_TV_ON);
        }

        if(v == _button[2])
        {
            _irController.sendMessage(IRMessages.HOME_SONY_HT_ON);
        }
        // Row 2
        if(v == _button[3])
        {
            _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_1);
        }

        if(v == _button[4])
        {
            _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_2);
        }

        if(v == _button[5])
        {
            _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_3);
        }
        // Row 3
        if(v == _button[6])
        {
            _irController.sendMessage(IRMessages.HOME_LG_TV_VOLUME_UP);
        }

        if(v == _button[7])
        {
            _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_5);
        }

        if(v == _button[8])
        {
            _irController.sendMessage(IRMessages.HOME_SONY_HT_VOLUME_UP);
        }

        // Row 4
        if(v == _button[9])
        {
            _irController.sendMessage(IRMessages.HOME_LG_TV_VOLUME_DOWN);
        }

        if(v == _button[10])
        {
            _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_4);
        }

        if(v == _button[11])
        {
            _irController.sendMessage(IRMessages.HOME_SONY_HT_VOLUME_DOWN);
        }


        _lastClickTimestamp = SystemClock.uptimeMillis();

    }
}
