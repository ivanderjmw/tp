package seedu.trippie.command;

import seedu.trippie.ExpenseList;
import seedu.trippie.PlaceList;
import seedu.trippie.Ui;
import seedu.trippie.exception.TrippieInvalidArgumentException;

public class BudgetCommand extends Command {

    private static final String FORMAT_ERROR_MESSAGE = "Incorrect format for [budget] command! "
            + "Please try the following:\nFormat: budget BUDGET\nExample: budget 4000";
    private static final String PARAMETER_ERROR_MESSAGE = "Please check that the budget value is in numerical form.";

    private Float budgetValue;

    public BudgetCommand(String userInput) throws TrippieInvalidArgumentException {
        this.budgetValue = null;
        try {
            this.budgetValue = extractBudgetValue(userInput);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            throw new TrippieInvalidArgumentException(PARAMETER_ERROR_MESSAGE);
        }
    }

    private Float extractBudgetValue(String userInput) {
        String budgetValueString = userInput.substring(7);
        return Float.parseFloat(budgetValueString);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, PlaceList place, ExpenseList expense) {
        expense.setBudgetValue(budgetValue);
        System.out.println("Successfully set your total budget to " + String.format("%.2f", budgetValue));
    }
}
