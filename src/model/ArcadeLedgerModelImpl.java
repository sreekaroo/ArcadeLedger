package model;

/**
 * Instance of an arcade game that tracks a user's spending and tokens at an arcade
 */
public class ArcadeLedgerModelImpl implements LedgerModel {

  private final double tokenPrice;
  private int tokens;
  private StringBuilder transactions;

  /**
   * Default constructor sets user's money balance to 100 bucks
   *
   * @param tokenPrice
   */
  public ArcadeLedgerModelImpl(double tokenPrice) {
    this.tokenPrice = tokenPrice;

    this.tokens = 0;
    this.transactions = new StringBuilder();
  }


  @Override
  public void purchase(int number, String description) {

    this.tokens += number;

    // adding to transaction list
    this.transactions.append("You bought ").append(number).append(" tokens for ")
        .append(this.tokenPrice).append("$ each ").append("with description ").append(description)
        .append("\n");
  }

  @Override
  public void spend(int tokens, String description) {

    if (this.tokens >= tokens) {
      this.tokens -= tokens;
      this.transactions.append("You spent ").append(tokens).append(" to play ")
          .append(description).append("\n");
    } else {
      throw new IllegalArgumentException("You lack enough tokens");
    }
  }

  @Override
  public String getTransactions() {

    return this.transactions.toString();
  }

  @Override
  public int getTokenBalance() {
    return this.tokens;
  }


}
