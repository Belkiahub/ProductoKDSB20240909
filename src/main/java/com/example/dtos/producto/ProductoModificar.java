package com.example.dtos.producto;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class ProductoModificar implements Serializable{
    private Integer id;

    private String NombreKDSB;

    private String DescripcionKDSB;

    private Integer Precio;
}
