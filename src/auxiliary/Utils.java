package auxiliary;

public class Utils {

  public static <X> X checkNotNull(X toCheck, String msg) {
    if (toCheck == null) {
      throw new IllegalArgumentException(msg);
    }
    return toCheck;
  }

  public static int checkIntBetween(int toCheck, int lowerBoundIncl, int upperBoundIncl) {
    if (toCheck < lowerBoundIncl || toCheck > upperBoundIncl) {
      throw new IllegalArgumentException(toCheck + " out of range for [" + lowerBoundIncl + "," +
          upperBoundIncl + "]");
    }
    return toCheck;
  }

}
