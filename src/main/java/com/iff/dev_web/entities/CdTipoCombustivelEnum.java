package com.iff.dev_web.entities;

public enum CdTipoCombustivelEnum {
    Gasolina(1),
    Alcool(2),
    Gas(3),
    Diesel(4);

    private final int value;

    CdTipoCombustivelEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CdTipoCombustivelEnum fromValue(int value) {
        for (CdTipoCombustivelEnum combustivel : CdTipoCombustivelEnum.values()) {
            if (combustivel.getValue() == value) {
                return combustivel;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + value);
    }
}
