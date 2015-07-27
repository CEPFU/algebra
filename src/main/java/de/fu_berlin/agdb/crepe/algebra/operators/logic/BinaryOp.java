package de.fu_berlin.agdb.crepe.algebra.operators.logic;

import de.fu_berlin.agdb.crepe.algebra.Operator;
import de.fu_berlin.agdb.crepe.algebra.OperatorNotSupportedException;
import de.fu_berlin.agdb.crepe.data.IEvent;

import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * @author Simon Kalt
 */
public class BinaryOp extends Operator {
    private final List<Operator> operands;
    private final BinaryOperatorType type;

    /**
     * Creates a binary operator of the specified type and operands.
     *
     * @param ofOperands A list of operands for this operator
     * @param type       Type of the operator
     */
    public BinaryOp(BinaryOperatorType type, List<Operator> ofOperands) {
        this.operands = Objects.requireNonNull(ofOperands);
        this.type = Objects.requireNonNull(type);

        // For backwards compatibility:
        this.children = ofOperands.toArray(new Operator[ofOperands.size()]);
    }

    /**
     * Returns a new {@code and} operator of the specified operators.
     *
     * @param ofOperands A list of operands for this operator
     */
    public static BinaryOp and(List<Operator> ofOperands) {
        return new BinaryOp(BinaryOperatorType.AND, ofOperands);
    }

    /**
     * Returns a new {@code or} operator of the specified operators.
     *
     * @param ofOperands A list of operands for this operator
     */
    public static BinaryOp or(List<Operator> ofOperands) {
        return new BinaryOp(BinaryOperatorType.OR, ofOperands);
    }

    /**
     * Returns a new {@code xor} operator of the specified operators.
     *
     * @param ofOperands A list of operands for this operator
     */
    public static BinaryOp xor(List<Operator> ofOperands) {
        return new BinaryOp(BinaryOperatorType.XOR, ofOperands);
    }

    @Override
    public boolean apply(IEvent event) throws OperatorNotSupportedException {
        boolean lastResult = type.getUnit();
        BinaryOperator<Boolean> operator = type.getOperator();
        for (Operator operand : operands) {
            lastResult = operator.apply(lastResult, operand.apply(event));
        }

        return lastResult;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", type, operands);
    }

    @Override
    public void reset() {
        operands.forEach(Operator::reset);
    }

    public List<Operator> getOperands() {
        return operands;
    }

    public BinaryOperatorType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryOp binaryOp = (BinaryOp) o;

        if (!operands.equals(binaryOp.operands)) return false;
        return type == binaryOp.type;
    }

    @Override
    public int hashCode() {
        int result = operands.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
