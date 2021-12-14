package io.github.bananalang;

import io.github.bananalang.ba_native.objects.BananaInt;
import io.github.bananalang.ba_native.objects.BananaObject;
import io.github.bananalang.ba_native.objects.BananaOperator;

public class NativesTest {
    public static void main(String[] args) {
        System.out.println(
            BananaInt.getBaseInstance().getOperatorOverload(
                BananaOperator.ADD
            ).getCallback().call(
                BananaInt.valueOf(5),
                new BananaObject[] {
                    BananaInt.valueOf(2)
                }
            )
        );
    }
}
