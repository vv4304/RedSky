package club.hnxxzyjsxy.redsky;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class SocketConnectRead implements Runnable {

    @Override
    public void run() {

        InputStream inputStream = null;


        while (true) {
/*
            try {
                inputStream = environmentObject.socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("CONNECT", "get input");
            StringBuilder sb = new StringBuilder();
            byte[] bytes = new byte[1024];
            int len;

            try {
                while ((len = inputStream.read(bytes)) != -1) {
                    try {
                        sb.append(new String(bytes, 0, len, "utf-8"));
                        Log.e("CONNECT", sb.toString());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/

/*
            try {
                JSONObject jsonObject1 = new JSONObject(String.valueOf(sb));
                Log.e("request", jsonObject1.toString());
                environmentObject.playerx = (int) (environmentObject.display_w * jsonObject1.getDouble("x"));

            } catch (JSONException e) {
                e.printStackTrace();
            }*/




/*
            Goods player0 = Draw.oneself;
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("x", String.valueOf((float) player0.getX() / (float) environmentObject.display_w));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                int z = jsonObject.toString().getBytes("utf-8").length;
                String s = z + jsonObject.toString();
                outputStream.write(s.getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("out", "ok" + jsonObject.toString());


         */




            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}