import java.util.Random;
import java.util.Arrays;

public class T_Java {

    public static int[] gerarAleatorios(int tamanho) {
        Random rand = new Random();
        int[] nums = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            nums[i] = rand.nextInt(1001);
        }

        return nums;
    }

    public static float calcularMedia(int[] nums) {
        float soma = 0;

        for (int num : nums) {
            soma += num;
        }

        return soma / nums.length;
    }

    public static void encontrarMinMax(int[] nums) {
        int menor = nums[0];
        int maior = nums[0];

        for (int num : nums) {
            menor = Math.min(menor, num);
            maior = Math.max(maior, num);
        }

        System.out.printf("O menor valor é: %d e o maior valor é %d\n", menor, maior);
    }

    public static int calcularSomaPares(int[] nums) {
        int soma = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                soma += num;
            }
        }

        return soma;
    }

    public static int contarPrimos(int[] nums) {
        int contadorPrimos = 0;

        for (int num : nums) {
            if (ehPrimo(num)) {
                contadorPrimos++;
            }
        }

        return contadorPrimos;
    }

    public static boolean ehPrimo(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }

        return true;
    }

    public static float calcularMediana(int[] nums) {
        int[] numsOrdenados = Arrays.copyOf(nums, nums.length);
        Arrays.sort(numsOrdenados);

        int meio = numsOrdenados.length / 2;

        if (numsOrdenados.length % 2 == 0) {
            return (numsOrdenados[meio - 1] + numsOrdenados[meio]) / 2.0f;
        } else {
            return numsOrdenados[meio];
        }
    }

    public static void main(String[] args) {
        final int TAMANHO = 1000;
        int[] nums = gerarAleatorios(TAMANHO);

        Thread t1 = new Thread(() -> {
            float media = calcularMedia(nums);
            System.out.printf("A média é: %.2f\n", media);
        });

        Thread t2 = new Thread(() -> {
            encontrarMinMax(nums);
        });

        Thread t3 = new Thread(() -> {
            int somaPares = calcularSomaPares(nums);
            System.out.println("O valor da soma de todos os números pares é: " + somaPares);
        });

        Thread t4 = new Thread(() -> {
            int contadorPrimos = contarPrimos(nums);
            System.out.println("Tiveram " + contadorPrimos + " números primos");
        });

        Thread t5 = new Thread(() -> {
            int[] numsOrdenados = Arrays.copyOf(nums, nums.length);
            Arrays.sort(numsOrdenados);
        });

        Thread t6 = new Thread(() -> {
            float mediana = calcularMediana(nums);
            System.out.printf("O valor da mediana é: %.2f\n", mediana);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
