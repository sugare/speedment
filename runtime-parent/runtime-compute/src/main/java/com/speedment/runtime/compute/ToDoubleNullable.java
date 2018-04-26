package com.speedment.runtime.compute;

import com.speedment.runtime.compute.expression.Expression;
import com.speedment.runtime.compute.expression.ExpressionType;
import com.speedment.runtime.compute.expression.Expressions;
import com.speedment.runtime.compute.internal.expression.ComposedUtil;
import com.speedment.runtime.compute.internal.expression.OrElseGetUtil;
import com.speedment.runtime.compute.internal.expression.OrElseThrowUtil;
import com.speedment.runtime.compute.internal.expression.OrElseUtil;
import com.speedment.runtime.compute.trait.*;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

/**
 * Expression that given an entity returns a {@code double} value, or
 * {@code null}. This expression can be implemented using a lambda, or it can be
 * a result of another operation. It has additional methods for operating on it.
 *
 * @param <T> type to extract from
 *
 * @see Function
 *
 * @author Emil Forslund
 * @since 3.1.0
 */
@FunctionalInterface
public interface ToDoubleNullable<T>
extends Expression<T>,
        ToNullable<T, Double>,
        HasAbs<ToDoubleNullable<T>>,
        HasSign<ToByteNullable<T>>,
        HasSqrt<ToDoubleNullable<T>>,
        HasNegate<ToDoubleNullable<T>>,
        HasHash<T>,
        HasCompare<T> {

    @Override
    default ExpressionType expressionType() {
        return ExpressionType.DOUBLE_NULLABLE;
    }

    default double applyAsDouble(T object) throws NullPointerException {
        return apply(object);
    }

    default ToDouble<T> orThrow() throws NullPointerException {
        return OrElseThrowUtil.orElseThrow(this);
    }

    default ToDouble<T> orElseGet(ToDouble<T> getter) {
        return OrElseGetUtil.orElseGet(this, getter);
    }

    default ToDouble<T> orElse(double value) {
        return OrElseUtil.orElse(this, value);
    }

    @Override
    default ToDoubleNullable<T> abs() {
        return Expressions.absOrNull(this);
    }

    @Override
    default ToDoubleNullable<T> negate() {
        return Expressions.negateOrNull(this);
    }

    @Override
    default ToByteNullable<T> sign() {
        return Expressions.signOrNull(this);
    }

    @Override
    default ToDoubleNullable<T> sqrt() {
        return Expressions.sqrtOrNull(this);
    }

    default ToDoubleNullable<T> mapIfPresent(DoubleUnaryOperator operator) {
        final ToDoubleNullable<T> delegate = this;
        return new ToDoubleNullable<T>() {

            @Override
            public Double apply(T t) {
                return delegate.isNull(t)
                    ? null
                    : operator.applyAsDouble(delegate.applyAsDouble(t));
            }

            @Override
            public ToDouble<T> orElseGet(ToDouble<T> getter) {
                return t -> delegate.isNull(t)
                    ? getter.applyAsDouble(t)
                    : operator.applyAsDouble(delegate.applyAsDouble(t));
            }

            @Override
            public ToDouble<T> orElse(double value) {
                return t -> delegate.isNull(t) ? value
                    : operator.applyAsDouble(delegate.applyAsDouble(t));
            }

            @Override
            public boolean isNull(T object) {
                return delegate.isNull(object);
            }

            @Override
            public boolean isNotNull(T object) {
                return delegate.isNotNull(object);
            }

            @Override
            public ToDoubleNullable<T> mapIfPresent(DoubleUnaryOperator nextOperator) {
                return delegate.mapIfPresent(operator.andThen(nextOperator));
            }
        };
    }

    @Override
    default long hash(T object) {
        if (isNull(object)) {
            return 0;
        } else {
            final long l = Double.doubleToLongBits(applyAsDouble(object));
            return (int) (l ^ (l >>> 32));
        }
    }

    @Override
    default int compare(T first, T second) {
        if (isNull(first)) {
            return isNull(second) ? 0 : 1;
        } else if (isNull(second)) {
            return -1;
        } else {
            return Double.compare(
                applyAsDouble(first),
                applyAsDouble(second)
            );
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    default <V> ToDoubleNullable<V> compose(Function<? super V, ? extends T> before) {
        return ComposedUtil.composeNullable((Function<V, T>) before, this);
    }
}
