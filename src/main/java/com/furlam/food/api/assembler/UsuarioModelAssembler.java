package com.furlam.food.api.assembler;

import com.furlam.food.api.model.UsuarioModel;
import com.furlam.food.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    public List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
