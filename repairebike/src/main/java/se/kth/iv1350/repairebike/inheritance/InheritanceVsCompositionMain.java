package se.kth.iv1350.repairebike.inheritance;

/**
 * Demonstrates the difference between adapting a class
 * using inheritance and adapting a class using composition.
 * Both classes add a nextBoundedInt method to java.util.Random.
 */
public class InheritanceVsCompositionMain {

    /**
     * Runs a demonstration of both adaptations.
     *
     * @param args The command line arguments, not used.
     */
    public static void main(String[] args) {
        System.out.println("=== Adaptation using Inheritance ===");
        RandomByInheritance byInheritance = new RandomByInheritance();
        System.out.println("Random int between 1 and 6: "
                + byInheritance.nextBoundedInt(1, 6));
        System.out.println("Random int between 10 and 20: "
                + byInheritance.nextBoundedInt(10, 20));
        System.out.println("Random int between 100 and 200: "
                + byInheritance.nextBoundedInt(100, 200));
        System.out.println("Inherited nextBoolean() still accessible: "
                + byInheritance.nextBoolean());

        System.out.println();

        System.out.println("=== Adaptation using Composition ===");
        RandomByComposition byComposition = new RandomByComposition();
        System.out.println("Random int between 1 and 6: "
                + byComposition.nextBoundedInt(1, 6));
        System.out.println("Random int between 10 and 20: "
                + byComposition.nextBoundedInt(10, 20));
        System.out.println("Random int between 100 and 200: "
                + byComposition.nextBoundedInt(100, 200));
        System.out.println("nextBoolean() is NOT accessible on this class.");
    }
}