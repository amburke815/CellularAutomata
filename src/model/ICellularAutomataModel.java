package model;

import auxiliary.IMatrix;
import java.util.List;

public interface ICellularAutomataModel {

  void iterate();

  ICellularAutomataBoard getCells();

  ECellState nextCellState(int row, int col);

  ICell getCell(int row, int col)
      throws NoSuchCellException;

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  /**
   * Returns all immediate neighbors of the given cell: (upper left, top, upper right,
   * left, right, lower left, bottom, lower right)
   */
  List<ICell> eightNeighbors(int row, int col);

  List<ICell> eightNeighbors(ICell centerCell);

  // All return null if there is no ICell at the specified location
  ICell right(int row, int col);

  ICell upperRight(int row, int col);

  ICell above(int row, int col);

  ICell upperLeft(int row, int col);

  ICell left(int row, int col);

  ICell lowerLeft(int row, int col);

  ICell below(int row, int col);

  ICell lowerRight(int row, int col);
}
