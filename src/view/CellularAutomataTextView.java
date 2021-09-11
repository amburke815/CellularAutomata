package view;

import auxiliary.Utils;
import model.ICellularAutomataModel;
import model.NoSuchCellException;

public class CellularAutomataTextView implements ICellularAutomataView {

  private final ICellularAutomataModel mdl;

  public CellularAutomataTextView(ICellularAutomataModel mdl) {
    this.mdl = Utils.checkNotNull(mdl, "Can't construct a CellularAutomataTextView with a null"
        + " model");
  }

  @Override
  public String toString() {
    String rendered = "";

    for (int i = 0; i < mdl.getCells().cellMatrix().getWidth(); i++) {
      for (int j = 0; j < mdl.getCells().cellMatrix().getHeight(); j++) {
        try {
          rendered += mdl.getCell(i, j).state().toString();
        } catch (NoSuchCellException e) { // should never trigger, but if it does, print a "_"
          rendered += "_";
        }
      }
      rendered += "\n";
    }

    return rendered;
  }

  @Override
  public void renderBoard() {
    System.out.println(this.toString()); // TODO: generalize appendable, not just System.out
  }

}
