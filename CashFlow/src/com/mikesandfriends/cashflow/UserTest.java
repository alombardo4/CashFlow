package com.mikesandfriends.cashflow;

import junit.framework.TestCase;

/**
 * Tests the equals method in the User Class.
 * @author Brett Lyle
 *
 */

public class UserTest extends TestCase {
    /**
     * this is few test users.
     */
    private User testUser;

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        testUser = new User("a" , "pass1");
    }


    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }


    /**
     * tests to check if the username is null.
     */
    public final void testNullUser() {
        assertFalse(testUser.getUsername().equals(null));
    }

    /**
     * tests checks the setPassword method.
     */

    public final void testSetUsernameTest() {
        final User copy = testUser;
        copy.setUsername("b");
        assertTrue(copy.getUsername().equals("b"));
    }

    /**
     * tests to see if a copy is identical to the original.
     */

    public final void testCopy() {
        final User copy = new User("a", "pass1");
        assertTrue(copy.equals(testUser));
    }

    /**
     * Tests same username with different password.
     */

    public final void testSameUsername() {
        final User copy = new User("a" , "pass2");
        assertFalse(copy.equals(testUser));
    }

    /**
     * Tests same password with different username.
     */

    public final void testSamePassword() {
        final User copy = new User("b" , "pass1");
        assertFalse(copy.equals(testUser));
    }

}
