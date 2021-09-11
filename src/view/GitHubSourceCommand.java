package view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JFrame;
import model.ICellularAutomataModel;

public class GitHubSourceCommand extends ACellularAutomataGUICommand {

  GitHubSourceCommand(ICellularAutomataModel mdl, CellularAutomataGUIView frame) {
    super(mdl, frame);
  }

  @Override
  public void execute() {
    try {
      // TODO: change link to new GH page
      Desktop.getDesktop().browse(new URL(CellularAutomataGUIView.GITHUB_URL).toURI());
    } catch (URISyntaxException | IOException e) {
      frame.errorPopup("Could not open up the github URL. Congrats on breaking the "
          + "program. https://github.com/Sharwin24/Image-Manipulation-and-Enhancement.git is"
          + " the actual link. Contact us there about this issue", "Bad GitHub URL");
    }
  }
}
