import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RBTree rbt = new RBTree();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean running = true;
        while (running) {
            System.out.println("\n======= Menu Red-Black Tree =======");
            System.out.println("1. Menu Manual");
            System.out.println("2. Menu Otomatis (generate 8 angka)");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");
            int choice = getInt(scanner);
            switch (choice) {
                case 1:
                    manualMenu(rbt, scanner);
                    break;
                case 2:
                    automaticMenu(rbt, scanner, random);
                    break;
                case 3:
                    System.out.println("Keluar...");
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }

    private static void manualMenu(RBTree rbt, Scanner scanner) {
        rbt.clear();
        boolean run = true;
        while (run) {
            System.out.println("\n--- Menu Manual ---");
            System.out.println("1. Insert key");
            // System.out.println("2. Delete key");
            System.out.println("2. Cek key");
            System.out.println("3. Tampilkan tree & traversals");
            System.out.println("4. Kembali");
            System.out.print("Pilihan: ");
            int c = getInt(scanner);
            switch (c) {
                case 1:
                    System.out.print("Masukkan integer key: ");
                    int k = getInt(scanner);
                    if (rbt.insert(k)) System.out.println("Inserted " + k);
                    else System.out.println("Key " + k + " sudah ada.");
                    rbt.printTree();
                    break;
                // case 2:
                //     System.out.print("Masukkan key yang ingin dihapus: ");
                //     int d = getInt(scanner);
                //     if (rbt.delete(d)) {
                //         System.out.println("Key " + d + " dihapus.");
                //         rbt.printTree();
                //     } else System.out.println("Key " + d + " tidak ditemukan.");
                //     break;
                case 2:
                    System.out.print("Masukkan key yang ingin dicek: ");
                    int q = getInt(scanner);
                    System.out.println(rbt.contains(q) ? "Ditemukan" : "Tidak ditemukan");
                    break;
                case 3:
                    System.out.println("Tree:");
                    rbt.printTree();
                    System.out.println("Pre-order:"); rbt.preOrder();
                    System.out.println("In-order:"); rbt.inOrder();
                    System.out.println("Post-order:"); rbt.postOrder();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void automaticMenu(RBTree rbt, Scanner scanner, Random random) {
        rbt.clear();
        int n = 8;
        int[] keys = new int[n];
        System.out.println("\nGenerate " + n + " random integers (10..99):");
        for (int i = 0; i < n; i++) {
            keys[i] = random.nextInt(90) + 10;
            rbt.insert(keys[i]);
        }
        System.out.print("Keys: ");
        for (int v : keys) System.out.print(v + " ");
        System.out.println("\nTree:");
        rbt.printTree();
        System.out.println("Pre-order:"); rbt.preOrder();
        System.out.println("In-order:"); rbt.inOrder();
        System.out.println("Post-order:"); rbt.postOrder();

        boolean run = true;
        while (run) {
            System.out.println("\n--- Menu Otomatis ---");
            System.out.println("1. Cek key");
            // System.out.println("2. Hapus key");
            System.out.println("2. Tampilkan lagi");
            System.out.println("3. Generate ulang");
            System.out.println("4. Kembali");
            System.out.print("Pilihan: ");
            int c = getInt(scanner);
            switch (c) {
                case 1:
                    System.out.print("Masukkan key: ");
                    int q = getInt(scanner);
                    System.out.println(rbt.contains(q) ? "Ditemukan" : "Tidak ditemukan");
                    break;
                // case 2:
                    // System.out.print("Masukkan key yang ingin dihapus: ");
                    // int d = getInt(scanner);
                    // if (rbt.delete(d)) {
                    //     System.out.println("Key " + d + " dihapus.");
                    //     rbt.printTree();
                    // } else System.out.println("Key " + d + " tidak ditemukan.");
                    // break;
                case 2:
                    rbt.printTree();
                    System.out.println("Pre-order:"); rbt.preOrder();
                    System.out.println("In-order:"); rbt.inOrder();
                    System.out.println("Post-order:"); rbt.postOrder();
                    break;
                case 3:
                    automaticMenu(rbt, scanner, random);
                    run = false;
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static int getInt(Scanner scanner) {
        while (true) {
            try {
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (Exception e) {
                System.out.print("Input tidak valid. Masukkan integer: ");
            }
        }
    }
}
