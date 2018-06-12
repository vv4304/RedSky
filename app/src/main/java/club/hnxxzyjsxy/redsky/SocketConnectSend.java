package club.hnxxzyjsxy.redsky;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnectSend implements Runnable {


    @Override
    public void run() {

        OutputStream outputStream = null;


        while (true) {
/*

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("x", Draw.oneself.getX());
                jsonObject.put("y", Draw.oneself.getY());
            } catch (JSONException e) {
                e.printStackTrace();
            }
*/


            try {
                outputStream = environmentObject.socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (Draw.message.size() >= 1) {
                try {
                    outputStream.write(Draw.message.get(0).getBytes("utf-8"));
                    Log.e("SEND", Draw.message.get(0));
                    Draw.message.remove(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    }
}