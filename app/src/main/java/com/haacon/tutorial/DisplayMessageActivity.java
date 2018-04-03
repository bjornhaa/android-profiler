package com.haacon.tutorial;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DisplayMessageActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.seekBar)
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView.setText(message);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText("verdi "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.adjust_volume_button)
    public void adjustVolume() {
        AudioManager audio = getSystemService(AudioManager.class);

        //AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int streamToAdjust = AudioManager.STREAM_RING;
        int currentVolume = audio.getStreamVolume(streamToAdjust);
        int maxVolume = audio.getStreamMaxVolume(streamToAdjust);
        float percent = 0.1f;
        int seventyVolume = (int) (maxVolume*percent);
        audio.setStreamVolume(streamToAdjust, seventyVolume, AudioManager.FLAG_SHOW_UI);

        ConnectivityManager connMgr = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

    }

}
