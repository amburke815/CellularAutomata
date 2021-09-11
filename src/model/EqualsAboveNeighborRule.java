package model;

import auxiliary.Utils;

/**
 * If the cell above is ON, set the given cell to ON, otherwise the cell in question is set to OFF.
 */
public class EqualsAboveNeighborRule implements ICellularAutomataRule {

  private final ICellularAutomataModel mdl;

  public EqualsAboveNeighborRule(ICellularAutomataModel mdl) {
    this.mdl = Utils.checkNotNull(mdl, "Cannot create an EqualsAboveNeighborRule with a "
        + "null model");
  }

  @Override
  public boolean test(ICell toTest) {
    return mdl.above(toTest.row(), toTest.col()).state().isOn();
  }
}
