public class FY {
    private int id;
    private double length; // 径长
    private double angle; // 角度（0 - 359.999）
    private double dist; // 与目标点的距离

    public FY(int id, double length, double angle, double dist) {
        this.id = id;
        this.length = length;
        this.angle = angle;
        this.dist = dist;
    }

    public FY() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getDist() {
        return dist;
    }


    public void setDist(double dist) {
        this.dist = dist;
    }
}
