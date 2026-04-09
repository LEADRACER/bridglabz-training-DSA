public class code5 {
    static class Pump {
        int p, d;
        Pump(int petrol, int distance) {
            p = petrol;
            d = distance;
        }
    }

    public static int find(Pump[] pumps) {
        int n = pumps.length, start = 0, surp = 0, def = 0;
        for (int i = 0; i < n; i++) {
            surp += (pumps[i].p - pumps[i].d);
            if (surp < 0) {
                def += surp;
                surp = 0;
                start = i + 1;
            }
        }
        return (surp + def >= 0) ? start : -1;
    }

    public static void main(String[] args) {
        Pump[] p = { new Pump(4, 6), new Pump(6, 5), new Pump(7, 3), new Pump(4, 5) };
        System.out.println(find(p));
    }
}
