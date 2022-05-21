package hu.progmatic;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> pits = new ArrayList<>();
        List<String> numberOfPits = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        try {
            BufferedReader br = new BufferedReader(new FileReader("melyseg.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                pits.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("1. feladat\n" +
                "A fájl adatainak száma: " + pits.size());
        System.out.println("2.feladat");
        System.out.println("Adjon meg egy távolságértéket!");
        int distance = sc.nextInt();
        System.out.println("Ezen a helyen a felszín " + pits.get(distance) + " méter mélyen van");
        System.out.println("3. feladat");
        System.out.println("Az érintetlen terület aránya: " + untouchedLand(pits) + "%");
        createGodrok(pits);
        try {
            BufferedReader br = new BufferedReader(new FileReader("godrok.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                numberOfPits.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(numberOfPits.size());
        System.out.println("5.feladat");
        System.out.println("A gödrök száma: "+ numberOfPits.size());
        System.out.println("6.feladat");
        int begin = distance;
        int end = distance;
        while(pits.get(begin-1) != 0){
            begin--;
        }
        while(pits.get(end) != 0){
            end++;
        }
        System.out.println("a)\nA gödör kezdete: "+(begin+1)+" méter, a gödör vége: "+end+" méter");
        boolean isConstantlyDeeping = true;
        for (int i = begin; i <end-1 ; i++) {
            if (pits.get(i) > pits.get(i + 1)) {
                isConstantlyDeeping = false;
                break;
            }
        }
        System.out.println("b)");
        if (isConstantlyDeeping){
            System.out.println("Folyamatosan mélyül");
        }else{
            System.out.println("Nem mélyül folyamatosan");
        }
        System.out.println("c)");
        int max = Integer.MIN_VALUE;
        for (int i = begin; i <=end ; i++) {
            if (pits.get(i) > max) {
                max = pits.get(i);
            }
        }
        System.out.println("A legnagyobb mélysége: " + max + " méter.");
        int volume = 0;
        for (int i = begin; i <=end ; i++) {
            volume += pits.get(i) * 10;
            }
        System.out.println("d)");
        System.out.println("A térfogata: " + volume + "m^3.");
        System.out.println("e)");
        int amountOfWater = 0;
        for (int i = begin; i <=end ; i++) {
            if (pits.get(i)>1){
                amountOfWater += 10*(pits.get(i)-1);
            }
        }
        System.out.println("A vízmennyiség " + amountOfWater + " m^3.");
    }

    public static double untouchedLand(List<Integer> pits) {
        double counter = 0;
        for (Integer pit : pits) {
            if (pit > 0) {
                counter++;
            }
        }
        double sum = pits.size() - counter;
        return Math.round((((sum / pits.size()) * 100)) * 100.0) / 100.0;
    }


    public static void createGodrok (List<Integer> pits){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("godrok.txt"));
            for (int i = 0; i < pits.size()-1; i++) {
                if (pits.get(i) == 0 && pits.get(i+1)==0){
                    continue;
                }else if (pits.get(i) == 0 && pits.get(i+1)!=0){
                    bw.write(Integer.toString(pits.get(i+1)));
                }else if (pits.get(i) != 0 && pits.get(i+1) == 0){
                    bw.write("\n");
                }else{
                    bw.write(Integer.toString(pits.get(i)));
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
