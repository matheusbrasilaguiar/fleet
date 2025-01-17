package com.fleet.controller;

import com.fleet.dto.request.TipoGenericoRequest;
import com.fleet.dto.response.TipoGenericoResponse;
import com.fleet.service.interfaces.TipoGenericoServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoGenericoController {

    private static final Logger logger = LoggerFactory.getLogger(TipoGenericoController.class);

    @Autowired
    private TipoGenericoServiceInterface tipoGenericoService;

    /**
     * Listar todos os tipos genéricos
     */
    @GetMapping
    public ResponseEntity<List<TipoGenericoResponse>> getAll() {
        logger.info("Recebida requisicao para listar todos os tipos genericos");
        List<TipoGenericoResponse> tipos = tipoGenericoService.getAll();
        return ResponseEntity.ok(tipos);
    }

    /**
     * Buscar tipo genérico pelo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TipoGenericoResponse> getById(@PathVariable Long id) {
        logger.info("Recebida requisicao para buscar tipo generico com ID {}", id);
        TipoGenericoResponse tipoGenerico = tipoGenericoService.getById(id);
        return ResponseEntity.ok(tipoGenerico);
    }

    /**
     * Listar tipos genéricos por categoria
     */
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<TipoGenericoResponse>> listByCategory(@PathVariable String categoria) {
        logger.info("Recebida requisicao para listar tipos genericos pela categoria {}", categoria);
        List<TipoGenericoResponse> tipos = tipoGenericoService.listByCategory(categoria);
        return ResponseEntity.ok(tipos);
    }

    /**
     * Criar um novo tipo genérico
     */
    @PostMapping
    public ResponseEntity<TipoGenericoResponse> save(@RequestBody TipoGenericoRequest tipoGenericoRequest) {
        logger.info("Recebida requisicao para salvar um tipo generico: {}", tipoGenericoRequest);
        TipoGenericoResponse tipoSalvo = tipoGenericoService.save(tipoGenericoRequest);
        return ResponseEntity.status(201).body(tipoSalvo); // HTTP 201 Created
    }

    /**
     * Deletar tipo genérico pelo ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Recebida requisicao para deletar tipo generico com ID {}", id);
        tipoGenericoService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
