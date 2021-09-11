package view;

import auxiliary.Utils;
import javax.swing.JFrame;
import model.ICellularAutomataModel;

public abstract class ACellularAutomataGUICommand implements ICellularAutomataGUICommand {

  protected ICellularAutomataModel mdl;
  protected CellularAutomataGUIView frame;

  ACellularAutomataGUICommand(ICellularAutomataModel mdl, CellularAutomataGUIView frame) {
    this.mdl = Utils.checkNotNull(mdl, "Cannot create an ACellularAutomataGUICommand "
        + "with a null model");
    this.frame = Utils.checkNotNull(frame, "Cannot construct an ACellularAutomataGUICommand "
        + "with a null frame");
  }

}
