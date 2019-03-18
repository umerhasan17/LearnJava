import java.io.File;
import java.io.FileNotFoundException;
import java.io.PipedInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * You must implement the <code>checkObjects</code>  method.
 */

public class ParallelCollisionDetection {

  public static final int NUM_THREADS = 3;

  public static void main(String[] args) throws Exception {
    String inputFile = args[0];

    // add and sort the 2D-objects according to the size in ascending order
    PriorityQueueInterface<Object2D> sortedPoints = new PriorityQueue<Object2D>();
    AABB region = readAndSortObjects(inputFile, sortedPoints);

    boolean collisionFree = checkObjects(sortedPoints, region);
    ;

    if (collisionFree) {
      System.out.println("Collision-free.");
    } else {
      System.out.println("Collision detected!");
    }
  }


  /**
   * <p> Implement this method for Question 4 </p>
   *
   * // collision detection:
   * // We create a quadTree.
   * // We try to add all the 2D-objects to the quadTree.
   * // At each step, we pick a 2D-object:
   * // 1. we get its safety region (i.e., a square centred at the current point and
   * // with width equals to twice of the point's size)
   * // 2. by querying the quadTree, we try to find if there is any existing 2D-object
   * // within the current point's safety region
   * //   a. if there is none, then we add the point to the quad tree
   * //   b. otherwise, a collision is detected and we halt and return false.
   * // 3. if we successfully add all the points to the quad-tree, then we are sure
   * // that there is no collision between the points, and we halt and return
   * // true.
   */
  private static boolean checkObjects(
      PriorityQueueInterface<Object2D> sortedPoints, AABB region) {

    Thread[] threads = new Thread[NUM_THREADS];
    Arrays.setAll(threads, i -> new Thread());
    Arrays.stream(threads).forEach(Thread::start);

    Arrays.stream(threads)
            .forEach(thread -> checkObjectsSequential(sortedPoints, region));

    boolean res = checkObjectsSequential(sortedPoints, region);

    stopThreads(threads);

    return res;

    /* Spawn three threads that check for collisions in parallel. 
     *
     * The behavior of your parallel implementation has to be functionally equivalent to the
     * sequential implementation in CollisionDetection. I.e., the value returned by
     * this method has to be *systematically* the same you obtained with the sequential
     * implementation in CollisionDetection
     *
     * You can modify the implementation of PriorityQueue, AABB and QuadTree, but
     * the new interfaces have to be compatible with the current skeleton not to
     * fail the tests (i.e., you can add methods or change the modifiers of existing
     * fields and methods only if your changes keep the compatibility with the skeleton
     * declarations).
     *
     * Feel free to add comments in the code to better explain your design choices,
     * if you think they are necessary.
     *
     * You can add a brief comment (no more than 500 words) at the end of the method
     * to explain if other synchronization choices where possible and why you preferred
     * this one
     */

    /*
     * Justify here your implementation choice versus other valid alternatives
     */
  }

  private static boolean checkObjectsSequential(PriorityQueueInterface<Object2D> sortedPoints, AABB region) {
    QuadTree qt = new QuadTree(region, 4);
    while (!sortedPoints.isEmpty()) {
      Object2D object2D = sortedPoints.peek();
      Point2D center = object2D.getCenter();
      AABB safetyRegion = new AABB(new Point2D(center.x - object2D.getSize(), center.y - object2D.getSize()), new Point2D(center.x + object2D.getSize(), center.y + object2D.getSize()));
      if (qt.queryRegion(safetyRegion).isEmpty()) {
        qt.add(object2D);
      } else {
        return false;
      }
      sortedPoints.remove();
    }

    return true;
  }


  private static void stopThreads(Thread[] threads) {
    Arrays.stream(threads).forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }


  private static class CheckerThread extends Thread {
    private PriorityQueueInterface<Object2D> points;
    private QuadTree qt;
    private ReentrantReadWriteLock lock;
  }

  /**
   * Reads 2D-Objects from a given input file and sort them in ascending order
   * with respect to their size using a PrioriyQueue
   */
  private static AABB readAndSortObjects(String inputFile,
      PriorityQueueInterface<Object2D> sortedPoints)
      throws FileNotFoundException, Exception, PQException {
    Scanner in = new Scanner(new File(inputFile));
    double minX, maxX, minY, maxY;
    minX = minY = Double.MAX_VALUE;
    maxX = maxY = Double.MIN_VALUE;
    while (in.hasNext()) {
      String[] line = in.nextLine().trim().split(",");
      if (line.length < 3) {
        in.close();
        throw new Exception(
            "Each point should have x-y coordinates and a size.");
      } else {
        double x = Double.parseDouble(line[0]);
        double y = Double.parseDouble(line[1]);
        double w = Double.parseDouble(line[2]);
        sortedPoints.add(new Object2D(x, y, w));
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
      }
    }
    in.close();
    return new AABB(new Point2D(minX, minY), new Point2D(maxX, maxY));
  }
}
