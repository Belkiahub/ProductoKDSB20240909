package com.example.servicio.interfaces;

import org.springframework.data.domain.*;
import com.example.dtos.producto.*;
import java.util.*;

public interface IProductoKDSBServices {
    List<ProductoSalida> obtenerTodos();

    Page<ProductoSalida> obtenerTodosPaginados(Pageable pageable);

    ProductoSalida obtenerPorId(Integer id);

    ProductoSalida crear(ProductoGuardar productoGuardar);

    ProductoSalida editar(ProductoModificar productoModificar);

    void eliminarPorId(Integer id);
}
