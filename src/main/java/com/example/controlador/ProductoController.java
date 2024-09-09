package com.example.controlador;

import com.example.dtos.producto.ProductoGuardar;
import com.example.dtos.producto.ProductoModificar;
import com.example.dtos.producto.ProductoSalida;
import com.example.servicio.interfaces.IProductoKDSBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private IProductoKDSBServices productoKDSBServices;

    @GetMapping
    public ResponseEntity<Page<ProductoSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<ProductoSalida> producto = productoKDSBServices.obtenerTodosPaginados(pageable);
        if(producto.hasContent()){
            return ResponseEntity.ok(producto);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ProductoSalida>> mostrarTodos(){
        List<ProductoSalida> producto = productoKDSBServices.obtenerTodos();
        if(!producto.isEmpty()){
            return ResponseEntity.ok(producto);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoSalida> buscarPorId(@PathVariable Integer id){
        ProductoSalida producto = productoKDSBServices.obtenerPorId(id);

        if(producto != null){
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductoSalida> crear(@RequestBody ProductoGuardar productoGuardar){
        ProductoSalida producto = productoKDSBServices.crear(productoGuardar);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoSalida> editar(@PathVariable Integer id, @RequestBody ProductoModificar productoModificar){
        ProductoSalida producto = productoKDSBServices.editar(productoModificar);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        productoKDSBServices.eliminarPorId(id);
        return ResponseEntity.ok("Producto eliminada correctamente");
    }
}
