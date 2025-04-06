package org.example.leecode.Lc20250331;

import java.util.*;

public class Router {
    private final List<int[]> memoryList;
    private final int memoryLimit;
    private final Set<String> cacheSet;
    private final Map<Integer, List<Integer>> timeMap;

    public Router(int memoryLimit) {
        memoryList = new ArrayList<>(memoryLimit);
        cacheSet = new HashSet<>(memoryLimit);
        timeMap = new HashMap<>();
        this.memoryLimit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String key = generateKey(source, destination, timestamp);
        if (cacheSet.contains(key)) {
            return false;
        }

        // 移除最旧数据包（若需要）
        if (memoryList.size() == memoryLimit) {
            forwardPacket();
        }

        // 添加新数据包
        int[] packet = {source, destination, timestamp};
        memoryList.add(packet);
        cacheSet.add(key);
        timeMap.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (memoryList.isEmpty()) {
            return new int[]{};
        }

        int[] packet = memoryList.remove(0);
        String key = generateKey(packet[0], packet[1], packet[2]);
        cacheSet.remove(key);

        // 只删除对应时间戳
        int dest = packet[1];
        int time = packet[2];
        List<Integer> times = timeMap.get(dest);
        if (times != null) {
            times.remove(Integer.valueOf(time));
            if (times.isEmpty()) timeMap.remove(dest);
        }
        return packet;
    }

    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> times = timeMap.get(destination);
        if (times == null || times.isEmpty()) return 0;

        // 二分优化查询
        int lower = findLowerBound(times, startTime);
        int upper = findUpperBound(times, endTime);
        return (lower <= upper) ? upper - lower + 1 : 0;
    }

    private int findLowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (list.get(mid) < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    private int findUpperBound(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (list.get(mid) > target) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }

    private String generateKey(int source, int destination, int timestamp) {
        return source + "_" + destination + "_" + timestamp;
    }


    public static void main(String[] args) {
//        Router router = new Router(3);
//        System.out.println(router.addPacket(1, 4, 90));
//        System.out.println(router.addPacket(2, 5, 90));
//        System.out.println(router.addPacket(1, 4, 90));
//        System.out.println(router.addPacket(3, 5, 95));
//        System.out.println(router.addPacket(4, 5, 105));
//        System.out.println(Arrays.toString(router.forwardPacket()));
//        System.out.println(router.addPacket(5, 2, 110));
//        System.out.println(router.getCount(5, 100, 110));

//        "Router","addPacket","forwardPacket","forwardPacket"
//        Router router = new Router(2);
//        System.out.println(router.addPacket(7, 4, 90));
//        System.out.println(Arrays.toString(router.forwardPacket()));
//        System.out.println(Arrays.toString(router.forwardPacket()));

        // "Router","addPacket","addPacket","addPacket","getCount","forwardPacket","getCount"
//        [[2],[1, 4, 1],[5, 4, 1],[1, 4, 1],[4, 1, 1],[],[4, 1, 1]]
//        [null,true,true,false,2,[1,4,1],1]
        Router router = new Router(2);
        System.out.println(router.addPacket(1, 4, 1));
        System.out.println(router.addPacket(5, 4, 1));
        System.out.println(router.addPacket(1, 4, 1));
        System.out.println(router.getCount(4, 1, 1));
        System.out.println(Arrays.toString(router.forwardPacket()));
        System.out.println(router.getCount(4, 1, 1));
    }
}

