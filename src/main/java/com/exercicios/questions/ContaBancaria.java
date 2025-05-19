package com.exercicios.questions;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContaBancaria {
    @Getter
    private final String titular;
    private double saldo;
    private final Lock lock = new ReentrantLock();

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        lock.lock();
        try {
            saldo += valor;
            System.out.println(titular + " depositou R$" + valor + " | Saldo: R$" + saldo);
        } finally {
            lock.unlock();
        }
    }

    public boolean sacar(double valor) {
        lock.lock();
        try {
            if (valor <= saldo) {
                saldo -= valor;
                System.out.println(titular + " sacou R$" + valor + " | Saldo: R$" + saldo);
                return true;
            } else {
                System.out.println("Saldo insuficiente para saque de R$" + valor + " em " + titular);
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean transferirPara(ContaBancaria destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " de " + titular + " para " + destino.getTitular());
            return true;
        }
        return false;
    }
}

class BancoSimulacao {

    public static void main(String[] args) {
        ContaBancaria contaA = new ContaBancaria("Alice", 1000);
        ContaBancaria contaB = new ContaBancaria("Bruno", 500);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Executando operações simultâneas
        executor.execute(() -> contaA.transferirPara(contaB, 200));
        executor.execute(() -> contaB.transferirPara(contaA, 150));
        executor.execute(() -> contaA.depositar(300));

        executor.shutdown();
    }
}

