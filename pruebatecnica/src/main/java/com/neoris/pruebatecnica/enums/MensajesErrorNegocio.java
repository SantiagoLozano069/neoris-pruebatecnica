package com.neoris.pruebatecnica.enums;

import lombok.Getter;

@Getter
public enum MensajesErrorNegocio {

    EXISTE_USUARIO_CON_MISMA_INDENTIFICACION("Ya existe un usuario con este mismo numero de indentificacion," +
            " por favor verifique la información."),
    NO_EXISTE_USUARIO_CON_ESTE_ID("No existe usuario con el id ingresado : "),

    ID_CLIENTE_NULL("El ID es obligatorio para editar el cliente."),

    EXISTE_CUENTA_CON_MISMO_NUMERO_CUENTA("Ya existe una cuenta con este mismo numero de de cuenta," +
                                                     " por favor verifique la información."),

    NO_EXISTE_CUENTA_CON_ESTE_ID("No existe cuenta con el id ingresado : "),

    ID_CUENTA_NULL("El ID es obligatorio para editar la cuenta."),

    SALDO_NO_DISPONIBLE("Saldo no disponible"),

    NO_EXISTE_MOVIMIENTO_CON_ESTE_ID("No existe movimiento con el id ingresado : "),

    ID_MOVIMIENTO_NULL("El ID es obligatorio para editar el movimiento."),

    NO_EXISTE_MOVIMIENTO_EN_EL_RAGO_DE_FECHAS("No existen movimientos en el rango de fechas ingresado : ");


    String mensaje;
    MensajesErrorNegocio(String mensaje){
        this.mensaje = mensaje;
    }


}
