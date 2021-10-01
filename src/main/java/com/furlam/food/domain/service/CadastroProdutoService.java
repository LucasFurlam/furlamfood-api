package com.furlam.food.domain.service;

import com.furlam.food.domain.exception.ProdutoNaoEncontradoException;
import com.furlam.food.domain.model.Produto;
import com.furlam.food.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }
}
