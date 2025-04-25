package com.ats.rtem.common.mapper;

import com.ats.rtem.domain.dto.RoleDto;
import com.ats.rtem.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * RoleMapper is a MapStruct interface for converting between
 * {@link Role} entity objects and {@link RoleDto} data transfer objects.
 * <p>
 * This mapper is used to simplify and automate the process of mapping
 * between layers of the application, typically from persistence to service or controller layers.
 * </p>
 *
 * <p>
 * The {@code componentModel = "spring"} attribute allows this mapper
 * to be recognized and injected as a Spring Bean.
 * </p>
 *
 * @author Alexander Thomas Sayson
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    /**
     * Converts a {@link Role} entity to a {@link RoleDto}.
     *
     * @param role the Role entity to convert
     * @return the converted RoleDto object
     */
    RoleDto mapToDto(Role role);

    /**
     * Converts a {@link RoleDto} to a {@link Role} entity.
     *
     * @param roleDto the RoleDto object to convert
     * @return the converted Role entity
     */
    Role mapToEntity(RoleDto roleDto);

    /**
     * Updates the fields of an existing {@link Role} entity using the non-null values from the given {@link RoleDto}.
     * <p>
     * The entity's identifier (id) is intentionally ignored to prevent accidental modification of the primary key,
     * which is managed by JPA and must remain unchanged during updates.
     * </p>
     *
     * @param dto    the source {@link RoleDto} containing updated field values
     * @param entity the target {@link Role} entity to be updated
     */
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(RoleDto dto, @MappingTarget Role entity);
}
