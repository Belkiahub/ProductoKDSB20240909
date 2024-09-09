package com.example.servicio.implementacion;

import com.example.dtos.producto.ProductoGuardar;
import com.example.dtos.producto.ProductoModificar;
import com.example.dtos.producto.ProductoSalida;
import com.example.modelo.ProductoKDSB;
import com.example.repositorio.IProductKDSBRepository;
import com.example.servicio.interfaces.IProductoKDSBServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoKDSBServices implements IProductoKDSBServices {
    @Autowired
    private IProductKDSBRepository productKDSBRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductoSalida> obtenerTodos() {
        List<ProductoKDSB> productoKDSBS = productKDSBRepository.findAll();

        return productoKDSBS.stream()
                .map(categoria -> modelMapper.map(categoria, ProductoSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductoSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<ProductoKDSB> page = productKDSBRepository.findAll(pageable);

        List<ProductoSalida> categoriasDto = page.stream()
                .map(categoria -> modelMapper.map(categoria, ProductoSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(categoriasDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public ProductoSalida obtenerPorId(Integer id) {
        return modelMapper.map(productKDSBRepository.findById(id).get(), ProductoSalida.class);
    }

    @Override
    public ProductoSalida crear(ProductoGuardar productoGuardar) {
        ProductoKDSB productoKDSB = productKDSBRepository.save(modelMapper.map(productoGuardar, ProductoKDSB.class));
        return modelMapper.map(productoKDSB, ProductoSalida.class);
    }

    @Override
    public ProductoSalida editar(ProductoModificar productoModificar) {
        ProductoKDSB productoKDSB = productKDSBRepository.save(modelMapper.map(productoModificar, ProductoKDSB.class));
        return modelMapper.map(productoKDSB, ProductoSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productKDSBRepository.deleteById(id);
    }
}
