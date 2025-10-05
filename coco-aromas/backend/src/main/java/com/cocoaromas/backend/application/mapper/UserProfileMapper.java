package com.cocoaromas.backend.application.mapper;

import com.cocoaromas.backend.api.dto.request.UserProfileRequestDto;
import com.cocoaromas.backend.api.dto.response.UserProfileResponseDto;
import com.cocoaromas.backend.domain.models.User;
import com.cocoaromas.backend.domain.models.UserProfile;

public class UserProfileMapper {

     // Constructor privado para evitar instanciación (regla SonarQube)
    private UserProfileMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static UserProfile toEntity(UserProfileRequestDto dto, User user) {
        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setNationalIdNumber(dto.getNationalIdNumber());
        profile.setPhoneNumber(dto.getPhoneNumber());
        profile.setAddress(dto.getAddress());
        profile.setPostalCode(dto.getPostalCode());
        profile.setProvince(dto.getProvince());
        profile.setCity(dto.getCity());
        profile.setCountry(dto.getCountry());
        profile.setSex(dto.getSex());
        profile.setBirthday(dto.getBirthday());
        profile.setTaxStatus(dto.getTaxStatus());
        profile.setAvatarUrl(dto.getAvatarUrl());
        return profile;
    }

   public static void updateEntity(UserProfile entity, UserProfileRequestDto dto) {
        if (dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) entity.setLastName(dto.getLastName());
        if (dto.getNationalIdNumber() != null) entity.setNationalIdNumber(dto.getNationalIdNumber());
        if (dto.getPhoneNumber() != null) entity.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAddress() != null) entity.setAddress(dto.getAddress());
        if (dto.getPostalCode() != null) entity.setPostalCode(dto.getPostalCode());
        if (dto.getProvince() != null) entity.setProvince(dto.getProvince());
        if (dto.getCity() != null) entity.setCity(dto.getCity());
        if (dto.getCountry() != null) entity.setCountry(dto.getCountry());
        if (dto.getSex() != null) entity.setSex(dto.getSex());
        if (dto.getBirthday() != null) entity.setBirthday(dto.getBirthday());
        if (dto.getTaxStatus() != null) entity.setTaxStatus(dto.getTaxStatus());
        if (dto.getAvatarUrl() != null) entity.setAvatarUrl(dto.getAvatarUrl());
    }

    public static UserProfileResponseDto toDto(UserProfile entity) {
        return new UserProfileResponseDto(
                entity.getId(),
                entity.getUser().getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNationalIdNumber(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getPostalCode(),
                entity.getProvince(),
                entity.getCity(),
                entity.getCountry(),
                entity.getSex(),
                entity.getBirthday() != null ? entity.getBirthday().toString() : null,
                entity.getTaxStatus(),
                entity.getAvatarUrl()
        );
    }

}
