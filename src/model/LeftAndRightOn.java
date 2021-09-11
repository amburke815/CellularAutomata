package model;

import auxiliary.Utils;

/**
 * For Cell c: ON(c) <--> ON(c.left) && ON(c.right)
 */
public class LeftAndRightOn implements ICellularAutomataRule {
  private final ICellularAutomataBoard board;

  public LeftAndRightOn(ICellularAutomataBoard board) {
    this.board = Utils.checkNotNull(board, "Cannot construct a LeftAndRightOn rule with a null "
        + "model");
  }

  @Override
  public boolean test(ICell toTest) {

    if (board.left(toTest.row(), toTest.col()) == null) {
      return board.right(toTest.row(), toTest.col()).state().isOn();
    }
    else if (board.right(toTest.row(), toTest.col()) == null) {
      return board.left(toTest.row(), toTest.col()).state().isOn();
    }
    else {
      return board.left(toTest.row(), toTest.col()).state().isOn()
          && board.right(toTest.row(), toTest.col()).state().isOn();
    }

  }

}
