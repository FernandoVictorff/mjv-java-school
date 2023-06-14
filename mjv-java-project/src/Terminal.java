import entity.Cliente;
import entity.ContaCorrente;
import entity.Operacao;

import java.time.LocalDate;
import java.util.List;

public class Terminal {
    public static void main(String[] args) {
        ContaCorrente conta1 = new ContaCorrente(
            new Cliente("Jhon Doe", LocalDate.of(1980, 12, 5))
        );
        var conta2 = new ContaCorrente(
            new Cliente("Maria", LocalDate.of(1999, 12, 2))
        );

        conta1.consultarSaldo();
        conta1.depositar(2000);
        conta1.sacar(1999);
        conta1.transferir(conta2, 1.0);
        conta1.cancelarConta("Falido");
        mostrarExtratoDaConta(conta1, LocalDate.now(), LocalDate.now());
        conta2.consultarSaldo();
        System.out.println();
        mostrarExtratoDaConta(conta2, LocalDate.now(), LocalDate.now());
    }

    private static void mostrarExtratoDaConta(ContaCorrente conta, LocalDate de, LocalDate ate) {
        List<Operacao> list = conta.consultarExtrato(de, ate);
        System.out.format("Conta\t\tAgência\t\tCliente\t\tDescrição\t\t\t\t\tValor\n");
        for (int i = 0; i < list.size(); i++) {
            Operacao operacao = list.get(i);
            System.out.format(
                "%04d\t\t%s\t\t%-8s\t%-23s\t\t%.2f\n",
                conta.getNumeroConta(), conta.getNumeroAgencia(), conta.getCliente().getNomeCliente(),operacao.getDescricao(), operacao.getValor());
        }
    }
}