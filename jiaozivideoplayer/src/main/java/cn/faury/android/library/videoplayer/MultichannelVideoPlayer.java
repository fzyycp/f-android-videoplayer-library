package cn.faury.android.library.videoplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;
import cn.jzvd.R;

/**
 * 多路视频播放器：
 * 1，带分辨率选择器
 * 2，带线路选择器
 */

public class MultichannelVideoPlayer extends JZVideoPlayerStandard {

    private VideoInfo videoInfo;
    private VideoInfo.Unit currentUnit;

    TextView controlOriginTv, controlOriginYunTv, controlOriginOfficialTv;
    TextView controlQualityTv, controlQualityFdTv, controlQualityLdTv, controlQualitySdTv,
            controlQualityHdTv, controlQualityOdTv, controlQualityHd2kTv, controlQualityHd4kTv;

    public MultichannelVideoPlayer(Context context) {
        super(context);
    }

    public MultichannelVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUp(@NonNull VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
        if (videoInfo.getOrigins() != null && videoInfo.getOrigins().size() > 0) {
            if (videoInfo.getOrigins().get("m3u8") != null
                    && videoInfo.getOrigins().get("m3u8").getUnits() != null
                    && videoInfo.getOrigins().get("m3u8").getUnits().size() > 0) {
                selectOriginYun();
            } else if (videoInfo.getOrigins().get("mp4") != null
                    && videoInfo.getOrigins().get("mp4").getUnits() != null
                    && videoInfo.getOrigins().get("mp4").getUnits().size() > 0) {
                selectOriginOfficial();
            }
        }
    }

    @Override
    public void init(Context context) {
        super.init(context);
        // 线路
        controlOriginTv = findViewById(R.id.control_origin_tv);
        controlOriginYunTv = findViewById(R.id.control_origin_yun_tv);
        controlOriginOfficialTv = findViewById(R.id.control_origin_official_tv);
        controlOriginTv.setOnClickListener(this);
        controlOriginYunTv.setOnClickListener(this);
        controlOriginOfficialTv.setOnClickListener(this);

        // 清晰度
        controlQualityTv = findViewById(R.id.control_quality_tv);
        controlQualityFdTv = findViewById(R.id.control_quality_fd_tv);
        controlQualityLdTv = findViewById(R.id.control_quality_ld_tv);
        controlQualitySdTv = findViewById(R.id.control_quality_sd_tv);
        controlQualityHdTv = findViewById(R.id.control_quality_hd_tv);
        controlQualityOdTv = findViewById(R.id.control_quality_od_tv);
        controlQualityHd2kTv = findViewById(R.id.control_quality_hd2k_tv);
        controlQualityHd4kTv = findViewById(R.id.control_quality_hd4k_tv);
        controlQualityTv.setOnClickListener(this);
        controlQualityFdTv.setOnClickListener(this);
        controlQualityLdTv.setOnClickListener(this);
        controlQualitySdTv.setOnClickListener(this);
        controlQualityHdTv.setOnClickListener(this);
        controlQualityOdTv.setOnClickListener(this);
        controlQualityHd2kTv.setOnClickListener(this);
        controlQualityHd4kTv.setOnClickListener(this);
    }

    private void clearQualityText() {
        controlQualityFdTv.setText("");
        controlQualityLdTv.setText("");
        controlQualitySdTv.setText("");
        controlQualityHdTv.setText("");
        controlQualityOdTv.setText("");
        controlQualityHd2kTv.setText("");
        controlQualityHd4kTv.setText("");
    }

    // 初始化清晰度
    private void initQuality(VideoInfo.Origin origin) {
        if (origin != null && origin.getUnits() != null && origin.getUnits().size() > 0) {
            List<VideoInfo.Unit> units = origin.getUnits();
            this.currentUnit = units.get(0);
            for (VideoInfo.Unit unit : units) {
                switch (unit.getType()) {
                    case FD:
                        controlQualityFdTv.setText(DefinitionType.FD.getDesc());
                        controlQualityFdTv.setTag(unit);
                        break;
                    case HD:
                        controlQualityHdTv.setText(DefinitionType.HD.getDesc());
                        controlQualityHdTv.setTag(unit);
                        break;
                    case LD:
                        controlQualityLdTv.setText(DefinitionType.LD.getDesc());
                        controlQualityLdTv.setTag(unit);
                        break;
                    case OD:
                        controlQualityOdTv.setText(DefinitionType.OD.getDesc());
                        controlQualityOdTv.setTag(unit);
                        break;
                    case SD:
                        controlQualitySdTv.setText(DefinitionType.SD.getDesc());
                        controlQualitySdTv.setTag(unit);
                        break;
                    case HD2K:
                        controlQualityHd2kTv.setText(DefinitionType.HD2K.getDesc());
                        controlQualityHd2kTv.setTag(unit);
                        break;
                    case HD4K:
                        controlQualityHd4kTv.setText(DefinitionType.HD4K.getDesc());
                        controlQualityHd4kTv.setTag(unit);
                        break;
                }
            }
        }
    }

    private void changeOrigin() {
        if (currentUnit != null) {
            JZVideoPlayerManager.completeAll();
            setUp(currentUnit.getUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN, videoInfo.getTitle());
            startVideo();
        } else {
            Toast.makeText(getContext(), "视频信息不存在", Toast.LENGTH_LONG).show();
        }
    }

    // 选择官方线路
    private void selectOriginOfficial() {
        this.clearQualityText();
        this.currentUnit = null;
        VideoInfo.Origin mp4 = videoInfo.getOrigins().get("mp4");
        initQuality(mp4);
        changeOrigin();
    }

