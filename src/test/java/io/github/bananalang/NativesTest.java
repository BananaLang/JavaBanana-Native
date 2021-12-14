package io.github.bananalang;

import io.github.bananalang.ba_native.objects.BananaInt;
import io.github.bananalang.ba_native.objects.BananaObject;
import io.github.bananalang.ba_native.objects.BananaOperator;

public class NativesTest {
    public static void main(String[] args) {
        BananaInt value = BananaInt.valueOf(5);
        System.out.println(value.getOperatorOverload(BananaOperator.ADD).getCallback().call(value, new BananaObject[] {BananaInt.valueOf(2)}));
    }
}
