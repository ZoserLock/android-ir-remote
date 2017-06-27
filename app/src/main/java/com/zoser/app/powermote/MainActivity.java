package com.zoser.app.powermote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Console;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity
{
    private IRController _irController;

    private Button [] _button = new Button[12];

    private boolean flag =false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        IRMessages.Initialize();

        setContentView(R.layout.main_activity);

        _irController= new IRController(getApplicationContext());

        _button[0] = (Button)findViewById(R.id.id_Button_0);
        _button[1] = (Button)findViewById(R.id.id_Button_1);
        _button[2] = (Button)findViewById(R.id.id_Button_2);

        _button[3] = (Button)findViewById(R.id.id_Button_3);
        _button[4] = (Button)findViewById(R.id.id_Button_4);
        _button[5] = (Button)findViewById(R.id.id_Button_5);

        _button[6] = (Button)findViewById(R.id.id_Button_6);
        _button[7] = (Button)findViewById(R.id.id_Button_7);
        _button[8] = (Button)findViewById(R.id.id_Button_8);

        _button[9] = (Button)findViewById(R.id.id_Button_9);
        _button[10] = (Button)findViewById(R.id.id_Button_10);
        _button[11] = (Button)findViewById(R.id.id_Button_11);


        _button[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.SendMessage(IRMessages.HDMI_SPLITTER_ON);
            }
        });

        _button[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.SendMessage(IRMessages.HOME_LG_TV_ON);
            }
        });

        _button[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.SendMessage(IRMessages.HOME_SONY_HT_ON);
            }
        });

        _button[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.SendMessage(IRMessages.HDMI_SPLITTER_SET_1);
            }
        });

        _button[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.SendMessage(IRMessages.HDMI_SPLITTER_SET_2);
            }
        });

        _button[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.SendMessage(IRMessages.HDMI_SPLITTER_SET_3);
            }
        });

        _button[6].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.SendMessage(IRMessages.HDMI_SPLITTER_SET_4);
            }
        });

        _button[7].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.SendMessage(IRMessages.HDMI_SPLITTER_SET_5);
            }
        });

    }
}
