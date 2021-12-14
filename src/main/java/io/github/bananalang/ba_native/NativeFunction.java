package io.github.bananalang.ba_native;

import java.util.ArrayList;
import java.util.List;

import io.github.bananalang.ba_native.objects.BananaObject;

public class NativeFunction {
    @FunctionalInterface
    public static interface NativeFunctionCallback {
        public BananaObject call(BananaObject[] args);
    }

    public static class NativeFunctionOverload {
        private final Class<? extends BananaObject> returnType;
        private final Class<? extends BananaObject>[] argTypes;

        NativeFunctionOverload(Class<? extends BananaObject> returnType, Class<? extends BananaObject>[] argTypes) {
            this.returnType = returnType;
            this.argTypes = argTypes;
        }

        public Class<? extends BananaObject> getReturnType() {
            return returnType;
        }

        public Class<? extends BananaObject>[] getArgTypes() {
            return argTypes;
        }
    }

    private final String name;
    private final NativeFunctionCallback callback;
    private final List<NativeFunctionOverload> overloads;
    private int overloadChanges, lastOverloadChange;
    private NativeFunctionOverload[] overloadsArray;

    protected NativeFunction(String name, NativeFunctionCallback callback) {
        this.name = name;
        this.callback = callback;
        this.overloads = new ArrayList<>(1);
        this.overloadChanges = 0;
        this.lastOverloadChange = -1;
    }

    @SafeVarargs
    public final NativeFunction addOverload(Class<? extends BananaObject> returnType, Class<? extends BananaObject>... argTypes) {
        overloadChanges++;
        overloads.add(new NativeFunctionOverload(returnType, argTypes));
        return this;
    }

    public String getName() {
        return name;
    }

    public NativeFunctionCallback getCallback() {
        return callback;
    }

    public NativeFunctionOverload[] getOverloads() {
        if (overloadChanges != lastOverloadChange) {
            overloadsArray = overloads.toArray(new NativeFunctionOverload[overloads.size()]);
            lastOverloadChange = overloadChanges;
        }
        return overloadsArray;
    }
}
