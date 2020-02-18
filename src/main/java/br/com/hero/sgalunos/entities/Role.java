package br.com.hero.sgalunos.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class Role extends BaseIdEntity {

    private static final long serialVersionUID = 1L;

    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permissoes_role", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "permissoes_id", referencedColumnName = "id")
    })
    private List<Permissoes> permissoes;

}
