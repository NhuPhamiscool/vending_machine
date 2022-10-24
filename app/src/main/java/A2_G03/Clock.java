package A2_G03;

public class Clock {
    int min = 0;
    int second = 0;
    int count = 0;

    public Clock(int min, int second) {
        this.min = min;
        this.second = second;
    }

    public int getSecond() {
        return this.second;
    }

    public int getMinute() {
        return this.min;
    }
    // public void tick() {
    //     if (count == 100) {
    //         if (second == 59) {
    //             min +=1;
    //             second = 0;
    //         } else {
    //             second += 1;
    //         }
    //         count = 0;
    //     }
    //     count += 1;
    // }

    // public String display () {
    //     String formatted = "";
    //     if (second < 10) {
    //         formatted = String.format("%02d", second);

    //     } else {
    //         formatted = String.valueOf(second);
    //     }

    //     return String.valueOf(min) + ":" + formatted;
    // }
}