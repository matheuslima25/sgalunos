package br.com.hero.sgalunos.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class Permissoes extends BaseIdEntity {

    private static final long serialVersionUID = 1L;

    private String nome;

}
