package club.hnxxzyjsxy.redsky;


import android.os.Vibrator;
import android.view.Display;

import java.net.Socket;
import java.util.Random;

public class environmentObject {

    static int code=0;
    static int bomb_sleep;
    static Boolean stop = false;
    static int display_w, display_h;
    static double lawn = 0.9;
    static int page = 0;
    static Boolean isBack = false;
    static Boolean state = false;
    static Boolean isRun = true;
    static Boolean iSinit = false;
    static int playerNumber = 1;
    static int[][] playerLocation = new int[10][3];
    static int load = 0;
    static int player_x, player_y, player_blood = 999,player_speed;
    static int playerx;
    static Socket socket = null;
    static long GreenHatSleep = 5;
    static long LuckyBoxSleep = 4;
    static long GrenadeSleep = 7;
    static long BaseballSleep = 8;
    static long BombSleep = 10;
    static long KnifeSleep = 10;
    static long maxMemory, totalMemory, freeMemory;
    static Random random1 = new Random();
    static Random RandomKnife = new Random();
    static Random RandomCloud = new Random();
    static Random RandomGreenHat = new Random();
    static long startime, endtime;
    static int WorldWidth, WorldHeight;
    static int direction = 1;
    static int ScreenPosX;
    static  Vibrator v;

}
