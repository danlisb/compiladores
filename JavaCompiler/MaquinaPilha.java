import java.io.*;
import java.util.*;

class MaquinaPilha {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java MaquinaPilha arquivoDeEntrada");
            return;
        }

        String arquivoEntrada = args[0];

        try {
            BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
            Stack<Integer> pilha = new Stack<>();
            String linha;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();

                if (linha.startsWith("PUSH")) {
                    // Exemplo: "PUSH 10"
                    String[] partes = linha.split(" ");
                    int valor = Integer.parseInt(partes[1]);
                    pilha.push(valor);
                } else if (linha.equals("SUM")) {
                    int b = pilha.pop();
                    int a = pilha.pop();
                    pilha.push(a + b);
                } else if (linha.equals("SUB")) {
                    int b = pilha.pop();
                    int a = pilha.pop();
                    pilha.push(a - b);
                } else if (linha.equals("MULT")) {
                    int b = pilha.pop();
                    int a = pilha.pop();
                    pilha.push(a * b);
                } else if (linha.equals("DIV")) {
                    int b = pilha.pop();
                    int a = pilha.pop();
                    pilha.push(a / b);
                } else if (linha.equals("PRINT")) {
                    System.out.println("Resultado: " + pilha.pop());
                } else if (linha.isEmpty()) {
                    // ignora linhas em branco
                    continue;
                } else {
                    System.out.println("Instrução desconhecida: " + linha);
                }
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + arquivoEntrada);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (EmptyStackException e) {
            System.out.println("Erro: pilha vazia ao tentar executar operação.");
        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
