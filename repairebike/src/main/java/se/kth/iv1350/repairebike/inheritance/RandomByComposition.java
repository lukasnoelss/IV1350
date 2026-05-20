package se.kth.iv1350.repairebike.inheritance;

import java.util.Random;

/**
 * An adaptation of java.util.Random using composition.
 * Contains a Random instance to add a method for generating
 * a random integer within a specified inclusive range.
 */
public class RandomByComposition {
    private final Random random;

    /**
     * Creates a new RandomByComposition instance.
     */
    public RandomByComposition() {
        random = new Random();
    }

    /**
     * Returns a random integer between min and max, inclusive.
     * For example, nextBoundedInt(5, 10) returns a value from 5 to 10.
     *
     * @param min The minimum value, inclusive.
     * @param max The maximum value, inclusive.
     * @return A random integer between min and max inclusive.
     */
    public int nextBoundedInt(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}