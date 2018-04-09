package cn.jzvd.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.faury.android.library.videoplayer.MultichannelVideoPlayer;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;

import static cn.jzvd.JZVideoPlayer.FULLSCREEN_ORIENTATION;

public class ActivityFullScreen extends AppCompatActivity {

    MultichannelVideoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        JZVideoPlayer.hideSupportActionBar(this);
        JZUtils.setRequestedOrientation(this, FULLSCREEN_ORIENTATION);
        player = findViewById(R.id.activity_full_screen_mcvp);
        player.setUp();
        player.startButton.performClick();
    }
}
