package edu.neu.coe.info6205.union_find;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class UF_HWQUPC_Client {

    /**
     * @param m maximum
     * @return a reandom integer
     */
    private static int generateRandom(int m) {
        // return (int) (Math.random()*m);
        // generating the same number again and again
        return ThreadLocalRandom.current().nextInt(0, m);
    }

    /**
     *
     * @param n number of sites
     * @param pathCompression : requred or not (true/false)
     * @return count it took to connect the
     */
    private static int runUnionFind(int n, boolean pathCompression) {
        UF h = new UF_HWQUPC(n, pathCompression);

        int[] sites = new int[n];
        for (int i=0; i<n; i++) {
            sites[i] = i;
        }

        Set<Integer> sitesSet = new HashSet<Integer>();

        int c = 0;
        int oc = 0;
        while ((sitesSet.size() + 1 != sites.length) && (c != n-1)) {
            int i = generateRandom(n);
            int j = generateRandom(n);

            if (i == j) {
                continue;
            }

            System.out.println("Generated: " + i + ", " + j);

            if (! h.isConnected(i, j)) {
                System.out.println("Connecting");
                h.connect(i, j);
                c++;
            } else System.out.println("Already connected!");

            if (i > j) sitesSet.add(j);
            else sitesSet.add(i);

            oc++;

        }
        System.out.println("Unions Performed: " + c);
        System.out.println("Total Iterations: " + oc);

        return oc;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of sites:");
        int n = scanner.nextInt();
        scanner.close();

        for (int i=0; i<1; i++) {
            int c = runUnionFind(n, true);
        }
    }
}

