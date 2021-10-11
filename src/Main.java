import model.ArcadeLedgerModelImpl;
import model.LedgerModel;
import view.SwingLedgerUI;

public class Main {

  // decide on token cost by inputting double cost into the arguments when running on CLI
  public static void main(String[] args) {

    // determining the cost of the tokens
    LedgerModel model;
    if (args.length < 1) {
      model = new ArcadeLedgerModelImpl(1.25);
    } else {
      String cmd = args[0];
      model = new ArcadeLedgerModelImpl(Double.parseDouble(cmd));
    }

    SwingLedgerUI view = new SwingLedgerUI(model);

  }


}
