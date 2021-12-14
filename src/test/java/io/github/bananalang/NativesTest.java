package io.github.bananalang;

import io.github.bananalang.ba_native.objects.BananaDecimal;
import io.github.bananalang.ba_native.objects.BananaInt;
import io.github.bananalang.ba_native.objects.BananaObject;
import io.github.bananalang.ba_native.objects.BananaOperator;

public class NativesTest {
    public static void main(String[] args) {
        System.out.println(
            BananaDecimal.getBaseInstance().getOperatorOverload(
                BananaOperator.EQUALS
            ).getCallback().call(
                BananaDecimal.valueOf(5),
                new BananaObject[] {
                    BananaInt.valueOf(5)
                }
            )
        );
    }
}
