package com.tencent.yolov8ncnn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class FlatStatistic {

    public HashMap<Integer, Vector<Float>> add_room(RoomType room){
        List<Integer> ignores = null;
        HashMap<Integer, Vector<Float>> map = null;
        switch(room){
            case KITCHEN:
                ignores = kitchen_map;
                map = kitchen;
                break;
            case LIVING:
                ignores = living_map;
                map = living;
                break;
            case HALL:
                ignores = hall_map;
                map = hall;
                break;
            case SANITARY:
                ignores = sanitary_map;
                map = sanitary;
                break;
        }
        for (int i = 0; i <= 21; i++){
            if (!ignores.contains(i)) {
                if (!map.containsKey(i)) map.put(i, new Vector<Float>(){{add(0.0f);}});
                else map.get(i).add(0.0f);
            }
        }
        return map;
    };

    public static List<Integer> kitchen_map = Arrays.asList(0, 9, 13, 16);
    public static List<Integer> living_map = Arrays.asList(0, 3, 9, 13, 16);
    public static List<Integer> hall_map = Arrays.asList(0, 3, 8, 9, 10, 13, 16, 19, 22);
    public static List<Integer> sanitary_map = Arrays.asList(3, 8, 10, 16, 19, 22);

    public HashMap<Integer, Vector<Float>> kitchen = new HashMap<Integer, Vector<Float>>();
    public HashMap<Integer, Vector<Float>> living = new HashMap<Integer, Vector<Float>>();
    public HashMap<Integer, Vector<Float>> hall = new HashMap<Integer, Vector<Float>>();
    public HashMap<Integer, Vector<Float>> sanitary = new HashMap<Integer, Vector<Float>>();
}