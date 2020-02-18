/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicktop.app.model;

import com.clicktop.app.dto.PlanStatusDTO;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import lombok.Data;

@SqlResultSetMapping(name = "status", classes = @ConstructorResult(
        targetClass = PlanStatusDTO.class,
        columns = {
            @ColumnResult(name = "planName", type = String.class),                    
            @ColumnResult(name = "qtd", type = Long.class)
        }))

@NamedNativeQuery(name = "Plan.listStatus",
        resultSetMapping = "status",
        query = "select b.name as planName, count(a.id) as qtd from clicktop.company a inner join clicktop.plan b on a.id_plan = b.id group by b.name")

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "plan")
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public void update(Plan plan) {
        if (Optional.ofNullable(plan.getName()).isPresent() && !plan.getName().equals(this.name)) {
            this.name = plan.getName();
        }

        if (Optional.ofNullable(plan.getDescription()).isPresent() && !plan.getDescription().equals(this.description)) {
            this.description = plan.getDescription();
        }
    }

}
