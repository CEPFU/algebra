package de.fu_berlin.agdb.crepe.algebra.operators.numeric;

import de.fu_berlin.agdb.crepe.algebra.Match;
import de.fu_berlin.agdb.crepe.algebra.Operator;
import de.fu_berlin.agdb.crepe.algebra.OperatorNotSupportedException;
import de.fu_berlin.agdb.crepe.data.IEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author Simon Kalt
 */
public class ComparisonOperation extends Match {
    @Nonnull
    private final ComparisonOperationType operation;
    @Nonnull
    private final String attribute;
    @Nonnull
    private final Mode mode;
    private IEvent otherEvent;
    private Operator otherOperator;
    private Object otherObject;

    private ComparisonOperation(@Nonnull ComparisonOperationType operation, @Nonnull String attribute, @Nonnull Mode mode) {
        this.operation = Objects.requireNonNull(operation);
        this.attribute = Objects.requireNonNull(attribute);
        this.mode = Objects.requireNonNull(mode);
    }

    public ComparisonOperation(@Nonnull ComparisonOperationType operation, @Nonnull String attribute, @Nonnull IEvent otherEvent) {
        this(operation, attribute, Mode.EVENT);
        this.otherEvent = Objects.requireNonNull(otherEvent);
    }

    public ComparisonOperation(@Nonnull ComparisonOperationType operation, @Nonnull String attribute, @Nonnull Operator otherOperator) {
        this(operation, attribute, Mode.OPERATOR);
        this.otherOperator = Objects.requireNonNull(otherOperator);
    }

    public ComparisonOperation(@Nonnull ComparisonOperationType operation, @Nonnull String attribute, @Nonnull Object otherObject) {
        this(operation, attribute, Mode.OBJECT);
        this.otherObject = Objects.requireNonNull(otherObject);
    }

    @Override
    public boolean apply(IEvent event) throws OperatorNotSupportedException {

        switch (mode) {
            case EVENT:
                state = operation.applyTo(attribute, event, otherEvent);
                break;
            case OPERATOR:
                state = operation.applyTo(attribute, event, otherOperator);
                break;
            case OBJECT:
                state = operation.applyTo(attribute, event, otherObject);
                break;
        }

        if (state) {
            setMatchingEvent(event);
        }

        return state;
    }

    @Nonnull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(100);

        result.append(operation.toString())
                .append('(')
                .append(attribute)
                .append(", ");

        switch (mode) {
            case EVENT:
                result.append(otherEvent.toString());
                break;
            case OPERATOR:
                result.append(otherOperator.toString());
                break;
            case OBJECT:
                result.append(otherObject.toString());
                break;
        }

        result.append(')');

        return result.toString();
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComparisonOperation that = (ComparisonOperation) o;

        if (operation != that.operation) return false;
        if (!attribute.equals(that.attribute)) return false;
        if (mode != that.mode) return false;
        if (otherEvent != null ? !otherEvent.equals(that.otherEvent) : that.otherEvent != null) return false;
        if (otherOperator != null ? !otherOperator.equals(that.otherOperator) : that.otherOperator != null)
            return false;
        return !(otherObject != null ? !otherObject.equals(that.otherObject) : that.otherObject != null);

    }

    @Override
    public int hashCode() {
        int result = operation.hashCode();
        result = 31 * result + attribute.hashCode();
        result = 31 * result + mode.hashCode();
        result = 31 * result + (otherEvent != null ? otherEvent.hashCode() : 0);
        result = 31 * result + (otherOperator != null ? otherOperator.hashCode() : 0);
        result = 31 * result + (otherObject != null ? otherObject.hashCode() : 0);
        return result;
    }

    private enum Mode {
        EVENT, OPERATOR, OBJECT
    }
}
