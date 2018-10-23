package club.hnxxzyjsxy.redsky;


import android.content.res.AssetManager;
import android.os.Vibrator;

import java.net.Socket;
import java.util.Random;

public class environmentObject {


    static int fangxiang = 0;
    static int ZZS = 10086;
    static int code = 1;
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
    static int player_x, player_y, player_blood = 100, player_maxblood = 100, player_speed;
    static double player_blood_b = 0;

    static int playerx;
    static Socket socket = null;

    static long GreenHatSleep = 5;
    static long LuckyBoxSleep = 4;
    static long GrenadeSleep = 7;
    static long BaseballSleep = 8;
    static long BombSleep = 10;
    static long KnifeSleep = 10;
    static long emoguoshiSleep = 8;
    static long bombJsleep = 2;


    static long maxMemory, totalMemory, freeMemory;


    static Random random1;


    static long startime = 1, endtime = 1;
    static int WorldWidth, WorldHeight, WorldDisplayHeight;
    static int GameWorldX, GameWorldX2;


    static int direction = 1;
    static int ScreenPosX, ScreenPosY;
    static Vibrator v;
    static AssetManager assetMg;
    static int FPS = 0;
    static int PlayerState = 0;
    static int PlayerUpHeight;
    static long StartTime = 0;
    static int sensorX = 0, sensorY = 0, sensorZ = 0;
    static boolean JumpCheck = false;
    static long GameRunTime = 0, GameStartTime = 0;
    static int knifeDamage = 25, bombDamage = 30;
    static long i;
    static long activityStopTime = 0;
    static int randomDamage = 0;
    static int EXP = 0, maxEXP = 300, Level = 1;
    static float EXP_b;
    static int gameArea0, gameArea1;
    static char[] timeChar;
    static int zx;
    static int year;
    static int wpl;
    static int maxToolbar = 6;
    static boolean isReduceBlood = true;
    static long WDendtime;

    static String NO1name = null;
    static int NO1time = 0;


    static boolean updata = false;
    static String myID = "TEST";


}
