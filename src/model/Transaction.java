package model;

/**
 * Represents an arcade game transaction in a log of transactions
 */
public class Transaction {

  private final String description; // description of transaction
  private final int amount; // amount of tokens involved


  public Transaction(int amount, String description) {
    this.amount = amount;
    this.description = description;
  }

  public int getAmount() {
    return this.amount;
  }

  public String getDescription() {
    return this.description;
  }


}
