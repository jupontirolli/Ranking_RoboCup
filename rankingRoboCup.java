import java.sql.SQLOutput;
import java.util.Scanner;

public class rankingRoboCup {

    static void main(String[] args) {

        Scanner e = new Scanner(System.in);

        int qtdEquipes = 0, qtdCombates=0;
        int pontuacaoDesempate = 0,  numEquipeDesempate = 0;
        double notaDesempate =0;

        //solicitar a quantidade de equipes participantes da competição (de 10 a 99
        //fazendo validação e não deixando repetir nº
        System.out.println("---- RANKING ROBOCUP FIAP ----");
        System.out.println("Insira a quantidade de equipes participantes da competição: ");
        qtdEquipes = e.nextInt();

        int [] numEquipes = new int[qtdEquipes];
        double[] notaDesign  = new double[qtdEquipes];

        //número e validações de cada equipe
        for (int i=0;i<qtdEquipes; i++){
            System.out.println("Digite o número da equipe " + (i + 1) + ": ");
            numEquipes[i] = e.nextInt();

            // valida intervalo
            while (numEquipes[i] < 10 || numEquipes[i] > 99) {
                System.out.println("Número inválido! Digite entre 10 e 99: ");
                numEquipes[i] = e.nextInt();
            }

            //garante que não repita nº já cadastrado
            for (int j = 0; j < i; j++) {
                while (numEquipes[j] == numEquipes[i]) {
                    System.out.println("Número já cadastrado! Digite outro: ");
                    numEquipes[i] = e.nextInt();
                }
            }

            //notas design cada equipe
            System.out.println("Digite a nota de design da equipe "
                    + (i+1) + " (0 a 10): ");
            notaDesign[i] = e.nextDouble();
            while (notaDesign[i] < 0 || notaDesign[i]>10){
                System.out.println("Digite um número válido entre 0 e 10: ");
                notaDesign[i] = e.nextDouble();
            }

        }

        //quantidade de combates realizada pelas equipes
        System.out.println("Digite a quantidade de combates realizada pelas equipes: ");
        qtdCombates = e.nextInt();

        char[][] combates = new char[qtdEquipes][qtdCombates];

        for (int i=0;i<qtdEquipes;i++) {
            System.out.println("--- Equipe " + numEquipes[i] + " ---");
            for (int j = 0; j < qtdCombates; j++) {
                System.out.println("Combate " + (j+1) +
                        " - V (vitória), E (empate) ou D (derrota): ");
                combates[i][j] = e.next().toUpperCase().charAt(0);

                while (combates[i][j] != 'V' && combates[i][j] != 'E' && combates[i][j] != 'D') {
                    System.out.println("Inválido! Digite V, E ou D: ");
                    combates[i][j] = e.next().toUpperCase().charAt(0);
                }
            }
        }

        // lista de classificação
        //V: 7p; E: 4p; D: 0p
        int[] pontuacao = new int[qtdEquipes];
        for (int i=0;i<qtdEquipes;i++){
            for (int j = 0; j < qtdCombates; j++){
                if (combates[i][j] == 'V') {
                    pontuacao[i] += 7;
                } else if (combates[i][j] == 'E') {
                    pontuacao[i] += 4;
                }
            }
        }

        //desempate
        for (int i = 0; i < qtdEquipes - 1; i++) {
            for (int j = i + 1; j < qtdEquipes; j++) {

                if (pontuacao[i] < pontuacao[j] ||
                        (pontuacao[i] == pontuacao[j] && notaDesign[i] < notaDesign[j])) {

                    pontuacaoDesempate    = pontuacao[i];
                    pontuacao[i]  = pontuacao[j];
                    pontuacao[j]  = pontuacaoDesempate;

                    notaDesempate = notaDesign[i];
                    notaDesign[i]  = notaDesign[j];
                    notaDesign[j]  = notaDesempate;

                    numEquipeDesempate    = numEquipes[i];
                    numEquipes[i] = numEquipes[j];
                    numEquipes[j] = numEquipeDesempate;
                }
            }
        }


        //saída
        System.out.println("=== RANKING FINAL ===");
        for (int i = 0; i < qtdEquipes; i++) {
            System.out.println((i+1) + "º - Equipe " + numEquipes[i]
                    + " | Pontos: " + pontuacao[i]
                    + " | Design: " + notaDesign[i]);
        }
    }
}
