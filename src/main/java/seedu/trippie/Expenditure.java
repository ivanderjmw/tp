package seedu.trippie;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Expenditure {
    private static final String SECTION_BREAK = "_______________________________________________";
    private final String itemName;
    private final String itemCost;
    private final String dayBought;

    public Expenditure(String itemName, String itemCost, String dayBought) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.dayBought = dayBought;
    }

    public static void deleteItem(String userInput, ArrayList<Expenditure> expenditureList) {
        String[] segments = userInput.split("-e ");
        int expenseIndex = Integer.parseInt(segments[1]) - 1;
        if (expenseIndex < expenditureList.size()) {
            System.out.println(SECTION_BREAK);
            System.out.println("Noted. I've removed this item from the expenditure list.");
            System.out.println(expenditureList.get(expenseIndex).toString());
            expenditureList.remove(expenseIndex);
            numberOfItemsTracker(expenditureList);
        } else {
            System.out.println(SECTION_BREAK);
            System.out.println("Item has not been created yet. Enter a valid index.");

        }
    }

    public static void buyItem(String userInput, ArrayList<Expenditure> expenditureList) {
        Expenditure itemEntry;
        String itemName = extractItemName(userInput);
        String itemCost = extractItemCost(userInput);
        String dayBought = extractDayBought(userInput);
        itemEntry = new Expenditure(itemName, itemCost, dayBought);
        System.out.println(SECTION_BREAK);
        System.out.println("Got it! I've added the following item: " + itemEntry.toString());
        expenditureList.add(itemEntry);
        numberOfItemsTracker(expenditureList);
        System.out.println(SECTION_BREAK);
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCost() {
        return itemCost;
    }

    public String getDayBought() {
        return dayBought;
    }

    public static void displayExpenditureList(ArrayList<Expenditure> expenditureList) {
        if (expenditureList.isEmpty()) {
            System.out.println(SECTION_BREAK);
            System.out.println("There is currently nothing in your expenditure list.");
            System.out.println(SECTION_BREAK);
        } else {
            int listIndex = 1;
            System.out.println(SECTION_BREAK);
            System.out.println("Expenditure List:");
            System.out.println(SECTION_BREAK);
            for (Expenditure item: expenditureList) {
                System.out.println("[" + listIndex + "] " + item.toString());
                listIndex++;
            }
            System.out.println(SECTION_BREAK);
        }
    }

    public static void displayTotalExpenditure(ArrayList<Expenditure> expenditureList) throws NumberFormatException {
        try {
            double amount = 0.00;
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String finalTotalExpenditure;
            for (Expenditure spending : expenditureList) {
                amount += Double.parseDouble(extractCostFromList(spending));
            }
            finalTotalExpenditure = formatter.format(amount);
            System.out.println(SECTION_BREAK);
            System.out.println("Your current total spending is " + finalTotalExpenditure + ".");
            System.out.println(SECTION_BREAK);
        } catch (NumberFormatException e) {
            System.out.println("Error! Incorrect number format.");
        }
    }

    public static String extractItemName(String userInput) {
        int startIndex = userInput.indexOf("-i") + 2;
        int endIndex = userInput.indexOf("-c") - 1;
        return userInput.substring(startIndex,endIndex).trim();
    }

    public static String extractItemCost(String userInput) {
        int startIndex = userInput.indexOf("-c") + 2;
        int endIndex = userInput.indexOf("-d") - 1;
        String itemCost = userInput.substring(startIndex,endIndex).trim();
        if (itemCost.contains("$")) {
            itemCost = itemCost.replace("$", "");
        }
        return itemCost;
    }

    public static String extractDayBought(String userInput) {
        int startIndex = userInput.indexOf("-d") + 2;
        return userInput.substring(startIndex).replaceAll("[^0-9]","").trim();
    }

    public static String extractCostFromList(Expenditure spending) {
        int startIndex = spending.toString().indexOf("- $") + 3;
        return spending.toString().substring(startIndex);
    }

    public static void numberOfItemsTracker(ArrayList<Expenditure> expenditureList) {
        int numberOfItems = expenditureList.size();
        System.out.println("Now you have " + numberOfItems + " item(s) in the list.");
    }

    @Override
    public String toString() {
        return "Day " + getDayBought() + ": " + getItemName() + " - $" + getItemCost();
    }
}
