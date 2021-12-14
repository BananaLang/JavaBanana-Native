package io.github.bananalang.ba_native;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import io.github.bananalang.ba_native.NativeFunction.NativeFunctionCallback;

public abstract class NativeModule {
    public final String name;
    private final Map<String, NativeFunction> functions;

    protected NativeModule(String name) {
        this.name = name;
        this.functions = new HashMap<>();
    }

    protected NativeFunction createFunction(String name, NativeFunctionCallback callback) {
        if (functions.containsKey(name)) {
            throw new IllegalStateException("Function with name " + name + " already exists");
        }
        NativeFunction function = new NativeFunction(name, callback);
        functions.put(name, function);
        return function;
    }

    public NativeFunction getFunction(String name) {
        NativeFunction function = functions.get(name);
        if (function == null) {
            throw new NoSuchElementException("No function with the name " + name);
        }
        return function;
    }

    public Map<String, NativeFunction> getFunctions() {
        return Collections.unmodifiableMap(functions);
    }
}
