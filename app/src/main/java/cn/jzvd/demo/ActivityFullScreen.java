package cn.jzvd.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

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
        String url1 = "http://v.wassk.cn/0a6738404b3a4f16a5c99ebe54cef9ce/b4837ae3f84a486f8ca70f3a8e1ef156-S00000001-200000.mp4",
                url2 = "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4",
                url3 = "http://jzvd.nathen.cn/b201be3093814908bf987320361c5a73/2f6d913ea25941ffa78cc53a59025383-5287d2089db37e62345123a1be272f8b.mp4";
        Map<String, String> data = new HashMap<>();
        data.put("videoId","");
        data.put("title","测试视频");
        data.put("coverImg","");
        data.put("source","");
        MultichannelVideoPlayer.VideoInfo videoInfo = new MultichannelVideoPlayer.VideoInfo(data);
        player.setUp(videoInfo);
        player.startButton.performClick();
    }
}
