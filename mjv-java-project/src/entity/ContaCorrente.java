package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static entity.TipoOperacao.*;

public class ContaCorrente {

    private final Cliente cliente;
    private Double saldo;
    private final Integer numeroConta;
    private final Integer numeroAgencia;
    private boolean ativa;
    private static int incremento;
    private List<Operacao> extrato;

    static {
        incremento++;
    }

    public ContaCorrente(Cliente cliente) {
        this.cliente = cliente;
        this.numeroConta = incremento;
        this.numeroAgencia = 1234;
        this.ativa = true;
        this.saldo = 0.0;
        extrato = new ArrayList<>();
    }

    private void addOperacao(String descricao, Double valor, TipoOperacao tipoOperacao) {
        extrato.add(
            Operacao.newBuilder()
                .descricao(descricao)
                .valor(valor)
                .tipoOperacao(tipoOperacao)
                .build()
        );
    }

    public Double consultarSaldo() {
        addOperacao("Consulta realizada", saldo, CONSULTA);
        return saldo;
    }

    public List<Operacao> consultarExtrato(LocalDate dataInicio, LocalDate dataFinal) {
        return (
            extrato
                .stream()
                .filter(
                    e ->
                        e.getData().isAfter(dataInicio.minusDays(1)) ||
                        e.getData().isBefore(dataFinal.plusDays(1))
                ).collect(Collectors.toList())
        );
    }

    public Double sacar(double valor) {
        if (valor == 0) {
            throw new IllegalArgumentException("Saque invalido, valor não pode ser igual a zero!");
        }
        if (saldo - valor < 0.0) {
            throw new IllegalArgumentException("Fundos insuficientes para sacar " + valor);
        }
        saldo -= valor;
        addOperacao("Saque realizado", valor, SAQUE);
        return valor;
    }

    public void depositar(double valor) {
        this.saldo = valor;
        addOperacao("Deposito realizado", valor, DEPOSITO);
    }

    public void transferir(ContaCorrente conta, Double valor) {
        conta.depositar(valor);
        addOperacao("Transferencia realizada", valor, TRANSFERENCIA);
    }

    public void cancelarConta(String justificativa) {
        this.ativa = false;
        addOperacao("Cancelamento: " + justificativa, 0.0, CANCELAMENTO);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaCorrente that = (ContaCorrente) o;
        return (
            ativa == that.ativa &&
            Objects.equals(cliente, that.cliente) &&
            Objects.equals(saldo, that.saldo) &&
            Objects.equals(numeroConta, that.numeroConta) &&
            Objects.equals(numeroAgencia, that.numeroAgencia) &&
            Objects.equals(extrato, that.extrato)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, saldo, numeroConta, numeroAgencia, ativa, extrato);
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "cliente=" + cliente +
                ", saldo=" + saldo +
                ", numeroConta=" + numeroConta +
                ", numeroAgencia=" + numeroAgencia +
                ", ativa=" + ativa +
                '}';
    }
}
