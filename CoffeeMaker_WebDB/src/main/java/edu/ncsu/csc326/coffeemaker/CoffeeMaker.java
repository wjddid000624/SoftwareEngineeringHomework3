package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.db.CreateDBTables;
import edu.ncsu.csc326.coffeemaker.db.PurchasedCoffeeDB;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

/**
 * @author Sarah Heckman
 */
public class CoffeeMaker {
	/** Array of recipes in coffee maker */
	private static RecipeBook recipeBook;
	/** Inventory of the coffee maker */
    private static Inventory inventory;
	
    /**
     * Constructor for the coffee maker
     *
     */
	public CoffeeMaker() {
	    recipeBook = new RecipeBook();
		inventory = new Inventory();
		CreateDBTables.createTables();
	}
	
	/**
	 * Returns true if the recipe is added to the
	 * list of recipes in the CoffeeMaker and false
	 * otherwise.
	 * @param r
	 * @return boolean
	 */
	public boolean addRecipe(Recipe r) {
		return recipeBook.addRecipe(r);
	}
	
	/**
	 * Returns the name of the successfully deleted recipe
	 * or null if the recipe cannot be deleted.
	 * 
	 * @param recipeToDelete
	 * @return String
	 */
	public String deleteRecipe(String name) {
		return recipeBook.deleteRecipe(name);
	}
	
	/**
	 * Returns the name of the successfully edited recipe
	 * or null if the recipe cannot be edited.
	 * @param recipeToEdit
	 * @param r
	 * @return String
	 */
	public String editRecipe(String name, Recipe r) {
		return recipeBook.editRecipe(name, r);
	}
	
	/**
	 * Returns true if the recipe exists
	 * @param name
	 * @return boolean
	 */
	public boolean recipeExists(String name) {
		return recipeBook.recipeExists(name);
	}
    
    /**
     * Returns void if inventory was successfully added
     * @param amtCoffee
     * @param amtMilk
     * @param amtSugar
     * @param amtChocolate
     * @return void
     */
    public synchronized void addInventory(String amtCoffee, String amtMilk, String amtSugar, String amtChocolate) throws InventoryException {
	    inventory.addCoffee(amtCoffee);
	    inventory.addMilk(amtMilk);
	    inventory.addSugar(amtSugar);
	    inventory.addChocolate(amtChocolate);
    }
    
    /**
     * Returns the inventory of the coffee maker
     * @return Inventory
     */
    public synchronized String checkInventory() {
        return inventory.toString();
    }
    
    /**
     * Returns the change of a user's beverage purchase, or
     * the user's money if the beverage cannot be made
     * @param r
     * @param amtPaid
     * @return int
     */
    public synchronized int makeCoffee(int recipeToPurchase, int amtPaid) {
        int change = 0;
        
        if (getRecipes()[recipeToPurchase] == null) {
        	change = amtPaid;
        } else if (getRecipes()[recipeToPurchase].getPrice() <= amtPaid) {
        	if (inventory.useIngredients(getRecipes()[recipeToPurchase])) {
        		change = amtPaid - getRecipes()[recipeToPurchase].getPrice();
        	} else {
        		change = amtPaid;
        	}
        } else {
        	change = amtPaid;
        }
        
        PurchasedCoffeeDB.addCoffeePurchased(getRecipes()[recipeToPurchase], amtPaid, change);
        
        return change;
    }

	/**
	 * Returns the list of Recipes in the RecipeBook.
	 * @return Recipe []
	 */
	public synchronized Recipe[] getRecipes() {
		return recipeBook.getRecipes();
	}
}
