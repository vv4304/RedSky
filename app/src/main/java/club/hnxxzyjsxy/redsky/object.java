package club.hnxxzyjsxy.redsky;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class object {


    static boolean init = false;
    static Bitmap cache2;
    static int player_beaten_number = 0;
    static boolean state = false;
    static SurfaceHolder surfaceHolder;
    static Bitmap background;
    static Rect DrawRect, DrawRect2;
    static LinkedList<String> message = new LinkedList<>();
    static ArrayList<Goods> GameList = new ArrayList<>();

    // static List<Goods> bombImages = new LinkedList<>();
    //static List<Goods> grenades = new LinkedList<>();s
    //static TreeMap fell = new TreeMap();
    static List<Layer> Layer = new ArrayList<>();
    static List<Integer> rucksack = new LinkedList<>();
    static List<Goods> fell = new ArrayList<>();
    static List<LinkedList<Goods>> head = new ArrayList<>();
    static List<GoodsEntity.ground> grounds = new LinkedList<>();


    //static ArrayList<GoodsEntity.player> player = new ArrayList<>();
    static int MoveD = 0;
    static ArrayList<Integer> MoveDirections = new ArrayList<>();
    static int MoveCondition = 0;
    static int MoveDirection = 0;
    static long beforetime, sumulationtime = 0, fixedtimer = 20, starttime = 1, endtime = 16;
    static GoodsEntity.player myself;
    static Bitmap[] left = new Bitmap[4];
    static Bitmap[] reight = new Bitmap[4];
    static ArrayList<Integer> move = new ArrayList<>();
    static Bitmap[] button = new Bitmap[2];
    static Bitmap bomb[] = new Bitmap[2];
    static Bitmap cloud[] = new Bitmap[2];
    static Bitmap baseball, grenade, NULL, luckybox, knife, emoguoshi;

    static Bitmap greenhat, yellowhat, purplehat, bluehat, redhat;

    static Bitmap caodi, caodi2;


    static Bitmap[] number = new Bitmap[10];


    static Bitmap LV0left[] = new Bitmap[12];
    static Bitmap LV0reight[] = new Bitmap[12];
    static Bitmap Seed[] = new Bitmap[4];

    static Bitmap[] bombExplosion = new Bitmap[5];
    static boolean isOk = true;
    static Bitmap Blood;
    static Canvas bloodCanvas;
    static Timer GoodsUpdate, animation;
    static Thread mainThread;
    static final Object object = new Object();
    static Paint Bluepaint = new Paint();
    static Paint Whitepaint = new Paint();
    static Paint paint = new Paint();
    static Thread request, send;
    static long RunTime, zTime;
    static Canvas canvas0, canvas1;
    static Bitmap bitmap1, bitmap2;
    static Canvas CameraView, FullView;
    static Bitmap FullBitmap;


    static Paint DrakPaint = new Paint();
    static Bitmap meteor;
    static MediaPlayer mediaPlayer = new MediaPlayer();
    static Bitmap xy;
    static SoundPool soundPool;
    static int an, shiwang, pu, fuck1;
    static Bitmap Bedrock;
    static SensorManager sensorMgr;
    static Sensor sensor;


}
