package model;

/**
 * Created by Administrator on 2019/8/25.
 */
public class CurrentCount {
    int currentCount;

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        if (currentCount <= 1) {
            this.currentCount = 1;
        }else {
            this.currentCount = currentCount;
        }
    }
}
