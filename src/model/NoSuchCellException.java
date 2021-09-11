package model;

import auxiliary.Utils;

/**
 * Custom exception to alert that there is no such Cell at the given index
 */
public class NoSuchCellException extends Exception {
  public NoSuchCellException(String errorMsg) {
    super(Utils.checkNotNull(errorMsg, "Cannot create a NoSuchCellException with a null"
        + " error message"));
  }
}
