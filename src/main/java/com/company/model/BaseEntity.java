package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Comparable<com.company.model.BaseEntity> {

    private Long id;

    @Override
    public int compareTo(com.company.model.BaseEntity object) {
        return Long.compare(id, object.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        com.company.model.BaseEntity entity = (com.company.model.BaseEntity) obj;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}