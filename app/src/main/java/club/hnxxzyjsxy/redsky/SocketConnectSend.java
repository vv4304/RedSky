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
                jsonObject.put("x", object.oneself.getX());
                jsonObject.put("y", object.oneself.getY());
            } catch (JSONException e) {
                e.printStackTrace();
            }
*/


            try {
                outputStream = environmentObject.socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (object.message.size() >= 1) {
                try {
                    outputStream.write(object.message.get(0).getBytes("utf-8"));
                    Log.e("SEND", object.message.get(0));
                    object.message.remove(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    }
}