    // 选择云端线路
    private void selectOriginYun() {
        this.clearQualityText();
        VideoInfo.Origin m3u8 = videoInfo.getOrigins().get("m3u8");
        initQuality(m3u8);
        changeOrigin();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.control_origin_yun_tv) {
            selectOriginYun();
        } else if (v.getId() == R.id.control_origin_official_tv) {
            selectOriginOfficial();
        } else if (v.getId() == R.id.control_origin_tv) {
            if ("visible".equals(v.getTag())) {
                controlOriginYunTv.setVisibility(GONE);
                controlOriginOfficialTv.setVisibility(GONE);
                v.setTag("");
            } else {
                controlOriginYunTv.setVisibility(VISIBLE);
                controlOriginOfficialTv.setVisibility(VISIBLE);
                v.setTag("visible");
            }
        } else if (v.getId() == R.id.control_quality_fd_tv
                || v.getId() == R.id.control_quality_ld_tv
                || v.getId() == R.id.control_quality_sd_tv
                || v.getId() == R.id.control_quality_hd_tv
                || v.getId() == R.id.control_quality_od_tv
                || v.getId() == R.id.control_quality_hd2k_tv
                || v.getId() == R.id.control_quality_hd4k_tv
                ) {
            this.currentUnit = (VideoInfo.Unit) v.getTag();
            changeOrigin();
        } else if (v.getId() == R.id.control_quality_tv) {
            if ("visible".equals(v.getTag())) {
                controlQualityFdTv.setVisibility(GONE);
                controlQualityLdTv.setVisibility(GONE);
                controlQualitySdTv.setVisibility(GONE);
                controlQualityHdTv.setVisibility(GONE);
                controlQualityOdTv.setVisibility(GONE);
                controlQualityHd2kTv.setVisibility(GONE);
                controlQualityHd4kTv.setVisibility(GONE);
                v.setTag("");
            } else {
                controlQualityFdTv.setVisibility(VISIBLE);
                controlQualityLdTv.setVisibility(VISIBLE);
                controlQualitySdTv.setVisibility(VISIBLE);
                controlQualityHdTv.setVisibility(VISIBLE);
                controlQualityOdTv.setVisibility(VISIBLE);
                controlQualityHd2kTv.setVisibility(VISIBLE);
                controlQualityHd4kTv.setVisibility(VISIBLE);
                v.setTag("visible");
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.f_library_video_player_multichannel;
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
        if (videoInfo != null) {
            if (videoInfo.getOrigins() != null && videoInfo.getOrigins().size() > 0) {
                controlOriginTv.setVisibility(VISIBLE);
                controlQualityTv.setVisibility(VISIBLE);

                // 线路设置
                VideoInfo.Origin mp4 = videoInfo.getOrigins().get("mp4");
                VideoInfo.Origin m3u8 = videoInfo.getOrigins().get("m3u8");
                if (mp4 == null) {// 没有官方线路
                    controlOriginOfficialTv.setText("");
                }
                if (m3u8 == null) {// 没有云线路
                    controlOriginYunTv.setText("");
                }
            }
        }
    }

    /**
     * 清晰度类型
     */
    public enum DefinitionType {
        FD("fd", "流畅"), LD("ld", "标清"), SD("sd", "高清"), HD("hd", "超清"), OD("od", "原画"), HD2K("2k", "2K"), HD4K("4k", "4K");
        private String code;
        private String desc;

        DefinitionType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 视频信息
     */
    public static class VideoInfo implements Serializable {

        private String videoId;
        private String title;
        private String coverImg;
        private String duration;
        private String size;
        private Map<String, Origin> origins;

        public VideoInfo(@NonNull Map<String, String> data) {
            videoId = data.get("videoId");
            title = data.get("title");
            coverImg = data.get("coverImg");
            duration = data.get("duration");
            size = data.get("size");
            origins = new HashMap<>(2);
            Object sourceObj = data.get("source");
            if (sourceObj instanceof Map) {
                Map source = (Map) sourceObj;
                if (source.get("mp4") instanceof List) {
                    origins.put("mp4", new Origin("mp4", (List<Map>) source.get("mp4")));
                }
                if (source.get("m3u8") instanceof List) {
                    origins.put("m3u8", new Origin("m3u8", (List<Map>) source.get("m3u8")));
                }
            }
        }

        public String getVideoId() {
            return videoId;
        }

        public String getTitle() {
            return title;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public String getDuration() {
            return duration;
        }

        public String getSize() {
            return size;
        }

        public Map<String, Origin> getOrigins() {
            return origins;
        }

        /**
         * 线路
         */
        public class Origin {
            private String name;
            private List<Unit> units;

            public Origin(@NonNull String name, @NonNull List<Map> datas) {
                this.name = name;
                this.units = new ArrayList<>();
                if (datas != null && datas.size() > 0) {
                    for (Map data : datas) {
                        units.add(new Unit(data));
                    }
                }
            }

            public String getName() {
                return name;
            }

            public List<Unit> getUnits() {
                return units;
            }
        }

        /**
         * 播放信息
         */
        public class Unit {
            private String url;
            private DefinitionType type;
            private String begin;
            private String end;

            public Unit(@NonNull Map<String, String> data) {
                this.url = data.get("url");
                this.begin = data.get("begin");
                this.end = data.get("end");
                this.type = DefinitionType.valueOf(data.get("definition"));
            }

            public Unit(String url, DefinitionType type, String begin, String end) {
                this.url = url;
                this.type = type;
                this.begin = begin;
                this.end = end;
            }

            public String getUrl() {
                return url;
            }

            public DefinitionType getType() {
                return type;
            }

            public String getBegin() {
                return begin;
            }

            public String getEnd() {
                return end;
            }
        }

    }
}
