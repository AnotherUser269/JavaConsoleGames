package game.components;

import game.configs.GameConfig;

import java.util.Deque;
import java.util.ArrayDeque;

public class Tower {
    // Represents a stack, where disks[i+1] located under disks[i]
    // For example, [1,2,3,4] is an initial state for 4 disks
    private final Deque<Disk> disks = new ArrayDeque<>();

    public void put(Disk newDisk) {
        disks.push(newDisk);
    }

    public boolean isFull() {
        return disks.size() >= GameConfig.DISK_AMOUNT;
    }

    public boolean isEmpty() {
        return disks.isEmpty();
    }

    public int[] getRadii() {
        int[] radii = new int[GameConfig.DISK_AMOUNT];
        int index = 0;
        int startIndex = GameConfig.DISK_AMOUNT-disks.size();

        for (Disk disk : disks) {
            radii[startIndex + index] = disk.getRadius();
            index++;
        }

        return radii;
    }

    public int peekHeadRadius() {
        // Returns top disk's radius
        if(disks.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        return disks.peek().getRadius();
    }

    public Disk pop() {
        return disks.pop();
    }
}
