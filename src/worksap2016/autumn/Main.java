package worksap2016.autumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dewayne on 2016/10/13.
 */

public class Main{
    static class Room {
        boolean isCaled;
        int s;
        List<Integer> doors = new ArrayList<>();
        Room(int s) {
            this.s = s;
        }
    }
    static List<Room> rooms = new ArrayList<>();
    static int n, m;
    static int search(int cnt, int maxnow, int start) {
        if(cnt == m)
            return maxnow;
        int max = maxnow;
        for(int i = start; i < n; i ++) {
            List<Integer> rec = new ArrayList<>();
            List<Integer> doors = rooms.get(i).doors;
            int tempmax = maxnow;
            if(!rooms.get(i).isCaled) {
                tempmax += rooms.get(i).s;
                rooms.get(i).isCaled = true;
                rec.add(i);
            }
            for(int j = 0; j < doors.size(); j ++)
                if(!rooms.get(doors.get(j)).isCaled) {
                    rooms.get(doors.get(j)).isCaled = true;
                    rec.add(doors.get(j));
                    tempmax += rooms.get(doors.get(j)).s;
                }
            int temp = search(cnt + 1, tempmax, start + 1);
            max = Math.max(max, temp);
            for(int j = 0; j < rec.size(); j ++)
                rooms.get(rec.get(j)).isCaled = false;
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            n = scan.nextInt();
            m = scan.nextInt();
            rooms.clear();
            for(int i = 0; i < n; i ++)
                rooms.add(new Room(scan.nextInt()));
            for(int i = 0; i < n - 1; i ++) {
                int no1 = scan.nextInt() - 1, no2 = scan.nextInt() - 1;
                rooms.get(no1).doors.add(no2);
                rooms.get(no2).doors.add(no1);
            }
            System.out.println(search(0, 0, 0));
        }
        scan.close();
    }
}