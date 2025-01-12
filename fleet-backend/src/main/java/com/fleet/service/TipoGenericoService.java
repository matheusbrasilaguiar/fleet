package com.fleet.service;

import com.fleet.domain.TipoGenerico;
import com.fleet.dto.request.TipoGenericoRequest;
import com.fleet.dto.response.TipoGenericoResponse;
import com.fleet.exception.EntidadeNaoEncontradaException;
import com.fleet.repository.TipoGenericoRepository;
import com.fleet.service.interfaces.TipoGenericoServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoGenericoService implements TipoGenericoServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(TipoGenericoService.class);

    @Autowired
    private TipoGenericoRepository tipoGenericoRepository;

    @Override
    public List<TipoGenericoResponse> getAll() {
        logger.info("Listando todos os tipos genéricos");
        return tipoGenericoRepository.findAll().stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public TipoGenericoResponse getById(Long id) {
        logger.info("Buscando tipo genérico com ID {}", id);
        TipoGenerico tipoGenerico = tipoGenericoRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Tipo genérico com ID {} não encontrado", id);
                return new EntidadeNaoEncontradaException("Tipo genérico com ID " + id + " não encontrado.");
            });
        return toResponse(tipoGenerico);
    }

    @Override
    public List<TipoGenericoResponse> listByCategory(String categoria) {
        logger.info("Listando tipos genéricos pela categoria {}", categoria);
        List<TipoGenerico> tipos = tipoGenericoRepository.findByCategoria(categoria);
        if (tipos.isEmpty()) {
            logger.warn("Nenhum tipo genérico encontrado para a categoria: {}", categoria);
            throw new EntidadeNaoEncontradaException("Nenhum tipo genérico encontrado para a categoria: " + categoria);
        }
        return tipos.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public TipoGenericoResponse save(TipoGenericoRequest tipoGenericoRequest) {
        logger.info("Salvando tipo genérico: {}", tipoGenericoRequest);
        validarEntrada(tipoGenericoRequest);

        TipoGenerico tipoGenerico = new TipoGenerico();
        tipoGenerico.setNome(tipoGenericoRequest.getNome());
        tipoGenerico.setDescricao(tipoGenericoRequest.getDescricao());
        tipoGenerico.setCategoria(tipoGenericoRequest.getCategoria());

        tipoGenerico = tipoGenericoRepository.save(tipoGenerico);
        logger.info("Tipo genérico salvo com ID {}", tipoGenerico.getId());
        return toResponse(tipoGenerico);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deletando tipo genérico com ID {}", id);
        if (!tipoGenericoRepository.existsById(id)) {
            logger.warn("Tentativa de deletar tipo genérico com ID {} que não existe", id);
            throw new EntidadeNaoEncontradaException("Tipo genérico com ID " + id + " não encontrado.");
        }
        tipoGenericoRepository.deleteById(id);
        logger.info("Tipo genérico com ID {} deletado com sucesso", id);
    }

    private void validarEntrada(TipoGenericoRequest tipoGenericoRequest) {
        if (tipoGenericoRequest.getNome() == null || tipoGenericoRequest.getNome().isEmpty()) {
            logger.warn("Validação falhou: nome do tipo genérico é obrigatório");
            throw new IllegalArgumentException("O nome do tipo genérico é obrigatório.");
        }
        if (tipoGenericoRequest.getCategoria() == null || tipoGenericoRequest.getCategoria().isEmpty()) {
            logger.warn("Validação falhou: categoria do tipo genérico é obrigatória");
            throw new IllegalArgumentException("A categoria do tipo genérico é obrigatória.");
        }
    }

    private TipoGenericoResponse toResponse(TipoGenerico tipoGenerico) {
        return new TipoGenericoResponse(
            tipoGenerico.getId(),
            tipoGenerico.getNome(),
            tipoGenerico.getDescricao(),
            tipoGenerico.getCategoria()
        );
    }
}
