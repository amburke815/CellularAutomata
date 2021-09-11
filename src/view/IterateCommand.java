package view;

import model.ICellularAutomataModel;

public class IterateCommand extends ACellularAutomataGUICommand {


  IterateCommand(ICellularAutomataModel mdl, CellularAutomataGUIView frame) {
    super(mdl, frame);
  }

  @Override
  public void execute() {
    mdl.iterate();
    new CellularAutomataTextView(mdl).renderBoard();
    frame.renderBoard();
  }
}
