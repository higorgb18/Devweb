package com.iff.dev_web.entities;

public enum CdTipoCombustivelEnum {
    GASOLINA(1),
    ETANOL(2),
    GNV(3),
    DIESEL(4);

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
