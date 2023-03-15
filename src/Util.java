import java.util.ArrayList;

public class Util {

    static double distance(double l1, double a1, double l2, double a2){
        double x = Math.cos(a1 * Math.PI / 180) * l1 - Math.cos(a2 * Math.PI / 180) * l2;
        double y = Math.sin(a1 * Math.PI / 180) * l1 - Math.sin(a2 * Math.PI / 180) * l2;
        return Math.sqrt(x * x + y * y);
    }

    static double[] calculate(ArrayList<FY> list, int i){
        FY FY00 = list.get(0);
        FY FY01 = list.get(1);
        FY FY0X = list.get(2);
        FY A = list.get(i);

        return threeFY(FY00, FY01, FY0X, A);
    }

    private static double[] threeFY(FY FY00, FY FY01, FY FY0K, FY A){
        double Ax = A.getLength() * Math.cos(A.getAngle() * Math.PI / 180);
        double Ay = A.getLength() * Math.sin(A.getAngle() * Math.PI / 180);
//        System.out.println("Ax " + Ax);
//        System.out.println("Ay " + Ay);
        if (
                (FY0K.getId() == 2 || FY0K.getId() == 3) && (Ay <= 0 && Ay <= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 4 || FY0K.getId() == 5) && (Ay <= 0 && Ay >= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 6 || FY0K.getId() == 7) && (Ay >= 0 && Ay <= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 8 || FY0K.getId() == 9) && (Ay >= 0 && Ay >= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180))
        ){ // S1
            double alpha1 = inAng(FY00, FY01, A);
            double alpha2 = inAng(FY00, FY0K, A);
            double a = Math.sin(alpha2 * Math.PI / 180) * Math.cos(alpha1 * Math.PI / 180) -
                    Math.sin(alpha1 * Math.PI / 180) * Math.cos((alpha2 + 40*(FY0K.getId()-1)) * Math.PI / 180);
            double b = Math.sin(alpha1 * Math.PI / 180) * Math.sin((alpha2 + 40*(FY0K.getId()-1)) * Math.PI / 180) -
                    Math.sin(alpha1 * Math.PI / 180) * Math.sin(alpha2 * Math.PI / 180);
            double[] lenAng = new double[2];
            lenAng[0] = 100 * (a / Math.sqrt(a*a + b*b) + b / (Math.sqrt(a*a + b*b) * Math.tan(alpha1 * Math.PI / 180)));
            lenAng[1] = 360 - Math.asin(b / Math.sqrt(a*a + b*b)) * 180 / Math.PI;
            return lenAng;
        } else if (
                (FY0K.getId() == 2 || FY0K.getId() == 3) && (Ay >= 0 && Ay >= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 4 || FY0K.getId() == 5) && (Ay >= 0 && Ay <= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 6 || FY0K.getId() == 7) && (Ay <= 0 && Ay >= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 8 || FY0K.getId() == 9) && (Ay <= 0 && Ay <= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180))
        ){
            double alpha1 = inAng(FY00, FY01, A);
            double alpha2 = inAng(FY00, FY0K, A);
//            System.out.println("alpha1 " + alpha1);
//            System.out.println("alpha2 " + alpha2);
//            System.out.println("θ " + 40*(FY0K.getId()-1));
            double a = Math.sin(alpha1 * Math.PI / 180) * Math.cos(alpha2 * Math.PI / 180) -
                    Math.sin(alpha2 * Math.PI / 180) * Math.cos((alpha1 + 40*(FY0K.getId()-1)) * Math.PI / 180);
            double b = Math.sin(alpha2 * Math.PI / 180) * Math.sin((alpha1 + 40*(FY0K.getId()-1)) * Math.PI / 180) -
                    Math.sin(alpha1 * Math.PI / 180) * Math.sin(alpha2 * Math.PI / 180);
            double[] lenAng = new double[2];
            lenAng[0] = 100 * (a / Math.sqrt(a*a + b*b) + b / (Math.sqrt(a*a + b*b) * Math.tan(alpha2 * Math.PI / 180)));
            lenAng[1] = Math.asin(b / Math.sqrt(a*a + b*b)) * 180 / Math.PI + 40*(FY0K.getId()-1);
            return lenAng;
        } else if (
                (FY0K.getId() == 2 || FY0K.getId() == 3) && (Ay <= 0 && Ay >= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 4 || FY0K.getId() == 5) && (Ay <= 0 && Ay <= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 6 || FY0K.getId() == 7) && (Ay >= 0 && Ay >= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180)) ||
                (FY0K.getId() == 8 || FY0K.getId() == 9) && (Ay >= 0 && Ay <= Ax * Math.tan(FY0K.getAngle() * Math.PI / 180))
        ){
            double alpha1 = inAng(FY00, FY01, A);
            double alpha2 = inAng(FY00, FY0K, A);
            double a = Math.sin(alpha2 * Math.PI / 180) * Math.cos(alpha1 * Math.PI / 180) +
                    Math.sin(alpha1 * Math.PI / 180) * Math.cos((alpha2 - 40*(FY0K.getId()-1)) * Math.PI / 180);
            double b = Math.sin(alpha1 * Math.PI / 180) * Math.sin((alpha2 - 40*(FY0K.getId()-1)) * Math.PI / 180) -
                    Math.sin(alpha1 * Math.PI / 180) * Math.sin(alpha2 * Math.PI / 180);
            double[] lenAng = new double[2];
            lenAng[0] = 100 * (a / Math.sqrt(a*a + b*b) + b / (Math.sqrt(a*a + b*b) * Math.tan(alpha1 * Math.PI / 180)));
            lenAng[1] = 360 - Math.asin(b / Math.sqrt(a*a + b*b)) * 180 / Math.PI;
            return lenAng;
        } else {
            double alpha1 = inAng(FY00, FY01, A);
            double alpha2 = inAng(FY00, FY0K, A);
//            System.out.println("alpha1 " + alpha1);
//            System.out.println("alpha2 " + alpha2);
//            System.out.println("θ " + 40*(FY0K.getId()-1));
            double a = Math.sin(alpha2 * Math.PI / 180) * Math.cos(alpha1 * Math.PI / 180) +
                    Math.sin(alpha1 * Math.PI / 180) * Math.cos((alpha2 + 40*(FY0K.getId()-1)) * Math.PI / 180);
            double b = Math.sin(alpha1 * Math.PI / 180) * Math.sin((alpha2 + 40*(FY0K.getId()-1)) * Math.PI / 180) -
                    Math.sin(alpha1 * Math.PI / 180) * Math.sin(alpha2 * Math.PI / 180);
            double[] lenAng = new double[2];
            lenAng[0] = 100 * (a / Math.sqrt(a*a + b*b) + b / (Math.sqrt(a*a + b*b) * Math.tan(alpha1 * Math.PI / 180)));
            lenAng[1] = Math.asin(b / Math.sqrt(a*a + b*b)) * 180 / Math.PI;
            return lenAng;
        }
    }

    private static double inAng(FY f1, FY f2, FY A){
        double a = distance(f1.getLength(), f1.getAngle(), f2.getLength(), f2.getAngle());
        double b = distance(f1.getLength(), f1.getAngle(), A.getLength(), A.getAngle());
        double c = distance(f2.getLength(), f2.getAngle(), A.getLength(), A.getAngle());
        return Math.acos((b*b + c*c - a*a) / (2*b*c)) * 180 / Math.PI;
    }
}
