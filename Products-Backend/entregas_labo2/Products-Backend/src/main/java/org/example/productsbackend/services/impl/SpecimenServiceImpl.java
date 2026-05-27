package org.example.productsbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.productsbackend.domain.dto.request.CreateSpecimenRequest;
import org.example.productsbackend.domain.dto.request.UpdateSpecimenRequest;
import org.example.productsbackend.domain.dto.response.PageableResponse;
import org.example.productsbackend.domain.dto.response.SpecimenResponse;
import org.example.productsbackend.domain.entities.Specimen;
import org.example.productsbackend.exceptions.ResourceNotFoundException;
import org.example.productsbackend.common.mappers.SpecimenMapper;
import org.example.productsbackend.repositories.SpecimenRepository;
import org.example.productsbackend.services.SpecimenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {

    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        Specimen savedSpecimen = specimenRepository.save(specimenMapper.toEntityCreate(request));
        return specimenMapper.toDto(savedSpecimen);
    }

    @Override
    public PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Specimen> specimenPage = specimenRepository.findAll(pageable);

        if (specimenPage.isEmpty()) {
            throw new ResourceNotFoundException("No hay specimens registrados en Hyrule records");
        }

        return specimenMapper.toPageableResponse(specimenPage);
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {
        return specimenRepository.findById(id)
                .map(specimenMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Specimen no encontrado en Hyrule Records"));
    }

    @Override
    @Transactional
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request) {
        this.getSpecimenById(id);
        Specimen toUpdate = specimenMapper.toEntityUpdate(request, id);
        return specimenMapper.toDto(specimenRepository.save(toUpdate));
    }

    @Override
    @Transactional
    public SpecimenResponse deleteSpecimen(UUID id) {
        SpecimenResponse existSpecimen = this.getSpecimenById(id);
        specimenRepository.deleteById(id);
        return existSpecimen;
    }
}
