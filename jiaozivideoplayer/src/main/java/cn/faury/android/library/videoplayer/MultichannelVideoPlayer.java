package cn.faury.android.library.videoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;
import cn.jzvd.R;

/**
 * 多路视频播放器：
 * 1，带分辨率选择器
 * 2，带线路选择器
 */

public class MultichannelVideoPlayer extends JZVideoPlayerStandard {
    String url1 = "http://v.wassk.cn/0a6738404b3a4f16a5c99ebe54cef9ce/b4837ae3f84a486f8ca70f3a8e1ef156-S00000001-200000.mp4",
            url2 = "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4",
            url3 = "http://jzvd.nathen.cn/b201be3093814908bf987320361c5a73/2f6d913ea25941ffa78cc53a59025383-5287d2089db37e62345123a1be272f8b.mp4";

    TextView controlOriginYunTv, controlOrigin61Tv, controlOriginTv;
    TextView controlQualityOdTv, controlQualitySdTv, controlQualityHdTv, controlQualityTv;

    public MultichannelVideoPlayer(Context context) {
        super(context);
    }

    public MultichannelVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUp() {
        setUp(this.url1, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "140个汉字1");
    }

    @Override
    public void init(Context context) {
        super.init(context);
        controlOriginYunTv = findViewById(R.id.control_origin_yun_tv);
        controlOrigin61Tv = findViewById(R.id.control_origin_61_tv);
        controlOriginTv = findViewById(R.id.control_origin_tv);
        controlOriginYunTv.setOnClickListener(this);
        controlOrigin61Tv.setOnClickListener(this);
        controlOriginTv.setOnClickListener(this);

        controlQualityOdTv = findViewById(R.id.control_quality_od_tv);
        controlQualitySdTv = findViewById(R.id.control_quality_sd_tv);
        controlQualityHdTv = findViewById(R.id.control_quality_hd_tv);
        controlQualityTv = findViewById(R.id.control_quality_tv);
        controlQualityOdTv.setOnClickListener(this);
        controlQualitySdTv.setOnClickListener(this);
        controlQualityHdTv.setOnClickListener(this);
        controlQualityTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId() == R.id.control_origin_yun_tv){
            JZVideoPlayerManager.completeAll();
            setUp(this.url2, JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN, "140个汉字1");
            startVideo();
        } else if (v.getId() == R.id.control_origin_61_tv){
            JZVideoPlayerManager.completeAll();
            setUp(this.url3, JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN, "140个汉字1");
            startVideo();
        } else if (v.getId() == R.id.control_origin_tv){
            if("visible".equals(v.getTag())){
                controlOriginYunTv.setVisibility(GONE);
                controlOrigin61Tv.setVisibility(GONE);
                v.setTag("");
            } else {
                controlOriginYunTv.setVisibility(VISIBLE);
                controlOrigin61Tv.setVisibility(VISIBLE);
                v.setTag("visible");
            }
        } else if(v.getId() == R.id.control_quality_od_tv){
            JZVideoPlayerManager.completeAll();
            setUp(this.url2, JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN, "140个汉字1");
            startVideo();
        } else if (v.getId() == R.id.control_quality_sd_tv){
            JZVideoPlayerManager.completeAll();
            setUp(this.url3, JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN, "140个汉字1");
            startVideo();
        } else if (v.getId() == R.id.control_quality_hd_tv){
            JZVideoPlayerManager.completeAll();
            setUp(this.url3, JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN, "140个汉字1");
            startVideo();
        } else if (v.getId() == R.id.control_quality_tv){
            if("visible".equals(v.getTag())){
                controlQualityOdTv.setVisibility(GONE);
                controlQualitySdTv.setVisibility(GONE);
                controlQualityHdTv.setVisibility(GONE);
                controlQualityTv.setVisibility(GONE);
                v.setTag("");
            } else {
                controlQualityOdTv.setVisibility(VISIBLE);
                controlQualitySdTv.setVisibility(VISIBLE);
                controlQualityHdTv.setVisibility(VISIBLE);
                controlQualityTv.setVisibility(VISIBLE);
                v.setTag("visible");
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.f_library_video_player_multichannel;
    }
}
