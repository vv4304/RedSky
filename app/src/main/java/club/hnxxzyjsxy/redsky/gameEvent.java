package club.hnxxzyjsxy.redsky;

import android.graphics.Rect;
import android.util.Log;

public class gameEvent {

    static void useItems(Integer id) {
        switch (id) {
            case ItemID.coin:
                if (environmentObject.random1.nextInt(2) == 0) {
                    PlayerAddBoold(-environmentObject.player_maxblood / 2);
                    object.info.add(new news("使用薛定谔的硬币减少了" + environmentObject.player_maxblood / 2, System.currentTimeMillis() + 100));
                } else {
                    PlayerAddBoold(environmentObject.player_maxblood / 2);
                    object.info.add(new news("使用薛定谔的硬币增加了" + environmentObject.player_maxblood / 2, System.currentTimeMillis() + 100));
                }
                break;
            case ItemID.drug:
                object.info.add(new news("使用血瓶增加了" + 100 / 3 + environmentObject.player_maxblood / 2, System.currentTimeMillis() + 100));
                PlayerAddBoold(100 / 3);
                break;
            case ItemID.wd:
                environmentObject.i = environmentObject.random1.nextInt(15000);
                object.info.add(new news("暂停时间" + environmentObject.i / 1000 + "S", System.currentTimeMillis() + 100));
                environmentObject.isReduceBlood = false;
                environmentObject.WDendtime = System.currentTimeMillis() + environmentObject.i;
                break;
            case ItemID.EXPdrug:
                environmentObject.i = environmentObject.random1.nextInt(100);
                object.info.add(new news("使用经验瓶增加了" + environmentObject.i, System.currentTimeMillis() + 100));
                environmentObject.EXP += environmentObject.i;
                break;
        }

    }

    static void PlayerAddBoold(int i) {

        if (environmentObject.player_blood + i >= environmentObject.player_maxblood) {
            environmentObject.player_blood = environmentObject.player_maxblood;
        } else {
            environmentObject.player_blood += i;
        }


    }

    static void RandomGetItems() {
        if (object.toolbar.size() < environmentObject.maxToolbar) {
            switch (environmentObject.random1.nextInt(10)) {
                case 0:
                    object.toolbar.add(ItemID.coin);
                    object.info.add(new news("获得薛定谔的硬币", System.currentTimeMillis() + 100));
                    break;
                case 1:
                    object.toolbar.add(ItemID.drug);
                    object.info.add(new news("获得血瓶", System.currentTimeMillis() + 100));
                    break;
                case 3:
                    object.toolbar.add(ItemID.EXPdrug);
                    object.info.add(new news("获得经验瓶", System.currentTimeMillis() + 100));
                    break;
                case 4:
                    object.toolbar.add(ItemID.wd);
                    object.info.add(new news("获得续命硬币", System.currentTimeMillis() + 100));
                    break;

                default:
                    object.info.add(new news("量子崩塌", System.currentTimeMillis() + 100));
                    break;
            }
        } else {
            object.info.add(new news("物品栏已满", System.currentTimeMillis() + 100));
        }
    }

    static void gameClear() {
        environmentObject.StartTime = 0;
        environmentObject.GameStartTime = 0;
        environmentObject.EXP = 0;
        environmentObject.maxEXP = 100;
        environmentObject.EXP_b = 0;
        object.Layer.clear();
        object.head.get(0).clear();
        object.scene.clear();
        object.fell.clear();
        object.toolbar.clear();
        environmentObject.player_maxblood = 100;
        environmentObject.player_blood = environmentObject.player_maxblood;
        Play.reduceTime = 0;
        Play.TempTime = 0;
        object.Layer.clear();
        object.MoveDirections.clear();
        environmentObject.Level = 1;

        object.DrawRect = new Rect((int) (environmentObject.WorldWidth / 2 - (environmentObject.WorldWidth * 0.2 / 3)), 0, (int) (environmentObject.WorldWidth / 2 - (environmentObject.WorldWidth * 0.2 / 3)) + environmentObject.display_w, environmentObject.display_h);
        object.myself.setX(environmentObject.WorldWidth / 2 - object.playerL[0].getWidth() / 2);
        object.myself.setY(environmentObject.WorldDisplayHeight - object.playerL[0].getHeight());
        environmentObject.NO1time = 0;

    }

}
