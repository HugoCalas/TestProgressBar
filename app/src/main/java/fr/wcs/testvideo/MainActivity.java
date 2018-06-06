package fr.wcs.testvideo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private MediaController mediacontroller;
    private ProgressBar progressBar;
    private VideoView vv;
    private Button bPlay;
    private boolean isPlayed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL = "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";


        bPlay = findViewById(R.id.b_play);
        progressBar = findViewById(R.id.progressBar);
        vv = findViewById(R.id.videoView);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.faker;

        Uri uri = Uri.parse(path);

       // vv.setMediaController(mediacontroller);
       // vv.setVideoURI(uri);
        vv.setVideoPath(URL);
        new MyAsync().execute();

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlayed) {
                    vv.pause();
                    isPlayed = false;
                    bPlay.setText("Play");
                } else {
                    vv.start();
                    isPlayed = true;
                    bPlay.setText("Pause");
                }
            }
        });
    }


        private class MyAsync extends AsyncTask<Void, Integer, Void>
        {
            int duration = 0;
            int current = 0;
            @Override
            protected Void doInBackground(Void... params) {

                vv.start();
                vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer mp) {
                        duration = vv.getDuration();
                    }
                });


                do {
                    current = vv.getCurrentPosition();
                  //  System.out.println("duration - " + duration + " current- " + current);
                    try {
                        publishProgress((int) (current * 100 / duration));
                        if(progressBar.getProgress() >= 100){
                            break;
                        }
                    } catch (Exception e) {
                    }
                } while (progressBar.getProgress() <= 100);

                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                System.out.println(values[0]);
                progressBar.setProgress(values[0]);
            }


    }
}
