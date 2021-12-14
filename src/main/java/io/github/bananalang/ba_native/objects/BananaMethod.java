package io.github.bananalang.ba_native.objects;

import java.util.ArrayList;
import java.util.List;

public final class BananaMethod {
    @FunctionalInterface
    public static interface BananaMethodCallback {
        public BananaObject call(BananaObject this_, BananaObject[] args);
    }

    public static class BananaMethodOverload {
        private final Class<? extends BananaObject> returnType;
        private final Class<? extends BananaObject>[] argTypes;

        BananaMethodOverload(Class<? extends BananaObject> returnType, Class<? extends BananaObject>[] argTypes) {
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
    private final BananaMethodCallback callback;
    private final List<BananaMethodOverload> overloads;
    private int overloadChanges, lastOverloadChange;
    private BananaMethodOverload[] overloadsArray;

    protected BananaMethod(String name, BananaMethodCallback callback) {
        this.name = name;
        this.callback = callback;
        this.overloads = new ArrayList<>(1);
        this.overloadChanges = 0;
        this.lastOverloadChange = -1;
    }

    @SafeVarargs
    public final BananaMethod addOverload(Class<? extends BananaObject> returnType, Class<? extends BananaObject>... argTypes) {
        overloadChanges++;
        overloads.add(new BananaMethodOverload(returnType, argTypes));
        return this;
    }

    public String getName() {
        return name;
    }

    public BananaMethodCallback getCallback() {
        return callback;
    }

    public BananaMethodOverload[] getOverloads() {
        if (overloadChanges != lastOverloadChange) {
            overloadsArray = overloads.toArray(new BananaMethodOverload[overloads.size()]);
            lastOverloadChange = overloadChanges;
        }
        return overloadsArray;
    }
}
