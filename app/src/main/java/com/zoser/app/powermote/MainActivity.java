package com.zoser.app.powermote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private IRController _irController;

    private ImageButton _buttonPowerAll;

    private RelativeLayout [] _button = new RelativeLayout[12];

    private LinearLayout [] _rows = new LinearLayout[4];

    private IRButtonData [] _irButtons = new IRButtonData[12];

    private void getRowReferences()
    {
        _rows[0] = (LinearLayout)findViewById(R.id.id_Layout_Row_01);
        _rows[1] = (LinearLayout)findViewById(R.id.id_Layout_Row_02);
        _rows[2] = (LinearLayout)findViewById(R.id.id_Layout_Row_03);
        _rows[3] = (LinearLayout)findViewById(R.id.id_Layout_Row_04);

    }


    private RelativeLayout createIRButton(String title, int imageResource, LinearLayout row)
    {
        View child = getLayoutInflater().inflate(R.layout.widget_button, null);

        RelativeLayout mainLayout = (RelativeLayout)child.findViewById(R.id.layout_main);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        mainLayout.setLayoutParams(lp);

        ImageView imageView = (ImageView)child.findViewById(R.id.image_main);
        TextView titleView  = (TextView)child.findViewById(R.id.text_title);

        imageView.setImageResource(imageResource);
        titleView.setText(title);

        row.addView(child);

        return mainLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        IRMessages.initialize();

        setContentView(R.layout.main_activity);

        getSupportActionBar().hide();

        _irController = new IRController(getApplicationContext());

        getRowReferences();

        _buttonPowerAll = (ImageButton) findViewById(R.id.id_Button_ALL);

        _button[0] =  createIRButton("HDMI",R.drawable.icon_01,_rows[0]);

      /*  _button[1] = (Button)findViewById(R.id.id_Button_1);
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
*/

        _buttonPowerAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    _irController.sendMessage(IRMessages.HDMI_SPLITTER_ON);
                    Thread.sleep(40);
                    _irController.sendMessage(IRMessages.HOME_LG_TV_ON);
                    Thread.sleep(40);
                    _irController.sendMessage(IRMessages.HOME_SONY_HT_ON);

                }catch(InterruptedException e)
                {

                }
            }
        });


      /*  _button[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.sendMessage(IRMessages.HDMI_SPLITTER_ON);
            }
        });

        _button[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.sendMessage(IRMessages.HOME_LG_TV_ON);
            }
        });

        _button[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.sendMessage(IRMessages.HOME_SONY_HT_ON);
            }
        });

        _button[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_1);
            }
        });

        _button[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_2);
            }
        });

        _button[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_3);
            }
        });

        _button[7].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_5);
            }
        });

        _button[10].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _irController.sendMessage(IRMessages.HDMI_SPLITTER_SET_4);
            }
        });*/


    }
}
