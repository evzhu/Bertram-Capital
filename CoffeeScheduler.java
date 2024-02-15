import java.util.Scanner;

public class CoffeeScheduler {

  // represents an individual Coworker: holds their name, preferred drink, price
  // of that drink, and total cost they've paid so far
  static class Coworker {
    private String name;
    private String drink;
    private double price;
    private double cumulativeCost;

    public Coworker(String name, String drink, double price) {
      this.name = name;
      this.drink = drink;
      this.price = price;
      this.cumulativeCost = 0.0;
    }

    public String getName() {
      return name;
    }

    public String getDrink() {
      return drink;
    }

    public double getPrice() {
      return price;
    }

    public double getCumulativeCost() {
      return cumulativeCost;
    }

    public void updateCumulativeCost(double totalPrice) {
      this.cumulativeCost += totalPrice;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numCoworkers = 7;
    Coworker[] coworkers = new Coworker[numCoworkers];
    double totalPrice = 0.0;

    // First day: asks for each coworker's name, drink pref, and its price
    for (int i = 0; i < numCoworkers; i++) {
      System.out
          .print("Enter name, drink preference, and its price for coworker " + (i + 1) + " (separated by commas): ");
      String[] split = scanner.nextLine().split(",");
      double price = Double.valueOf(split[2].trim());
      Coworker coworker = new Coworker(split[0].trim(), split[1].trim(), price);
      totalPrice += price;
      coworkers[i] = coworker;
    }
    int i = 1;
    boolean nextDay = false;
    System.out.print("See which coworker pays on day " + i + "? (y/n) ");
    if (scanner.next().equals("y"))
      nextDay = true;
    // Any following days
    while (nextDay) {
      Coworker payer = getPayer(coworkers);
      System.out.println("Based on current spending, " + payer.getName() + " should pay for coffee today.");
      payer.updateCumulativeCost(totalPrice);
      i++;
      System.out.print("See which coworker pays on day " + i + "? (y/n) ");
      if (!scanner.next().equals("y"))
        nextDay = false;
    }
    scanner.close();
  }

  static Coworker getPayer(Coworker[] coworkers) {
    double minCost = Double.MAX_VALUE;
    Coworker payer = null;
    for (Coworker coworker : coworkers) {
      double cost = coworker.getCumulativeCost();
      if (cost < minCost) {
        minCost = cost;
        payer = coworker;
      }
    }
    return payer;
  }
}
