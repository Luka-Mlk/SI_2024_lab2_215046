import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class SILab2Test {

    // Branch 1: Null allItems list
    @Test(expected = RuntimeException.class)
    public void testAllItemsNull() {
        SILab2.checkCart(null, 100);
    }

    // Branch 2: Empty allItems list
    @Test
    public void testAllItemsEmpty() {
        Item[] allItems = {};
        assertTrue(SILab2.checkCart(Arrays.asList(allItems), 0));
    }

    // Branch 3: Item with null name and valid barcode
    @Test
    public void testItemWithNullNameAndValidBarcode() {
        Item[] allItems = {new Item(null, "12345", 100, (float) 0)};
        List<Item> itemList = Arrays.asList(allItems);
        assertTrue(SILab2.checkCart(itemList, 100));
        assertEquals("unknown", itemList.get(0).getName());
    }

    // Branch 4: Item with empty name and valid barcode
    @Test
    public void testItemWithEmptyNameAndValidBarcode() {
        Item[] allItems = {new Item("", "12345", 100, (float) 0)};
        List<Item> itemList = Arrays.asList(allItems);
        assertTrue(SILab2.checkCart(itemList, 100));
        assertEquals("unknown", itemList.get(0).getName());
    }

    // Branch 5: Item with illegal character in barcode
    @Test(expected = RuntimeException.class)
    public void testItemWithIllegalCharacterInBarcode() {
        Item[] allItems = {new Item("item1", "1234A", 100, (float) 0)};
        SILab2.checkCart(Arrays.asList(allItems), 100);
    }

    // Branch 6: Item with valid barcode and discount
    @Test
    public void testItemWithValidBarcodeAndDiscount() {
        Item[] allItems = {new Item("item1", "12345", 100, (float) 0.1)};
        assertTrue(SILab2.checkCart(Arrays.asList(allItems), 10));
    }

    // Branch 7: Item with valid barcode but no discount
    @Test
    public void testItemWithValidBarcodeButNoDiscount() {
        Item[] allItems = {new Item("item1", "12345", 100, (float) 0)};
        assertTrue(SILab2.checkCart(Arrays.asList(allItems), 100));
    }

    // Branch 8: Item with special discount condition
    @Test
    public void testItemWithSpecialDiscountCondition() {
        Item[] allItems = {new Item("item1", "012345", 350, (float) 0.1)};
        assertTrue(SILab2.checkCart(Arrays.asList(allItems), 32));
    }

    // Branch 9: Sum greater than payment
    @Test
    public void testSumGreaterThanPayment() {
        Item[] allItems = {new Item("item1", "12345", 100, (float) 0)};
        assertFalse(SILab2.checkCart(Arrays.asList(allItems), 50));
    }

    // Branch 10: Item with no barcode
    @Test(expected = RuntimeException.class)
    public void testNoBarcode() {
        Item[] allItems = {new Item("item1", null, 100, (float) 0)};
        SILab2.checkCart(Arrays.asList(allItems), 100);
    }

    // Branch 11: Item with price > 300, discount > 0, and barcode starting with '0'
    @Test
    public void testItemWithPriceOver300AndSpecialDiscount() {
        Item[] allItems = {new Item("item1", "012345", 350, (float) 0.1)};
        assertTrue(SILab2.checkCart(Arrays.asList(allItems), 32));
    }

    // Branch 12: Item with price > 300, discount == 0, and barcode starting with '0'
    @Test
    public void testItemWithPriceOver300AndNoDiscount() {
        Item[] allItems = {new Item("item1", "012345", 350, (float) 0)};
        assertFalse(SILab2.checkCart(Arrays.asList(allItems), 350));
    }

    @Test
    public void testPrice300DiscountPositiveBarcodeStartsWithZero() {
        Item[] allItems = {new Item("item1", "012345", 300, (float) 0.1)};
        assertTrue(SILab2.checkCart(Arrays.asList(allItems), 27));
    }

    // Branch: Price over 300, discount zero, barcode starts with '0'
    @Test
    public void testPriceOver300DiscountZeroBarcodeStartsWithZero() {
        Item[] allItems = {new Item("item1", "012345", 350, (float) 0)};
        assertFalse(SILab2.checkCart(Arrays.asList(allItems), 350));
    }

    // Branch: Price over 300, discount positive, barcode does not start with '0'
    @Test
    public void testPriceOver300DiscountPositiveBarcodeNotStartsWithZero() {
        Item[] allItems = {new Item("item1", "12345", 350, (float) 0.1)};
        assertFalse(SILab2.checkCart(Arrays.asList(allItems), 35));
    }

    // Branch: Price is exactly 300, discount negative, barcode starts with '0'
    @Test
    public void testPrice300DiscountNegativeBarcodeStartsWithZero() {
        Item[] allItems = {new Item("item1", "012345", 300, (float) -0.1)};
        assertFalse(SILab2.checkCart(Arrays.asList(allItems), 300));
    }

    // Branch: Price over 300, discount zero, barcode does not start with '0'
    @Test
    public void testPriceOver300DiscountZeroBarcodeNotStartsWithZero() {
        Item[] allItems = {new Item("item1", "12345", 350, (float) 0)};
        assertFalse(SILab2.checkCart(Arrays.asList(allItems), 350));
    }

    // Branch: Price is exactly 300, discount zero, barcode does not start with '0'
    @Test
    public void testPrice300DiscountZeroBarcodeNotStartsWithZero() {
        Item[] allItems = {new Item("item1", "12345", 300, (float) 0)};
        assertTrue(SILab2.checkCart(Arrays.asList(allItems), 300));
    }

}
