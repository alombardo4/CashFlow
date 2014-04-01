package com.mikesandfriends.cashflow;

import junit.framework.TestCase;

/**
 * Used to test the method in Account
 * which tests whether or not the balance
 * is still valid after a potential
 * withdrawal.
 *
 * @author Chelsea
 *
 */
public class AccountStillValidTest extends TestCase {
    /**
     * Account used for testing purposes.
     */
    private Account testAccount;
    /**
     * Balance for testing.
     */
    private final double balance = 50.00;
    /**
     * Number less than balance for testing.
     */
    private final double less = 30.00;
    /**
     * Number greater than balance for testing.
     */
    private final double more = 60.00;
    /**
     * Set up the account for testing.
     */
    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        testAccount = new Account("Testing");
    }
/**
 * Establish the tear down for garbage collection.
 */
    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }
    /**
     * Test if the account is initialized correctly.
     */
    public final void testIfNull() {
        assertFalse(testAccount.equals(null));
    }
    /**
     * Test if the initial balance is set to zero as shown in the
     * Account constructor.
     */
    public final void testInitialBalance() {
        assertTrue(testAccount.getBalance() == 0);
    }
    /**
     * Test if the account is still valid after a
     * potential withdrawal - should be true because
     * the balance is greater than the withdrawal.
     */
    public final void testBalance1() {
        testAccount.setBalance(balance);
        assertTrue(testAccount.validAfterWithdrawal(less));
    }
    /**
     * Test if the account is still valid after a potential
     * withdrawal - should be true because the balance is equal
     * to the withdrawal. The balance is allowed to be zero.
     */
    public final void testBalance2() {
        testAccount.setBalance(balance);
        assertTrue(testAccount.validAfterWithdrawal(balance));
    }
    /**
     * Test if the account is still valid after a potential
     * withdrawal - should be false because the balance is
     * less than the withdrawal.
     */
    public final void testBalance3() {
        testAccount.setBalance(balance);
        assertFalse(testAccount.validAfterWithdrawal(more));
    }

}
