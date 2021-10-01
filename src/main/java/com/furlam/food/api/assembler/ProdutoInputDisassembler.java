package com.furlam.food.api.assembler;

import com.furlam.food.api.model.input.ProdutoInput;
import com.furlam.food.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public ProdutoInput toInput(Produto produto) {
        return modelMapper.map(produto, ProdutoInput.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }
}
