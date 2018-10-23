package club.hnxxzyjsxy.redsky;

import cn.bmob.v3.BmobObject;

public class rankings extends BmobObject {

    private String id;
    private int gameTime;
    private int level;


    public String getId() {
        return id;
    }

    public int getGameTime() {
        return gameTime;
    }

    public int getLevel() {
        return level;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
