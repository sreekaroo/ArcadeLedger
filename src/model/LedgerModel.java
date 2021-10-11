package model;

/**
 * represents the operations required for an arcade ledger
 */
public interface LedgerModel {

  /**
   * purchase number of tokens considering the customer has the enough money
   */
  void purchase(int number, String description);

  /**
   * spends the specified number of tokens on specified game
   *
   * @throws IllegalArgumentException if number of tokens is greater than current balance of tokens
   */
  void spend(int tokens, String description);

  /**
   * Observer method to see the list of transactions so far
   *
   * @return the transactions so far
   */
  String getTransactions();

  /**
   * Observer method to see number of tokens
   *
   * @return the customer's current number of tokens
   */
  int getTokenBalance();


}
