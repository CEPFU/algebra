package de.fu_berlin.agdb.crepe.algebra.operators.numeric;

import de.fu_berlin.agdb.crepe.algebra.Match;
import de.fu_berlin.agdb.crepe.algebra.Operator;
import de.fu_berlin.agdb.crepe.algebra.OperatorNotSupportedException;
import de.fu_berlin.agdb.crepe.data.Event;
import de.fu_berlin.agdb.crepe.data.IAttribute;
import de.fu_berlin.agdb.crepe.data.IEvent;

import java.util.Objects;

import static de.fu_berlin.agdb.crepe.algebra.operators.numeric.NumericOperationType.*;

/**
 * Match that performs numeric operations.
 *
 * @author Simon Kalt
 */
public class NumericOperation extends Match {
    private final NumericOperationType operation;
    private final String attribute;
    private Mode mode;
    private Operator firstOperator, secondOperator;
    private Object firstObject, secondObject;

    private NumericOperation(NumericOperationType operation, String attribute, Mode mode) {
        this.operation = Objects.requireNonNull(operation);
        this.attribute = Objects.requireNonNull(attribute);
        this.mode = Objects.requireNonNull(mode);
    }

    /**
     * Creates a numeric operation of the given type over two operators.
     *
     * @param operation      Type of the operation
     * @param firstOperator  First operand
     * @param secondOperator Second operand
     */
    public NumericOperation(NumericOperationType operation, String attribute, Operator firstOperator, Operator secondOperator) {
        this(operation, attribute, Mode.OPERATORS);
        this.firstOperator = Objects.requireNonNull(firstOperator);
        this.secondOperator = Objects.requireNonNull(secondOperator);

        // For backwards compatibility:
        this.setChildren(firstOperator, secondOperator);
    }

    /**
     * Creates a numeric operation of the given type over an object and an operator.
     *
     * @param operation Type of the operation
     * @param attribute attribute whose values the operation is performed on
     * @param first     first operand
     * @param second    second operand
     */
    public NumericOperation(NumericOperationType operation, String attribute, Object first, Operator second) {
        this(operation, attribute, Mode.OBJ_OP);

        this.firstObject = Objects.requireNonNull(first);
        this.secondOperator = Objects.requireNonNull(second);
    }

    /**
     * Creates a numeric operation of the given type over an operator and an object.
     *
     * @param operation Type of the operation
     * @param attribute attribute whose values the operation is performed on
     * @param first     first operand
     * @param second    second operand
     */
    public NumericOperation(NumericOperationType operation, String attribute, Operator first, Object second) {
        this(operation, attribute, Mode.OP_OBJ);

        this.firstOperator = Objects.requireNonNull(first);
        this.secondObject = Objects.requireNonNull(second);
    }

    /**
     * Creates a numeric operation of the given type over two objects.
     *
     * @param operation Type of the operation
     * @param attribute attribute whose values the operation is performed on
     * @param first     first operand
     * @param second    second operand
     */
    public NumericOperation(NumericOperationType operation, String attribute, Object first, Object second) {
        this(operation, attribute, Mode.OBJECTS);

        this.firstObject = Objects.requireNonNull(first);
        this.secondObject = Objects.requireNonNull(second);
    }

