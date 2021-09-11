package com.furlam.food.api.assembler;

import com.furlam.food.api.model.RestauranteModel;
import com.furlam.food.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteModel.class);
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
