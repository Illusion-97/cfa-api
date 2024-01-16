package fr.dawan.AppliCFABack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntityDto {
    protected long id;
	protected int version;
}
