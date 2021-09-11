package com.furlam.food.api.assembler;

import com.furlam.food.api.model.CidadeModel;
import com.furlam.food.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModel toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModel.class);
    }

    public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
