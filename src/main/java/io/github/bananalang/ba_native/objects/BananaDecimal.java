package io.github.bananalang.ba_native.objects;

public class BananaDecimal extends BananaObject {
    private static final BananaDecimal METHODS = new BananaDecimal();

    public final double value;

    public static BananaDecimal getBaseInstance() {
        return METHODS;
    }

    /** Method initialization */
    private BananaDecimal() {
        value = 0;
        createOperatorOverload(BananaOperator.ADD, this::operatorAdd)
            .addOverload(BananaDecimal.class, BananaDecimal.class)
            .addOverload(BananaDecimal.class, BananaInt.class);
    }

    protected BananaDecimal(double value) {
        super(METHODS);
        this.value = value;
    }

    public static BananaDecimal valueOf(double value) {
        return new BananaDecimal(value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    // Operator overloads
    protected BananaDecimal operatorAdd(BananaObject this_, BananaObject[] args) {
        double value = ((BananaDecimal)this_).value;
        BananaObject other = args[0];
        if (other instanceof BananaDecimal) {
            return valueOf(value + ((BananaDecimal)other).value);
        } else {
            return BananaDecimal.valueOf(value + ((BananaInt)other).value.doubleValue());
        }
    }
}
