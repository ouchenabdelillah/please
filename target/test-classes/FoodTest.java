package hotel.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    @Test
    public void testFoodId() {
        Food food = new Food();

        // Set foodId and assert the getter returns the correct value
        food.setFoodId(101);
        assertEquals(101, food.getFoodId());
    }

    @Test
    public void testName() {
        Food food = new Food();

        // Set name and assert the getter returns the correct value
        food.setName("Pasta");
        assertEquals("Pasta", food.getName());
    }

    @Test
    public void testPrice() {
        Food food = new Food();

        // Set price and assert the getter returns the correct value
        food.setPrice(500);
        assertEquals(500, food.getPrice());
    }

    @Test
    public void testFoodIdSetterGetter() {
        Food food = new Food();

        // Set and get foodId
        food.setFoodId(202);
        assertEquals(202, food.getFoodId());
    }

    @Test
    public void testNameSetterGetter() {
        Food food = new Food();

        // Set and get name
        food.setName("Burger");
        assertEquals("Burger", food.getName());
    }

    @Test
    public void testPriceSetterGetter() {
        Food food = new Food();

        // Set and get price
        food.setPrice(100);
        assertEquals(100, food.getPrice());
    }
}
