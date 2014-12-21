public class Problem85 {

    public static final int TARGET = 2000000;

    public static void main(String[] args){

        int minDifferenceFromTarget = 2000000;
        int minDifferenceH = 0;
        int minDifferenceW = 0;

        for (int h = 1; h < 100; h++){
            for (int w = h; w < 100; w++){
                int numberOfRectangles = getNumRectangles(h,w);
                int difference = Math.abs(numberOfRectangles - TARGET);

                if (difference < minDifferenceFromTarget){
                    minDifferenceFromTarget = difference;
                    minDifferenceH = h;
                    minDifferenceW = w;
                }
            }
        }
        System.out.println(minDifferenceH*minDifferenceW);
    }

    public static int getNumRectangles(int gridHeight, int gridWidth){
        int numberOfRectangles = 0;
        for (int h = 1; h <= gridHeight; h++){
            for (int w = 1; w <= gridWidth; w++){
                numberOfRectangles += (gridWidth - w + 1) * (gridHeight - h + 1);
            }
        }
        return numberOfRectangles;
    }
}

