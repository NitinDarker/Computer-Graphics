public class LiangBarsky {

    static double xmin = 100, ymin = 100;
    static double xmax = 300, ymax = 250;

    static boolean clipTest(double p, double q, double[] t) {
        if (p == 0 && q < 0)
            return false;

        double r = q / p;

        if (p < 0) {
            if (r > t[1])
                return false;
            else if (r > t[0])
                t[0] = r;
        } else if (p > 0) {
            if (r < t[0])
                return false;
            else if (r < t[1])
                t[1] = r;
        }
        return true;
    }

    static void liangBarsky(double x1, double y1, double x2, double y2) {

        double dx = x2 - x1;
        double dy = y2 - y1;

        double[] t = { 0.0, 1.0 }; // t[0] = tmin, t[1] = tmax

        if (clipTest(-dx, x1 - xmin, t) &&
                clipTest(dx, xmax - x1, t) &&
                clipTest(-dy, y1 - ymin, t) &&
                clipTest(dy, ymax - y1, t)) {
            // Clipped line endpoints
            double nx1 = x1 + t[0] * dx;
            double ny1 = y1 + t[0] * dy;
            double nx2 = x1 + t[1] * dx;
            double ny2 = y1 + t[1] * dy;

            System.out.println("Clipped Line:");
            System.out.println("(" + nx1 + ", " + ny1 + ") to (" + nx2 + ", " + ny2 + ")");
        } else {
            System.out.println("Line is completely outside.");
        }
    }

    public static void main(String[] args) {
        liangBarsky(50, 50, 350, 300);
    }
}