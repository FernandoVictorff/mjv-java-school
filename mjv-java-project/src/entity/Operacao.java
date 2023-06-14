package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Operacao {
    private final LocalDate data;
    private final String descricao;
    private final Double valor;
    private final TipoOperacao tipoOperacao;

    public Operacao(Builder builder) {
        data = LocalDate.now();
        descricao = builder.descricao;
        valor = builder.valor;
        tipoOperacao = builder.tipoOperacao;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operacao operacao = (Operacao) o;
        return (
            Objects.equals(data, operacao.data) &&
            Objects.equals(descricao, operacao.descricao) &&
            Objects.equals(valor, operacao.valor) &&
            tipoOperacao == operacao.tipoOperacao
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, descricao, valor, tipoOperacao);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String descricao;
        private Double valor;
        private TipoOperacao tipoOperacao;

        private Builder() {}

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder valor(Double valor) {
            this.valor = valor;
            return this;
        }

        public Builder tipoOperacao(TipoOperacao tipoOperacao) {
            this.tipoOperacao = tipoOperacao;
            return this;
        }

        public Operacao build() {
            return new Operacao(this);
        }
    }
}