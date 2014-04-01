package com.mikesandfriends.cashflow;

import java.util.GregorianCalendar;

import junit.framework.TestCase;

/**
 * Used to test the Account .equals method.
 * .
 * @author Michael Koval
 * @version 1.0
 * .
 */
public class IsValidTransactionTest extends TestCase {
    /**
     * The basic account to run tests against.
     */
    private Transaction testTransaction1;
    /**.
     * failing account to compare to
     */
    private Transaction testTransaction2;

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        //number just to have a constant date
        long timestamp = 1234567890;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(timestamp);
        testTransaction1 = new Transaction("name", 1, 1, cal);
        testTransaction2 = new Transaction("", 0, 0, null);

    }

    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests if object.equals(null). Should be false.
     */
    public final void testNullObjectEquals() {
        assertFalse(testTransaction1.equals(null)); // needed for test
        // pmd doesn't like either way
    }

    /**
     * Tests if object.equals the same object reference. Should be true.
     */
    public final void testSameObjectEquals() {
        final Transaction copy = testTransaction1;
        assertTrue(testTransaction1.equals(copy)); // pmd doesn't like either
                                                   // way

    }

    /**
     * Tests if object.equals a different object of a different class. Should be
     * false.
     */
    public final void testDifferentClassEquals() {
        final Account test = new Account("Test");
        assertFalse(testTransaction1.equals(test));
        // pmd doesn't like either way
    }

    /**
     * Tests if isValidTransaction works with no name.
     */
    public final void testNoName() {
        testTransaction1.setName("");
        assertFalse(testTransaction1.isValidTransaction());
    }

    /**
     * Tests if isValidTransaction works with no date.
     */
    public final void testNoDate() {
        testTransaction1.setDate(null);
        assertFalse(testTransaction1.isValidTransaction());
    }

    /**.
     * Tests if isValidTransaction works with no modifications
     */
    public final void testValidTransaction() {
        assertTrue(testTransaction1.isValidTransaction());
        assertFalse(testTransaction2.isValidTransaction());
    }

    /**
     * Tests if object.equals a different object of same class with different
     * name. Should be false.
     */
    public final void testDifferentNameEquals() {
        assertFalse(testTransaction1.equals(testTransaction2)); // pmd doesn't
                                                                // like either
                                                                // way
    }

}
