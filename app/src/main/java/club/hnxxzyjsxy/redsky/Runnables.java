package club.hnxxzyjsxy.redsky;

import android.content.res.AssetFileDescriptor;

import java.io.IOException;

public class Runnables {


    static class backgroundMusic implements Runnable {

        @Override
        public void run() {

/*            AssetFileDescriptor fileDescriptor = null;
           try {
                fileDescriptor = environmentObject.assetMg.openFd("TOONE.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert fileDescriptor != null;
                object.mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                object.mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }


            object.mediaPlayer.start();*/


        }
    }

    static class Gm implements Runnable {
        @Override
        public void run() {
            while (true) {

                if (environmentObject.isRun) {
                    if (environmentObject.page == 22) {
                        if (environmentObject.code == 1) {

                            if (environmentObject.GameStartTime == 0) {
                                environmentObject.GameStartTime = System.currentTimeMillis();
                            } else {
                                environmentObject.GameRunTime = System.currentTimeMillis() - environmentObject.GameStartTime;
                            }

                        }
                    }
                }
            }
        }
    }
}