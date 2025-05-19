package com.exercicios.questions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RemoverLinhasEmBranco {

    public static void main(String[] args) {
        Path inputPath = Paths.get(Objects.requireNonNull(
                RemoverLinhasEmBranco.class.getClassLoader().getResource("arquivos/input.txt")).getPath()
        );

        Path outputPath = Paths.get("src/main/resources/arquivos/output.txt");

        try {
            List<String> linhas = Files.readAllLines(inputPath)
                    .stream()
                    .filter(linha -> !linha.trim().isEmpty())
                    .collect(Collectors.toList());

            Files.write(outputPath, linhas);

            System.out.println("Processo concluído! Arquivo 'output.txt' gerado corretamente.");

        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao processar os arquivos: " + e.getMessage());
        }
    }
}