    /**
     * Creates an addition operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation add(String attribute, Operator first, Operator second) {
        return new NumericOperation(ADD, attribute, first, second);
    }

    /**
     * Creates an addition operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation add(String attribute, Object first, Operator second) {
        return new NumericOperation(ADD, attribute, first, second);
    }

    /**
     * Creates an addition operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation add(String attribute, Operator first, Object second) {
        return new NumericOperation(ADD, attribute, first, second);
    }

    /**
     * Creates an addition operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation add(String attribute, Object first, Object second) {
        return new NumericOperation(ADD, attribute, first, second);
    }

    /**
     * Creates an subtraction operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation subtract(String attribute, Operator first, Operator second) {
        return new NumericOperation(SUBTRACT, attribute, first, second);
    }

    /**
     * Creates an subtraction operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation subtract(String attribute, Object first, Operator second) {
        return new NumericOperation(SUBTRACT, attribute, first, second);
    }

    /**
     * Creates an subtraction operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation subtract(String attribute, Operator first, Object second) {
        return new NumericOperation(SUBTRACT, attribute, first, second);
    }

    /**
     * Creates an subtraction operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation subtract(String attribute, Object first, Object second) {
        return new NumericOperation(SUBTRACT, attribute, first, second);
    }

    /**
     * Creates an multiplication operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation multiply(String attribute, Operator first, Operator second) {
        return new NumericOperation(MULTIPLY, attribute, first, second);
    }

    /**
     * Creates an multiplication operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation multiply(String attribute, Object first, Operator second) {
        return new NumericOperation(MULTIPLY, attribute, first, second);
    }

    /**
     * Creates an multiplication operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation multiply(String attribute, Operator first, Object second) {
        return new NumericOperation(MULTIPLY, attribute, first, second);
    }

    /**
     * Creates an multiplication operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation multiply(String attribute, Object first, Object second) {
        return new NumericOperation(MULTIPLY, attribute, first, second);
    }

    /**
     * Creates an division operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation divide(String attribute, Operator first, Operator second) {
        return new NumericOperation(DIVIDE, attribute, first, second);
    }

    /**
     * Creates an division operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation divide(String attribute, Object first, Operator second) {
        return new NumericOperation(DIVIDE, attribute, first, second);
    }

    /**
     * Creates an division operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation divide(String attribute, Operator first, Object second) {
        return new NumericOperation(DIVIDE, attribute, first, second);
    }

    /**
     * Creates an division operation of the given operands.
     *
     * @param first  First operand
     * @param second Second operand
     */
    public static NumericOperation divide(String attribute, Object first, Object second) {
        return new NumericOperation(DIVIDE, attribute, first, second);
    }

    // TODO: Move to more generic class?
    public static Object getAttributeValue(Operator operator, String name) throws OperatorNotSupportedException {
        IEvent matchingEvent = operator.getMatchingEvent();

        if (matchingEvent == null)
            throw new OperatorNotSupportedException("Matching event is null.");

        IAttribute attr = matchingEvent
                .getAttributes()
                .get(name);

        if (attr == null)
            throw new OperatorNotSupportedException("Attribute not found");

        return attr.getValue();
    }

    @Override
    public boolean apply(IEvent event) throws OperatorNotSupportedException {
        switch (mode) {
            case OPERATORS:
                firstObject = getAttributeValue(firstOperator, attribute);
                secondObject = getAttributeValue(secondOperator, attribute);
                break;
            case OBJ_OP:
                secondObject = getAttributeValue(secondOperator, attribute);
                break;
            case OP_OBJ:
                firstObject = getAttributeValue(firstOperator, attribute);
                break;
            case OBJECTS:
                break;
        }

        // We have the attribute values now, so we can set the mode to "OBJECTS"
        this.mode = Mode.OBJECTS;

        Number result = operation.applyObj(firstObject, secondObject);
        setMatchingEvent(new Event(attribute, result));

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumericOperation that = (NumericOperation) o;

        if (operation != that.operation) return false;
        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null) return false;
        if (mode != that.mode) return false;
        if (firstOperator != null ? !firstOperator.equals(that.firstOperator) : that.firstOperator != null)
            return false;
        if (secondOperator != null ? !secondOperator.equals(that.secondOperator) : that.secondOperator != null)
            return false;
        if (firstObject != null ? !firstObject.equals(that.firstObject) : that.firstObject != null) return false;
        return !(secondObject != null ? !secondObject.equals(that.secondObject) : that.secondObject != null);

    }

    @Override
    public int hashCode() {
        int result = operation != null ? operation.hashCode() : 0;
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        result = 31 * result + (firstOperator != null ? firstOperator.hashCode() : 0);
        result = 31 * result + (secondOperator != null ? secondOperator.hashCode() : 0);
        result = 31 * result + (firstObject != null ? firstObject.hashCode() : 0);
        result = 31 * result + (secondObject != null ? secondObject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(50);
        result.append(operation.toString())
                .append("(");

        if (mode == Mode.OPERATORS || firstOperator != null && secondOperator != null) {
            result.append(firstOperator).append(", ").append(secondOperator);
        } else {
            switch (mode) {
                case OBJECTS:
                    result.append(firstObject).append(", ").append(secondObject);
                    break;
                case OBJ_OP:
                    result.append(firstObject).append(", ").append(secondOperator);
                    break;
                case OP_OBJ:
                    result.append(firstOperator).append(", ").append(secondObject);
                    break;
            }
        }

        result.append(")");

        return result.toString();
    }

    private enum Mode {
        OPERATORS, OBJECTS, OBJ_OP, OP_OBJ
    }
}
