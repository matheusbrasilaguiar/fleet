package com.fleet.service;

import com.fleet.domain.TipoGenerico;
import com.fleet.dto.request.TipoGenericoRequest;
import com.fleet.dto.response.TipoGenericoResponse;
import com.fleet.exception.EntidadeNaoEncontradaException;
import com.fleet.repository.TipoGenericoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoGenericoServiceTest {

    @Mock
    private TipoGenericoRepository tipoGenericoRepository;

    @InjectMocks
    private TipoGenericoService tipoGenericoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testGetAll_Success() {
        // Arrange
        TipoGenerico tipo1 = new TipoGenerico(1L, "Tipo1", "Descrição1", "Categoria1");
        TipoGenerico tipo2 = new TipoGenerico(2L, "Tipo2", "Descrição2", "Categoria2");
        when(tipoGenericoRepository.findAll()).thenReturn(List.of(tipo1, tipo2));

        // Act
        List<TipoGenericoResponse> response = tipoGenericoService.getAll();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(tipoGenericoRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Success() {
        // Arrange
        TipoGenerico tipoGenerico = new TipoGenerico(1L, "Tipo1", "Descrição1", "Categoria1");
        when(tipoGenericoRepository.findById(1L)).thenReturn(Optional.of(tipoGenerico));

        // Act
        TipoGenericoResponse response = tipoGenericoService.getById(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Tipo1", response.getNome());
        assertEquals("Descrição1", response.getDescricao());
        verify(tipoGenericoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        // Arrange
        when(tipoGenericoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
            EntidadeNaoEncontradaException.class,
            () -> tipoGenericoService.getById(1L)
        );

        assertEquals("Tipo genérico com ID 1 não encontrado.", exception.getMessage());
        verify(tipoGenericoRepository, times(1)).findById(1L);
    }

    @Test
    void testSave_Success() {
        // Arrange
        TipoGenericoRequest request = new TipoGenericoRequest();
        request.setNome("Tipo1");
        request.setDescricao("Descrição1");
        request.setCategoria("Categoria1");

        TipoGenerico tipoGenerico = new TipoGenerico(null, "Tipo1", "Descrição1", "Categoria1");
        TipoGenerico savedTipoGenerico = new TipoGenerico(1L, "Tipo1", "Descrição1", "Categoria1");
        when(tipoGenericoRepository.save(any(TipoGenerico.class))).thenReturn(savedTipoGenerico);

        // Act
        TipoGenericoResponse response = tipoGenericoService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Tipo1", response.getNome());
        verify(tipoGenericoRepository, times(1)).save(any(TipoGenerico.class));
    }

    @Test
    void testDelete_Success() {
        // Arrange
        when(tipoGenericoRepository.existsById(1L)).thenReturn(true);

        // Act
        tipoGenericoService.delete(1L);

        // Assert
        verify(tipoGenericoRepository, times(1)).existsById(1L);
        verify(tipoGenericoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        when(tipoGenericoRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
            EntidadeNaoEncontradaException.class,
            () -> tipoGenericoService.delete(1L)
        );

        assertEquals("Tipo genérico com ID 1 não encontrado.", exception.getMessage());
        verify(tipoGenericoRepository, times(1)).existsById(1L);
    }
}
