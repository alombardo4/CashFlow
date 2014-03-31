package com.mikesandfriends.cashflow;

import junit.framework.TestCase;

/**
 * Used to test the Account .hashCode() method.
 * @author Michael Avery
 * @version 1.0
 *
 */
public class AccountHashTest extends TestCase {

    /**
     * The basic account to run tests against.
     */
    private Account testAccount;
    /**
     * The basic account to run tests against.
     */
    private Account testAccount2;
    /**
     * The number used for the hash.
     */
    private static final int TEST1 = 1433;
    /**
     * The number used for the hash.
     */
    private static final int TEST2 = 293;


    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        testAccount = new Account("Hithere");
        testAccount2 = new Account("Hit");
    }

    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests if object.equals the same object reference. Should be true.
     */
    public final void testHashCodeLengthLong() {
        final Account copy = testAccount;
        assertEquals(copy.hashCode(), TEST1);
    }

    /**
     * Tests if object.equals the same object reference. Should be true.
     */
    public final void testHashCodeLengthShort() {
        final Account copy = testAccount2;
        assertEquals(copy.hashCode(), TEST2);
    }
}

