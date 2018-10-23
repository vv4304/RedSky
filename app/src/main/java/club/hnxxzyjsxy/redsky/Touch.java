package club.hnxxzyjsxy.redsky;

import android.util.Log;
import android.view.MotionEvent;

public class Touch {
    public static boolean Touch(MotionEvent event) {
        int x = (int) event.getX(event.getActionIndex());
        int y = (int) event.getY(event.getActionIndex());
        if (environmentObject.isBack) {
        } else {
            switch (environmentObject.page) {
                case 22:
                    if (environmentObject.GameRunTime > 1000) {

                        if (y < environmentObject.display_h / 3 * 2 && environmentObject.PlayerState == 0) {
                            environmentObject.PlayerState = 1;
                        }

                        if (y > environmentObject.WorldDisplayHeight) {

                            //Log.e("CCCCC", object.wupinglan.getWidth() + "");

                            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                                case MotionEvent.ACTION_DOWN:
                                    if (y >= environmentObject.WorldDisplayHeight && y <= environmentObject.WorldDisplayHeight + object.wupinglan.getHeight()) {

                                        if (x > environmentObject.display_w * 0.05 && x < environmentObject.wpl + environmentObject.display_w * 0.05) {
                                            if (object.toolbar.size() > 0) {
                                                gameEvent.useItems(object.toolbar.get(0));
                                                object.toolbar.remove(0);
                                            }
                                            Log.e("A", "C1");
                                        }
                                        if (x > environmentObject.wpl + environmentObject.display_w * 0.05 && x < environmentObject.wpl * 2 + environmentObject.display_w * 0.05) {
                                            if (object.toolbar.size() > 1) {
                                                gameEvent.useItems(object.toolbar.get(1));
                                                object.toolbar.remove(1);
                                            }
                                            Log.e("A", "C2");
                                        }
                                        if (x > environmentObject.wpl * 2 + environmentObject.display_w * 0.05 && x < environmentObject.wpl * 3 + environmentObject.display_w * 0.05) {
                                            if (object.toolbar.size() > 2) {
                                                gameEvent.useItems(object.toolbar.get(2));
                                                object.toolbar.remove(2);
                                            }
                                        }
                                        if (x > environmentObject.wpl * 3 + environmentObject.display_w * 0.05 && x < environmentObject.wpl * 4 + environmentObject.display_w * 0.05) {
                                            if (object.toolbar.size() > 3) {
                                                gameEvent.useItems(object.toolbar.get(3));
                                                object.toolbar.remove(3);
                                            }
                                        }
                                        if (x > environmentObject.wpl * 4 + environmentObject.display_w * 0.05 && x < environmentObject.wpl * 5 + environmentObject.display_w * 0.05) {
                                            if (object.toolbar.size() > 4) {
                                                gameEvent.useItems(object.toolbar.get(4));
                                                object.toolbar.remove(4);
                                            }
                                        }
                                        if (x > environmentObject.wpl * 5 + environmentObject.display_w * 0.05 && x < environmentObject.wpl * 6 + environmentObject.display_w * 0.05) {
                                            if (object.toolbar.size() > 5) {
                                                gameEvent.useItems(object.toolbar.get(5));
                                                object.toolbar.remove(5);
                                            }
                                        }
                                    }
                                    if (y > environmentObject.WorldDisplayHeight + object.wupinglan.getHeight()) {
                                        if (x < environmentObject.display_w / 2) {
                                            object.MoveDirections.add(0);
                                            environmentObject.fangxiang = 0;
                                        } else {
                                            object.MoveDirections.add(1);
                                            environmentObject.fangxiang = 1;
                                        }

                                    }
                                    break;
                                case MotionEvent.ACTION_POINTER_DOWN:
                                    if (x > environmentObject.display_w / 2) {
                                        object.MoveDirections.add(1);
                                        environmentObject.fangxiang = 0;
                                    } else {
                                        object.MoveDirections.add(0);
                                        environmentObject.fangxiang = 1;
                                    }
                                    if (y < environmentObject.display_h / 3 * 2 && environmentObject.PlayerState == 0) {
                                        if (environmentObject.JumpCheck) {
                                            environmentObject.PlayerState = 1;
                                            environmentObject.JumpCheck = false;
                                        }
                                    }
                                    break;
                                case MotionEvent.ACTION_UP:
                                    object.MoveDirections.clear();
                                    break;
                                case MotionEvent.ACTION_POINTER_UP:
                                    break;

                            }
                        }

                    }
                    break;
                case 0:
                    switch (event.getAction() & event.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            if (event.getY() <= environmentObject.display_h / 2) {
                                environmentObject.page = 22;
                            }
                            break;
                    }
                    break;
                case 1:
                    switch (event.getAction() & event.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            gameEvent.gameClear();
                            environmentObject.page = 22;
                            break;
                    }
                    break;


            }
        }
        return true;
    }
}