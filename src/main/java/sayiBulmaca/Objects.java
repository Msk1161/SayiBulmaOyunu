package sayiBulmaca;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Objects {
    private int level;
    private double puan;


    public int getLevel() throws IOException {
        return level;
    }

    public void setLevel(int level) throws IOException {
        this.level = level;
    }

    public double getPuan() {
        return puan;
    }

    public void setPuan(double puan) {
        this.puan = puan;
    }
}
