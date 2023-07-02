package co.com.worldoffice.utils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DataConverter<E, D> {

    public abstract E dtoToEntity(D dto);

    public abstract D entityToDto(E entity);

    public List<E> dtoToEntity(List<D> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public List<D> entityToDto(List<E> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}