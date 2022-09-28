package com.br.ayrton.todolist.model.enums;

import lombok.Getter;

@Getter
public enum StatusTask {
    NAOINICIADA("Não Iniciada"),ANDAMENTO("Em Andamento"),CONCLUIDA ("Concluída");

    private String descricao;

    StatusTask(String descricao) {
        this.descricao = descricao;
    }
}
