package com.mikesandfriends.cashflow;

import junit.framework.TestCase;

/**
 * Used to test the Account .equals method.
 * @author Alec Lombardo
 * @version 1.0
 *
 */
public class AccountEqualsTest extends TestCase {
    /**
     * The basic account to run tests against.
     */
    private Account testAccount;

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        testAccount = new Account("Test User");
    }

    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests if object.equals(null). Should be false.
     */
    public final void testNullObjectEquals() {
        assertFalse(testAccount.equals(null)); //needed for test
        //pmd doesn't like either way
    }

    /**
     * Tests if object.equals the same object reference. Should be true.
     */
    public final void testSameObjectEquals() {
        final Account copy = testAccount;
        assertTrue(testAccount.equals(copy)); //pmd doesn't like either way

    }

    /**
     * Tests if object.equals a different object with same name. Should be true.
     */
    public final void testSameNameEquals() {
        final Account test2 = new Account("Test User");
        assertTrue(testAccount.equals(test2)); //pmd doesn't like either way
    }

    /**
     * Tests if object.equals a different object of a different class.
     * Should be false.
     */
    public final void testDifferentClassEquals() {
        final Transaction trans = new Transaction("", 0, 0, null);
        assertFalse(testAccount.equals(trans)); //pmd doesn't like either way
    }

    /**
     * Tests if object.equals a different object of same class with different
     * name. Should be false.
     */
    public final void testDifferentNameEquals() {
        final Account test2 = new Account("Not test user");
        assertFalse(testAccount.equals(test2)); //pmd doesn't like either way
    }

}
