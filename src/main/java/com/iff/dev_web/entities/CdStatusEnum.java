package com.iff.dev_web.entities;

public enum CdStatusEnum {
    Analise(1),
    Vigente(2),
    Negado(3),
    Cancelado(4);

    private final int value;

    CdStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CdStatusEnum fromValue(int value) {
        for (CdStatusEnum status : CdStatusEnum.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + value);
    }
}
