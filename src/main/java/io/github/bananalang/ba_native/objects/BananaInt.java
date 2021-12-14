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

    /** Method initialization */
    private BananaInt() {
        value = null;
        createOperatorOverload(BananaOperator.ADD, this::operatorAdd)
            .addOverload(BananaInt.class, BananaInt.class)
            .addOverload(BananaDecimal.class, BananaDecimal.class);
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
}
