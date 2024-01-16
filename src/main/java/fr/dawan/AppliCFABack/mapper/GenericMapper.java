package fr.dawan.AppliCFABack.mapper;

public interface GenericMapper<E,D> {
    D toDto(E entity);
    E toEntity(D dto);
}
