package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void DFS(int startPos)
    {
        if (!markedStates.contains(startPos))
            markedStates.add(startPos);
        List<Integer> nextStates = relationsMap.get(startPos);
        if (nextStates == null || nextStates.size() == 0)
            deadStates.add(startPos);
        else
        {
            for (int i = 0; i < nextStates.size(); i++) {
                int nextState = nextStates.get(i);
                if (!markedStates.contains(nextState))
                    DFS(nextState);
            }
        }
    }

    public static HashMap<Integer, List<Integer>> relationsMap = new HashMap<>();
    public static List<Integer> markedStates = new ArrayList<Integer>();
    public static List<Integer> deadStates = new ArrayList<Integer>();
    public static List<Integer> unreachableStates = new ArrayList<Integer>();

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("file.txt"));
        String ASet = scanner.nextLine();
        String SSet = scanner.nextLine();
        String start = scanner.nextLine();
        Integer startPos = Integer.parseInt(start);
        String FSet = scanner.nextLine();

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] states = line.split(" a ");
            int v1 = Integer.parseInt(states[0]);
            int v2 = Integer.parseInt(states[1]);
            List<Integer> relStates = new ArrayList<>();
            if(relationsMap.containsKey(v1))
            {
                relStates = relationsMap.get(v1);
                relStates.add(v2);
                relationsMap.put(v1, relStates);
            }
            else
            {
                relStates.add(v2);
                relationsMap.put(v1, relStates);
            }
        }
        DFS(startPos);
        for (int verticle = 1; verticle <= Integer.parseInt(SSet); verticle++) {
            if (!markedStates.contains(verticle))
                unreachableStates.add(verticle);
        }
        System.out.println("Graph: ");
        System.out.println(relationsMap);
        System.out.println("Deadlock states is: ");
        System.out.println(deadStates);
        System.out.println("Unreachable states is: ");
        System.out.println(unreachableStates);


    }
}
