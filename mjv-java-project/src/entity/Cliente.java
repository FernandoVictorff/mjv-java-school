package entity;

import java.time.LocalDate;

public class Cliente {
    private final String nomeCliente;
    private final LocalDate dataNascimento;

    public Cliente(String nomeCliente, LocalDate dataNascimento) {
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
}
