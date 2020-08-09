package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClosureTableRelations {
    private Integer closureTableRelationId;
    private Integer treeAncestorId;
    private Integer treeDescendantId;
    private Integer depth;
}
