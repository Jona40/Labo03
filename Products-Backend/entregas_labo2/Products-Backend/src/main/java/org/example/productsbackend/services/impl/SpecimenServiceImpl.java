package org.example.productsbackend.services.impl;

import com.jetbrains.exported.JBRApi;
import lombok.RequiredArgsConstructor;
import org.example.productsbackend.common.mapper.SpecimenMapper;
import org.example.productsbackend.exceptions.ResourceNotFoundException;
import org.example.productsbackend.services.SpecimenService;

import java.awt.print.Pageable;

@JBRApi.Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    public PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Specimen> specimenPage = specimenRepository.findAll(pageable);

        if (specimenPage.isEmpty()) {
            throw new ResourceNotFoundException("No hay specimens en Hyrule records");
        }

        return specimenMapper.toPageableResponse(specimenPage);
    }


}
