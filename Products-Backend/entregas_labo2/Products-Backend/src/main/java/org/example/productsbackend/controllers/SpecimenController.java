package org.example.productsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.productsbackend.services.SpecimenService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/specimens")
@RequiredArgsConstructor
public class SpecimenController {

    private final SpecimenService specimenService;

    @GetMapping("/getAll")
    public ResponseEntity<GeneralResponse> getAllSpecimens(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        return buildResponse(
                "Specimens retrieved successfully from Sheikah Slate",
                HttpStatus.OK,
                specimenService.getAllSpecimens(page, size, sortBy, sortOrder)
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GeneralResponse> getSpecimenById(@PathVariable UUID id) {
        return buildResponse(
                "Specimen found",
                HttpStatus.OK,
                specimenService.getSpecimenById(id)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createSpecimen(@Valid @RequestBody CreateSpecimenRequest request) {
        return buildResponse(
                "Specimen registered in the census",
                HttpStatus.CREATED,
                specimenService.createSpecimen(request)
        );
    }

    private ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        return ResponseEntity.status(status).body(
                GeneralResponse.builder()
                        .uri(ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath())
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
        );
    }
}
