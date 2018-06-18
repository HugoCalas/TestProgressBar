package fr.wcs.testvideo;

import android.app.ActionBar;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MediaController mediacontroller;
    private VideoView vv;
    private Button bPlay;
    private Button bTag;
    private boolean isPlayed = false;
    private boolean firstPlay = true;
    private TextView time;
    private int tagTime;
    private SeekBar seek2;
    private int mDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.faker;

        String URL2 = "/assets_tutorials/media/Shaun-the-Sheep-The-Movie-Official-Trailer.mp4";

        bTag = findViewById(R.id.b_tag);
        bPlay = findViewById(R.id.b_play);
        vv = findViewById(R.id.videoView);
        time = findViewById(R.id.tv_tag);
        seek2 = findViewById(R.id.seekBar2);


        String URL = "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";
        vv.setVideoPath(URL);
        mDuration = vv.getDuration();

        Uri uri = Uri.parse(path);


        // vv.setMediaController(mediacontroller);
        // vv.setVideoURI(uri);
        final Async async = new Async(seek2, vv);

        bTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagTime = vv.getCurrentPosition();
                time.setText(String.valueOf(tagTime));

            }
        });


        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progressB = seek2.getProgress();
                vv.seekTo(progressB * 100 / mDuration);
            }
        });

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlayed) {
                    vv.pause();
                    isPlayed = false;
                    bPlay.setText("Play");
                } else if (firstPlay) {
                    // async.execute();
                    async.execute();
                    firstPlay = false;
                    isPlayed = true;
                    bPlay.setText("Pause");
                } else {
                    vv.start();
                    isPlayed = true;
                    bPlay.setText("Pause");
                }
            }
        });
    }
}


