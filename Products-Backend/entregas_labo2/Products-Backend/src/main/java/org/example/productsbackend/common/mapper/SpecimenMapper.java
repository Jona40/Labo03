package org.example.productsbackend.common.mapper;

import org.example.productsbackend.domain.dto.request.CreateSpecimenRequest;
import org.example.productsbackend.domain.dto.request.UpdateSpecimenRequest;
import org.example.productsbackend.domain.dto.response.PageableResponse;
import org.example.productsbackend.domain.dto.response.SpecimenResponse;
import org.example.productsbackend.domain.entities.Specimen;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SpecimenMapper {
    public Specimen toEntityCreate(CreateSpecimenRequest request) {
        return Specimen.builder()
                .name(request.getName())
                .region(request.getRegion())
                .dangerLevel(request.getDangerLevel())
                .isFriendly(request.getIsFriendly())
                .build();
    }

    public Specimen toEntityUpdate(UpdateSpecimenRequest request, UUID id) {
        return Specimen.builder()
                .id(id)
                .name(request.getName())
                .region(request.getRegion())
                .dangerLevel(request.getDangerLevel())
                .isFriendly(request.getIsFriendly())
                .build();
    }

    public SpecimenResponse toDto(Specimen specimen) {
        return SpecimenResponse.builder()
                .id(specimen.getId())
                .name(specimen.getName())
                .region(specimen.getRegion())
                .dangerLevel(specimen.getDangerLevel())
                .isFriendly(specimen.getIsFriendly())
                .build();
    }

    public PageableResponse<SpecimenResponse> toPageableResponse(Page<Specimen> page) {
        List<SpecimenResponse> content = page.getContent().stream()
                .map(this::toDto)
                .toList();

        return PageableResponse.<SpecimenResponse>builder()
                .content(content)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}
