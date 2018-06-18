package fr.wcs.testvideo;

import android.appwidget.AppWidgetProviderInfo;
import android.os.AsyncTask;
import android.widget.SeekBar;
import android.widget.VideoView;

public class Async extends AsyncTask<Void, Integer, Void> {
    int duration = 0;
    int current = 0;
    SeekBar seekBar;
    VideoView videoView;

    public Async(SeekBar seekBar, VideoView videoView) {
        this.seekBar = seekBar;
        this.videoView = videoView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        videoView.start();
        duration = videoView.getDuration();
        do {
            current = videoView.getCurrentPosition();
            try {
                publishProgress((int) (current * 100 / duration));
                if(seekBar.getProgress() >= 100){

                    break;
                }
            } catch (Exception e) {
            }
        } while (seekBar.getProgress() <= 100);

        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        seekBar.setProgress(values[0]);
    }
}
