package dev.perfectbogus.arrays;

public class Reverse {

    public static int[] Solve(int[] data) {
        if (data == null) throw new IllegalArgumentException("data cannot be null");
        if (data.length == 0) return data;

        int iLow = 0;
        int iHigh = data.length - 1;
        int iStop = (data.length / 2);
        while (iLow < iStop) {
            int tmp = data[iLow];
            data[iLow] = data[iHigh];
            data[iHigh] = tmp;

            iLow++;
            iHigh--;
        }
        return data;
    }
}
