package com.furlam.food.domain.service;

import com.furlam.food.domain.exception.GrupoNaoEncontradoException;
import com.furlam.food.domain.model.Grupo;
import com.furlam.food.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroGrupoService {

    private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public void excluir(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(grupoId);
        } catch (DataIntegrityViolationException e) {
            throw new GrupoNaoEncontradoException(String.format(MSG_GRUPO_EM_USO, grupoId));
        }
    }

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

}
