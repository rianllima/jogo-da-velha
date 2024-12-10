import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    String[][] tabuleiro = new String[3][3];
    private int jogadorAtual = 0, parada = 1, contador = 1;
    Scanner scan = new Scanner(System.in);

    public Game() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = " ";
            }
        }
    }

    public void resetarGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = " ";
            }
        }
        //Reseta valores
        jogadorAtual = 0;
        parada = 1;
        contador = 1;
    }

    public void executar() {
        boolean finalizarPartida = false;

        while (!finalizarPartida) {
            do {
                exibirTela();
                jogadorAtual = jogadorAtual == 0 ? 1 : 0; // Ocorre a troca de jogador
                jogadaFeita();
                verificarPartida();
            } while (parada != 0);

            System.out.println("\nIniciar nova partida?\n's' para continuar ou outra tecla para parar.");
            String respostaDoJogador = scan.next();

            if (respostaDoJogador.equals("s")) {
                resetarGame();
            } else {
                finalizarPartida = true;
                scan.close();
            }
        }
    }

    public void exibirTela() {
        System.out.println("\n   0   1   2");
        System.out.println("0  " + tabuleiro[0][0] + " | " + tabuleiro[0][1] + " | " + tabuleiro[0][2]);
        System.out.println("1  " + tabuleiro[1][0] + " | " + tabuleiro[1][1] + " | " + tabuleiro[1][2]);
        System.out.println("2  " + tabuleiro[2][0] + " | " + tabuleiro[2][1] + " | " + tabuleiro[2][2]);
    }

    public void jogadaFeita() {
        int linha, coluna;
        boolean jogadaValida = false;

        while (!jogadaValida) {
            try {
                String quemJoga = (jogadorAtual == 1 ? "X" : "O");
                System.out.println("\nVez do jogador " + quemJoga);
                System.out.print("Digite a Linha: ");
                linha = scan.nextInt();
                System.out.print("Digite a coluna: ");
                coluna = scan.nextInt();

                if (linha >= 0 & linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna].equals(" ")) {
                    tabuleiro[linha][coluna] = quemJoga;
                    jogadaValida = true;
                } else {
                    System.out.println("\nJogada inválida.\nDigite novamente!");
                    exibirTela();
                }
            } catch (InputMismatchException e) {
                System.out.println("\nDigite um número!");
                exibirTela();
                scan.nextLine(); //Limpar entrada
            }
        }
    }

    public void verificarPartida() {
        if (contador < 9) {
            for (int i = 0; i < 3; i++) {
                //analisando as linhas
                if (tabuleiro[i][0].equals(tabuleiro[i][1]) && tabuleiro[i][0].equals(tabuleiro[i][2]) && !tabuleiro[i][0].equals(" ")) {
                    if (jogadorAtual == 0) {
                        exibirTela();
                        System.out.print("\n'O' venceu!");
                        parada = 0;
                    } else {
                        exibirTela();
                        System.out.print("\n'X' venceu!");
                        parada = 0;
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                //analisando as colunas
                if (tabuleiro[0][i].equals(tabuleiro[1][i]) && tabuleiro[0][i].equals(tabuleiro[2][i]) && !tabuleiro[0][i].equals(" ")) {
                    if (jogadorAtual == 0) {
                        exibirTela();
                        System.out.println("\n'O' venceu!");
                        parada = 0;
                    } else {
                        exibirTela();
                        System.out.println("\n'X' venceu!");
                        parada = 0;
                    }
                }
            }
            //analisando as diagonais
            if (tabuleiro[0][0].equals(tabuleiro[1][1]) && tabuleiro[0][0].equals(tabuleiro[2][2]) && !tabuleiro[0][0].equals(" ") ||
                    tabuleiro[2][0].equals(tabuleiro[1][1]) && tabuleiro[2][0].equals(tabuleiro[0][2]) && !tabuleiro[2][0].equals(" ")) {
                if (jogadorAtual == 0) {
                    exibirTela();
                    System.out.println("\n'O' venceu!");
                    parada = 0;
                } else {
                    exibirTela();
                    System.out.println("\n'X' venceu!");
                    parada = 0;
                }
            }
        } else {
            exibirTela();
            System.out.println("\nEmpate!");
            parada = 0;
        }
        contador++;
    }
}


