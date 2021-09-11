package com.furlam.food.core.modelmapper;

import com.furlam.food.api.model.EnderecoModel;
import com.furlam.food.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        return modelMapper;
    }

}
