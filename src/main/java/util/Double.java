package util;

public class Double {
    public static double rounding(double d, int i){

        double scale = Math.pow(10, i);
        return Math.round(d * scale) / scale;
    }

}
