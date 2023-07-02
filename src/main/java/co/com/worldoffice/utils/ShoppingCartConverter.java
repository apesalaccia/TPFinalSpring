package co.com.worldoffice.utils;

import org.springframework.stereotype.Component;

import co.com.worldoffice.dto.ShoppingCartDto;
import co.com.worldoffice.entities.ShoppingCartEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ShoppingCartConverter extends DataConverter<ShoppingCartEntity, ShoppingCartDto> {

    @Override
    public ShoppingCartEntity dtoToEntity(ShoppingCartDto dto) {
        return ShoppingCartEntity.builder().idCart(dto.getIdCart()).IdCustomer(dto.getIdCustomer()).date(dto.getDate())
                .status(dto.getStatus()).build();
    }

    @Override
    public ShoppingCartDto entityToDto(ShoppingCartEntity entity) {
        return ShoppingCartDto.builder().idCart(entity.getIdCart()).IdCustomer(entity.getIdCustomer())
                .date(entity.getDate()).status(entity.getStatus()).build();
    }

}