package de.fu_berlin.agdb.crepe.algebra.operators.logic;

import java.util.function.BinaryOperator;

/**
 * @author Simon Kalt
 */
public enum BinaryOperatorType {
    AND(true, Boolean::logicalAnd),
    OR(false, Boolean::logicalOr),
    XOR(false, Boolean::logicalXor);

    private final boolean unit;
    private final BinaryOperator<Boolean> operator;

    BinaryOperatorType(boolean unit, BinaryOperator<Boolean> operator) {
        this.unit = unit;
        this.operator = operator;
    }

    /**
     * Returns the associated binary operator.
     */
    public BinaryOperator<Boolean> getOperator() {
        return operator;
    }

    /**
     * Returns the associated unit.
     */
    public boolean getUnit() {
        return unit;
    }
}
