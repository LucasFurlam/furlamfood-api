package com.furlam.food.api.controller;

import com.furlam.food.api.assembler.PedidoInputDisassembler;
import com.furlam.food.api.assembler.PedidoModelAssembler;
import com.furlam.food.api.assembler.PedidoResumoModelAssembler;
import com.furlam.food.api.model.PedidoModel;
import com.furlam.food.api.model.PedidoResumoModel;
import com.furlam.food.api.model.input.PedidoInput;
import com.furlam.food.domain.exception.EntidadeNaoEncontradaException;
import com.furlam.food.domain.exception.NegocioException;
import com.furlam.food.domain.model.Pedido;
import com.furlam.food.domain.model.Usuario;
import com.furlam.food.domain.repository.PedidoRepository;
import com.furlam.food.domain.repository.filter.PedidoFilter;
import com.furlam.food.domain.service.EmissaoPedidoService;
import com.furlam.food.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
        List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));

        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}
