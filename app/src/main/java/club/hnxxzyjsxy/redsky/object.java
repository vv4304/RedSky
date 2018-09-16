package club.hnxxzyjsxy.redsky;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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

    static List<Layer> Layer = new ArrayList<>();
    static List<Integer> rucksack = new LinkedList<>();
    static List<Goods> fell = new ArrayList<>();
    //static TreeMap fell = new TreeMap();
    static List<LinkedList<Goods>> head = new ArrayList<>();


    //static ArrayList<GoodsEntity.player> player = new ArrayList<>();
    static int MoveD = 0;
    static ArrayList<Integer> MoveDirections = new ArrayList<>();
    static int MoveCondition = 0;
    static int MoveDirection = 0;
    static long beforetime, sumulationtime = 0, fixedtimer = 20, starttime, endtime = 0;
    static GoodsEntity.player myself;
    static Bitmap[] left = new Bitmap[4];
    static Bitmap[] reight = new Bitmap[4];
    static ArrayList<Integer> move = new ArrayList<>();
    static Bitmap[] button = new Bitmap[2];
    static Bitmap bomb[] = new Bitmap[2];
    static Bitmap cloud[] = new Bitmap[2];
    static Bitmap baseball, grenade, NULL, luckybox, knife;
    static Bitmap greenhat, yellowhat, purplehat, bluehat, redhat;


    static Bitmap[] bombExplosion = new Bitmap[5];
    static boolean isOk = true;
    static Bitmap Blood;
    static Canvas bloodCanvas;
    static Timer GoodsUpdate, animation;
    static Thread mainThread;
    static final Object object = new Object();
    static Paint Bloodpaint1 = new Paint();
    static Paint Bloodpaint2 = new Paint();
    static Paint paint = new Paint();
    static Thread request, send;
    static long RunTime, zTime;
    static Canvas canvas0, canvas1;
    static Bitmap bitmap1, bitmap2;
    static Canvas CameraView, FullView;
    static Bitmap FullBitmap;
    static Paint paint1;
    static Random random = new Random();
    static Random random1 = new Random();

    static Paint DrakPaint = new Paint();
    static Bitmap meteor;


}
