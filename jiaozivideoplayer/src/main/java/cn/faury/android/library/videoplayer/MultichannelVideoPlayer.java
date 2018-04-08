package cn.faury.android.library.videoplayer;

import android.content.Context;
import android.util.AttributeSet;

import cn.jzvd.JZVideoPlayerStandard;
import cn.jzvd.R;

/**
 * 多路视频播放器：
 * 1，带分辨率选择器
 * 2，带线路选择器
 */

public class MultichannelVideoPlayer extends JZVideoPlayerStandard {
    public MultichannelVideoPlayer(Context context) {
        super(context);
    }

    public MultichannelVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.f_library_video_player_multichannel;
    }
}
