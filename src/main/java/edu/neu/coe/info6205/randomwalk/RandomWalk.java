/*
 * Copyright (c) 2017. Phasmid Software
 */

/*
 * Maharshi Jinandra
 * NUID 001546653
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

	private int lampPostX = 0;
	private int lampPostY = 0;
    private int x = this.lampPostX; // initially the drunkard is @ lamp post
    private int y = this.lampPostY; // initially the drunkard is @ lamp post

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        for (int i = 0; i < m; i++) {
        	randomMove();
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
    	return Math.sqrt(Math.pow(this.x - this.lampPostX, 2) +
						Math.pow(this.y - this.lampPostY, 2));
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param n the number of steps for each experiment
     * @param m the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int n, int m) {
        double totalDistance = 0;
        for (int i = 0; i < m; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(n);
            totalDistance += walk.distance();
        }
        return Math.round((totalDistance / m) * 100.0) / 100.0;
    }

    public static void main(String[] args) {
    	int howManyM = 100;
    	
    	for (int i = 0; i < howManyM; i++) {
	        int n = 512; // number of steps {16, 32, 64, 128, 256, 512}
	        int m = 10; // number of experiment
	        double meanDistance = randomWalkMulti(n, m);
//	        System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
	        System.out.println(meanDistance);
            System.out.println(System.nanoTime());

    	}
    }
}
