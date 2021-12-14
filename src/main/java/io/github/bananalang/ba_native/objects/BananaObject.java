package io.github.bananalang.ba_native.objects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import io.github.bananalang.ba_native.objects.BananaMethod.BananaMethodCallback;

public abstract class BananaObject {
    private final Map<String, BananaMethod> methods;
    private final Map<BananaOperator, BananaMethod> operatorOverloads;

    protected BananaObject() {
        this.methods = new HashMap<>();
        this.operatorOverloads = new HashMap<>();
    }

    protected BananaObject(BananaObject copyMethodsFrom) {
        this.methods = copyMethodsFrom.methods;
        this.operatorOverloads = copyMethodsFrom.operatorOverloads;
    }

    protected BananaMethod createFunction(String name, BananaMethodCallback callback) {
        if (methods.containsKey(name)) {
            throw new IllegalStateException("Method with name " + name + " already exists");
        }
        BananaMethod method = new BananaMethod(name, callback);
        methods.put(name, method);
        return method;
    }

    protected BananaMethod createOperatorOverload(BananaOperator operator, BananaMethodCallback callback) {
        if (operatorOverloads.containsKey(operator)) {
            throw new IllegalStateException("Operator overload for operator " + operator + " already exists");
        }
        BananaMethod method = new BananaMethod(operator.getOverloadedMethodName(), callback);
        operatorOverloads.put(operator, method);
        return method;
    }

    public BananaMethod getMethod(String name) {
        BananaMethod method = methods.get(name);
        if (method == null) {
            throw new NoSuchElementException("No method with name " + name);
        }
        return method;
    }

    public BananaMethod getOperatorOverload(BananaOperator operator) {
        BananaMethod method = operatorOverloads.get(operator);
        if (method == null) {
            throw new NoSuchElementException("No operator overload for operator " + operator);
        }
        return method;
    }

    public Map<String, BananaMethod> getMethods() {
        return Collections.unmodifiableMap(methods);
    }

    public Map<BananaOperator, BananaMethod> getOperatorOverloads() {
        return Collections.unmodifiableMap(operatorOverloads);
    }

    public static BananaObject getBaseInstance() {
        return null;
    }
}
