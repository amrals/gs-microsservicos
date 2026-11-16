package br.com.fiap.zelourbano.controller;

import br.com.fiap.zelourbano.dto.request.OcorrenciaRequestDTO;
import br.com.fiap.zelourbano.dto.response.OcorrenciaResponseDTO;
import br.com.fiap.zelourbano.service.OcorrenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ocorrencias")
@Tag(name = "Ocorrências", description = "Gerenciamento de problemas urbanos e gatilho automático de 80%")
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @PostMapping
    @Operation(summary = "Criar nova ocorrência")
    public ResponseEntity<OcorrenciaResponseDTO> criar(@Valid @RequestBody OcorrenciaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as ocorrências")
    public ResponseEntity<List<OcorrenciaResponseDTO>> listar() {
        return ResponseEntity.ok(ocorrenciaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar ocorrência por ID")
    public ResponseEntity<OcorrenciaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ocorrenciaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ocorrência existente")
    public ResponseEntity<OcorrenciaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody OcorrenciaRequestDTO dto) {
        return ResponseEntity.ok(ocorrenciaService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/curtir")
    @Operation(summary = "Adicionar curtida e verificar gatilho automático de 80% da região")
    public ResponseEntity<OcorrenciaResponseDTO> curtir(@PathVariable Long id) {
        return ResponseEntity.ok(ocorrenciaService.adicionarCurtida(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir ocorrência")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        ocorrenciaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}