import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<FY> list = new ArrayList<>();
        double[] target = {0, 0, 40, 80, 120, 160, 200, 240, 280, 320};

        list.add(new FY());
        for (int i = 1; i < 10; i++) {
            double len = sc.nextDouble();
            double ang = sc.nextDouble();
            list.add(new FY(i, len, ang, Util.distance(len, ang, 100, target[i])));
            //System.out.println("id " + list.get(i).getId() + " " + list.get(i).getLength() + " " + list.get(i).getAngle());
        }

        int cnt = 0; // 轮数
        while (cnt <= 100){

            double distAll = 0;
            for (int i = 1; i < 10; i++) {
                distAll += list.get(i).getDist();
            }
            System.out.println("cnt: " + cnt + "    dist: " + distAll);

            cnt++;
            Collections.sort(list, new Comparator<FY>(){
                @Override
                public int compare(FY o1, FY o2) {
                    if ((o1.getDist() - o2.getDist() > 0 ? 1 : o1.getDist() == o2.getDist() ? 0 : -1) != 0){
                        return (o1.getDist() - o2.getDist() > 0 ? 1 : o1.getDist() == o2.getDist() ? 0 : -1);
                    } else {
                        return (o1.getId() - o2.getId() > 0 ? 1 : -1);
                    }
                }
            });
//            for (int i = 0; i < 10; i++) {
//                System.out.println("id:" + list.get(i).getId() + " len:" + list.get(i).getLength() +
//                        " ang:" + list.get(i).getAngle() + " dist:" + list.get(i).getDist());
//            }
//            System.out.println("----------------------");

            for (int i = 3; i < 10; i++) {
                double[] lenAng = Util.calculate(list, i);

                list.get(i).setLength(list.get(i).getLength() + 100 - lenAng[0]);
                list.get(i).setAngle(list.get(i).getAngle() + target[list.get(i).getId()] - lenAng[1]);
                list.get(i).setDist(Util.distance(list.get(i).getLength(), list.get(i).getAngle(), 100, target[list.get(i).getId()]));
            }
//            for (int i = 0; i < 10; i++) {
//                System.out.println("id:" + list.get(i).getId() + " len:" + list.get(i).getLength() +
//                        " ang:" + list.get(i).getAngle() + " dist:" + list.get(i).getDist());
//            }

        }

    }
}
