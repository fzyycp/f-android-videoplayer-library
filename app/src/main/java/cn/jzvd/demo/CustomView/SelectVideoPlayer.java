package cn.jzvd.demo.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedHashMap;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayerStandard;
import cn.jzvd.demo.R;

/**
 * Created by Fanyc on 2018/3/15.
 */

public class SelectVideoPlayer extends JZVideoPlayerStandard {
    LinearLayout layoutRight;
//    TextView videoYuanhua, videoLiuchang, videoGaoqing;
    Button controlOriginAliyunBtn,controlOrigin61Btn,controlOriginBtn;
    String url1, url2, url3;

    public SelectVideoPlayer(Context context) {
        super(context);
    }

    public SelectVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        layoutRight = findViewById(R.id.layout_right);
//        videoYuanhua = findViewById(R.id.video_yuanhua);
//        videoLiuchang = findViewById(R.id.video_liuchang);
//        videoGaoqing = findViewById(R.id.video_gaoqing);
        controlOriginAliyunBtn = findViewById(R.id.control_origin_aliyun_btn);
        controlOrigin61Btn = findViewById(R.id.control_origin_61_btn);
        controlOriginBtn = findViewById(R.id.control_origin_btn);
    }

    public void setUp(String url1, String url2, String url3) {
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
        LinkedHashMap map = new LinkedHashMap();
        map.put("高清", url1);
        map.put("标清", url2);
        map.put("普清", url3);
        Object[] dataSourceObjects = new Object[2];
        dataSourceObjects[0] = map;
        dataSourceObjects[1] = false;//looping
        setUp(dataSourceObjects, 0, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "140个汉字");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.control_origin_btn:
                if ("visibility".equals(controlOriginBtn.getTag())){
                    controlOriginAliyunBtn.setVisibility(GONE);
                    controlOrigin61Btn.setVisibility(GONE);
                    controlOriginBtn.setTag("");
                } else {
                    controlOriginAliyunBtn.setVisibility(VISIBLE);
                    controlOrigin61Btn.setVisibility(VISIBLE);
                    controlOriginBtn.setTag("visibility");
                }
                break;
            case R.id.control_quality_tv:
                break;
        }
    }

    //    @Override
//    public void onClick(View v) {
//        super.onClick(v);
//        switch (v.getId()){
//            case R.id.video_yuanhua:
//                long position = this.getCurrentPositionWhenPlaying();
//                JZMediaManager.pause();
//                JZMediaManager.setCurrentDataSource(this.url2);
//                break;
//            case R.id.video_liuchang:
//                JZMediaManager.setCurrentDataSource(this.url3);
//                break;
//            case R.id.video_gaoqing:
//                JZMediaManager.setCurrentDataSource(this.url1);
//                break;
//        }
//    }

    @Override
    public int getLayoutId() {
        return R.layout.select_layout_standard;
    }
}
