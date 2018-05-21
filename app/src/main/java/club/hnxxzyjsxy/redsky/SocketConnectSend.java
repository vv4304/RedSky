package club.hnxxzyjsxy.redsky;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class SocketConnectSend implements Runnable {


    @Override
    public void run() {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("10.42.0.1", 7777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            try {
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }


            StringBuilder sb = new StringBuilder();
            byte[] bytes = new byte[1024];
            int len;

            try {
                while ((len = inputStream.read(bytes)) != -1) {
                    Log.e("SS", "SS");
                    try {
                        sb.append(new String(bytes, 0, len, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("request", sb + "");


            try {
                JSONObject jsonObject1 = new JSONObject(String.valueOf(sb));
                Log.e("request", jsonObject1.toString());
                environmentObject.playerx = (int) (environmentObject.display_w * jsonObject1.getDouble("x"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                socket.shutdownInput();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Goods player0 = Draw.manimages;
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


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
