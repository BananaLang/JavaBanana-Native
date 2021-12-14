package io.github.bananalang.ba_native.objects;

import java.math.BigInteger;

public class BananaInt extends BananaObject {
    private static final BananaInt[] CACHE;
    private static final BananaInt METHODS;
    public static final BananaInt ZERO, ONE, TWO;

    static {
        METHODS = new BananaInt();
        CACHE = new BananaInt[256];
        for (int i = 0; i < 256; i++) {
            CACHE[i] = new BananaInt(BigInteger.valueOf(i - 128));
        }
        ZERO = CACHE[128];
        ONE = CACHE[129];
        TWO = CACHE[130];
    }

    public final BigInteger value;

    public static BananaInt getBaseInstance() {
        return METHODS;
    }

    /** Method initialization */
    private BananaInt() {
        value = null;
        createOperatorOverload(BananaOperator.ADD, this::operatorAdd)
            .addOverload(BananaInt.class, BananaInt.class)
            .addOverload(BananaDecimal.class, BananaDecimal.class);
        createOperatorOverload(BananaOperator.SUBTRACT, this::operatorSubtract)
            .addOverload(BananaInt.class, BananaInt.class)
            .addOverload(BananaDecimal.class, BananaDecimal.class);
        createOperatorOverload(BananaOperator.MULTIPLY, this::operatorMultiply)
            .addOverload(BananaInt.class, BananaInt.class)
            .addOverload(BananaDecimal.class, BananaDecimal.class);
        createOperatorOverload(BananaOperator.DIVIDE, this::operatorDivide)
            .addOverload(BananaInt.class, BananaInt.class)
            .addOverload(BananaDecimal.class, BananaDecimal.class);
        createOperatorOverload(BananaOperator.EQUALS, this::operatorEquals)
            .addOverload(BananaBool.class, BananaInt.class)
            .addOverload(BananaBool.class, BananaDecimal.class);
    }

    protected BananaInt(BigInteger value) {
        super(METHODS);
        this.value = value;
    }

    public static BananaInt valueOf(BigInteger value) {
        return new BananaInt(value);
    }

    public static BananaInt valueOf(int value) {
        if (value > -129 & value < 128) {
            return CACHE[value + 128];
        }
        return new BananaInt(BigInteger.valueOf(value));
    }

    public static BananaInt valueOf(long value) {
        if (value > -129 & value < 128) {
            return CACHE[(int)(value + 128)];
        }
        return new BananaInt(BigInteger.valueOf(value));
    }

    public BigInteger getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    // Operator overloads
    protected BananaObject operatorAdd(BananaObject this_, BananaObject[] args) {
        BigInteger value = ((BananaInt)this_).value;
        BananaObject other = args[0];
        if (other instanceof BananaInt) {
            return valueOf(value.add(((BananaInt)other).value));
        } else {
            return BananaDecimal.valueOf(value.doubleValue() + ((BananaDecimal)other).value);
        }
    }

    protected BananaObject operatorSubtract(BananaObject this_, BananaObject[] args) {
        BigInteger value = ((BananaInt)this_).value;
        BananaObject other = args[0];
        if (other instanceof BananaInt) {
            return valueOf(value.subtract(((BananaInt)other).value));
        } else {
            return BananaDecimal.valueOf(value.doubleValue() - ((BananaDecimal)other).value);
        }
    }

    protected BananaObject operatorMultiply(BananaObject this_, BananaObject[] args) {
        BigInteger value = ((BananaInt)this_).value;
        BananaObject other = args[0];
        if (other instanceof BananaInt) {
            return valueOf(value.multiply(((BananaInt)other).value));
        } else {
            return BananaDecimal.valueOf(value.doubleValue() * ((BananaDecimal)other).value);
        }
    }

    protected BananaObject operatorDivide(BananaObject this_, BananaObject[] args) {
        BigInteger value = ((BananaInt)this_).value;
        BananaObject other = args[0];
        if (other instanceof BananaInt) {
            return valueOf(value.divide(((BananaInt)other).value));
        } else {
            return BananaDecimal.valueOf(value.doubleValue() / ((BananaDecimal)other).value);
        }
    }

    protected BananaObject operatorEquals(BananaObject this_, BananaObject[] args) {
        BigInteger value = ((BananaInt)this_).value;
        BananaObject other = args[0];
        if (other instanceof BananaInt) {
            return BananaBool.valueOf(value.equals(((BananaInt)other).value));
        } else {
            return BananaBool.valueOf(value.doubleValue() == ((BananaDecimal)other).value);
        }
    }
}
