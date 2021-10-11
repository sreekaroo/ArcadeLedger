package view;

import java.awt.BorderLayout;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.LedgerModel;
import model.Transaction;

/**
 * Implementation for a view for a basic arcade ledger view
 */
public class SwingLedgerUI extends JFrame {

  private LedgerModel model;

  JLabel transactionLog;
  JScrollPane mainScrollPane;
  JLabel balance;


  public SwingLedgerUI(LedgerModel model) {
    super();

    // storing the model passed in
    this.model = model;

    // setting main panel
    JPanel mainPanel;
    this.setTitle("Arcade Ledger");
    setSize(350, 500);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout(500, 500));

    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // Transaction Log
    transactionLog = new JLabel("Transactions");
    JScrollPane transactionScroller = new JScrollPane(transactionLog,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    transactionLog.setVerticalAlignment(JLabel.TOP);
    JPanel transactions = new JPanel();
    transactions.add(transactionLog);
    mainPanel.add(transactionLog, BorderLayout.SOUTH);

    // spend and purchase buttons
    JPanel buttonPanel = new JPanel();
    JButton purchaseTokensButton = new JButton("Purchase Tokens");
    purchaseTokensButton.setActionCommand("purchase");
    buttonPanel.add(purchaseTokensButton);
    mainPanel.add(buttonPanel);

    purchaseTokensButton
        .addActionListener(evt -> this.purchase());

    JButton spendTokens = new JButton("Spend Tokens");
    purchaseTokensButton.setActionCommand("spend");
    buttonPanel.add(spendTokens);

    spendTokens
        .addActionListener(evt -> this.spend());

    // token balance
    balance = new JLabel();
    balance.setText(Integer.toString(model.getTokenBalance()) + " tokens");
    buttonPanel.add(balance);

    this.pack();

    this.setVisible(true);

  }

  /**
   * Handles the button input for spending tokens.
   */
  private void spend() {

    String input = this.getInput("How many tokens would you like to spend, provide the game");

    try {
      Transaction newTransaction = this.parseTransactionInput(input);
      String description = newTransaction.getDescription();
      model.spend(newTransaction.getAmount(), description);
      this.updateTransactions();
      this.updateBalance();
    } catch (IllegalArgumentException e) {
      this.renderAlert(e.getMessage());
    } catch (InputMismatchException e) {
      this.renderAlert("Invalid format of tokens and description. Try again");
    }

  }


  /**
   * Handles the button input for spending tokens.
   */
  private void purchase() {

    try {
      String input = this
          .getInput("How many tokens would you like to purchase, provide description");
      Transaction newTransaction = this.parseTransactionInput(input);

      this.renderAlert(Integer.toString(newTransaction.getAmount()));

      model.purchase(newTransaction.getAmount(), newTransaction.getDescription());
      this.updateTransactions();
      this.updateBalance();

    } catch (InputMismatchException e) {
      this.renderAlert("Invalid format of tokens and description. Try again");
    }

  }

  /**
   * Updates the current log of transactions
   */
  public void updateTransactions() {

    String transactions = model.getTransactions();

//    this.transactionLog.setText(transactions);

    this.transactionLog
        .setText("<html>" + model.getTransactions().replaceAll("<", "&lt;").replaceAll(
            ">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

  }

  public void updateBalance() {

    this.balance.setText(Integer.toString(model.getTokenBalance()) + " tokens");
  }

  public String getInput(String prompt) {
    return JOptionPane.showInputDialog(prompt);
  }


  /**
   * Parses input into a Transaction object
   *
   * @param input a number and description seperated by space
   * @return a transaction object representing the input
   */
  private Transaction parseTransactionInput(String input) {

    Scanner scan = new Scanner(input);

    int num = scan.nextInt();
    StringBuilder description = new StringBuilder();

    while (scan.hasNext()) {

      description.append(scan.next()).append(" ");
    }

    return new Transaction(num, description.toString());

  }


  /**
   * Renders a message to the screen
   *
   * @param message the message
   */
  public void renderAlert(String message) {

    JOptionPane.showMessageDialog(SwingLedgerUI.this, message, "Message",
        JOptionPane.PLAIN_MESSAGE);
  }


}
