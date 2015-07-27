package de.fu_berlin.agdb.crepe.algebra.operators.logic;

import org.junit.Test;

import java.util.function.BinaryOperator;

import static de.fu_berlin.agdb.crepe.algebra.operators.logic.BinaryOperatorType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test boolean binary operator types.
 *
 * @author Simon Kalt
 */
public class BinaryOperatorTypeTest {

    @Test
    public void testAndOperator() throws Exception {
        BinaryOperator<Boolean> and = AND.getOperator();
        assertFalse("false && false should return false", and.apply(false, false));
        assertFalse("false && true should return false", and.apply(false, true));
        assertFalse("true && false should return false", and.apply(true, false));
        assertTrue("true && true should return true", and.apply(true, true));
    }

    @Test
    public void testOrOperator() throws Exception {
        BinaryOperator<Boolean> or = OR.getOperator();
        assertFalse("false && false should return false", or.apply(false, false));
        assertTrue("false && true should return true", or.apply(false, true));
        assertTrue("true && false should return true", or.apply(true, false));
        assertTrue("true && true should return true", or.apply(true, true));
    }

    @Test
    public void testXorOperator() throws Exception {
        BinaryOperator<Boolean> xor = XOR.getOperator();
        assertFalse("false && false should return false", xor.apply(false, false));
        assertTrue("false && true should return true", xor.apply(false, true));
        assertTrue("true && false should return true", xor.apply(true, false));
        assertFalse("true && true should return false", xor.apply(true, true));
    }


    @Test
    public void testGetUnit() throws Exception {
        assertEquals("Unit for 'AND' is incorrect.", true, AND.getUnit());
        assertEquals("Unit for 'OR' is incorrect.", false, OR.getUnit());
        assertEquals("Unit for 'XOR' is incorrect.", false, XOR.getUnit());
    }
}
