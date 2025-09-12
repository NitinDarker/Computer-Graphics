public class Bresenhem {
    public static void plotLine(int x0, int y0, int x1, int y1) {
        int x = x0, y = y0;
        System.out.println("Plotting...");
        System.out.println("(" + x + ", " + y + ")");
        int dx = x1 - x0;
        int dy = y1 - y0;
        int pk = 2*dy - dx;
        for (int i = 0; i < dx; i++) {
            if (pk < 0) {
                x = x + 1;
                pk = pk + 2*dy;
            } else {
                x = x + 1;
                y = y + 1;
                pk = pk + 2*dy - 2*dx;
            }
            System.out.println("(" + x + ", " + y + ")");
        }
    }
    
    public static void main(String[] args) {
        int x0 = 5;
        int y0 = 6;
        int x1 = 10;
        int y1 = 10;
        plotLine(x0, y0, x1, y1);
    }
}