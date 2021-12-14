package io.github.bananalang.ba_native.objects;

public class BananaBool extends BananaObject {
    private static final BananaBool METHODS;
    public static final BananaBool TRUE, FALSE;

    static {
        METHODS = new BananaBool();
        TRUE = new BananaBool(true);
        FALSE = METHODS;
    }

    public final boolean value;

    public static BananaBool getBaseInstance() {
        return METHODS;
    }

    /** Method initialization */
    private BananaBool() {
        value = false;
    }

    protected BananaBool(boolean value) {
        super(METHODS);
        this.value = value;
    }

    public static BananaBool valueOf(boolean value) {
        return value ? TRUE : FALSE;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value ? "true" : "false";
    }
}
