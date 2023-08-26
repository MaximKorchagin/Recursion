package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = {21, 1, 20, 23};
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, new int[day]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {

        if (memory[day - 1] != 0) return memory[day - 1];
        if (day == 1) {
            memory[day - 1] = (startNumbers[3] * startNumbers[1]) % 10 + 1;
        } else if (day == 2) {
            memory[day - 1] = (chooseHobbyRecursive(startNumbers, day - 1, memory) * startNumbers[2]) % 10 + 1;
        } else if (day == 3) {
            memory[day - 1] = (chooseHobbyRecursive(startNumbers, day - 1, memory) * startNumbers[3]) % 10 + 1;
        } else {
            int prev = chooseHobbyRecursive(startNumbers, day - 1, memory);
            int prevprevprev = chooseHobbyRecursive(startNumbers, day - 3, memory);
            memory[day - 1] = (prev * prevprevprev) % 10 + 1;
        }
        return memory[day - 1];
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4; // ������� ���� � ������� �������� �� 4
            int prev = numbers.get(index - 1); // ���������� ��������
            int prePrePrev = numbers.get(index - 3); // ���-���-���������� ��������
            numbers.add((prev * prePrePrev) % 10 + 1);
        }

        return numbers.get(numbers.size() - 1);
    }
}