package io.github.bananalang.ba_native.objects;

public enum BananaOperator {
    LOGICAL_OR("||"),
    LOGICAL_AND("&&"),
    BITWISE_OR("|"),
    BITWISE_XOR("^"),
    BITWISE_AND("&"),
    EQUALS("=="), NOT_EQUALS("!="),
    LESS_THAN("<"), GREATER_THAN(">"), LESS_THAN_EQUALS("<="), GREATER_THAN_EQUALS(">="),
    LEFT_SHIFT("<<"), RIGHT_SHIFT(">>"),
    ADD("+"), SUBTRACT("-"),
    MULTIPLY("*"), DIVIDE("/"), MODULUS("%"),

    UNARY_PLUS("+", true), NEGATE("-", true), NOT("!", true), BITWISE_INVERT("~", true);

    private final String literal;
    private final boolean unary;

    BananaOperator(String literal) {
        this.literal = literal;
        this.unary = false;
    }

    BananaOperator(String literal, boolean unary) {
        this.literal = literal;
        this.unary = unary;
    }

    public String getLiteral() {
        return literal;
    }

    public boolean isUnaryOp() {
        return unary;
    }

    public String getOverloadedMethodName() {
        return "operator" + (unary ? "_u" : "") + literal;
    }
}
