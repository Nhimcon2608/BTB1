package app;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);
        String msg = """
            Chương trình quản lý sách
            1. Thêm 1 cuốn sách
            2. Xóa 1 cuốn sách
            3. Thay đổi sách
            4. Xuất thông tin
            5. Tìm sách lập trình
            6. Lấy sách tối đa theo giá
            7. Tìm kiếm theo tác giả
            0. Thoát
            Chọn chức năng:""";

        int chon = 0;
        do {
            System.out.printf(msg);
            chon = x.nextInt();

            switch (chon) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                    System.out.println("Đã thêm sách thành công!");
                }
                case 2 -> {
                    System.out.print("Nhập vào mã sách cần xóa: ");
                    int bookId = x.nextInt();
                    Book find = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst()
                            .orElseThrow();
                    listBook.remove(find);
                    System.out.println("Đã xóa sách thành công");
                }
                case 3 -> {
                    System.out.print("Nhập vào mã sách cần điều chỉnh: ");
                    int bookId = x.nextInt();
                    Book find = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst()
                            .orElseThrow();
                    System.out.println("Nhập thông tin mới cho sách:");
                    find.input();
                    System.out.println("Đã cập nhật sách thành công!");
                }
                case 4 -> {
                    System.out.println("\n Xuất thông tin danh sách ");
                    listBook.forEach(p -> p.output());
                }
                case 5 -> {
                    // Tìm sách có tựa đề chứa "Lập trình" (không phân biệt hoa thường)
                    List<Book> list5 = listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lập trình"))
                            .toList();
                    System.out.println("Sách có tựa đề chứa 'Lập trình':");
                    list5.forEach(Book::output);
                }
                case 6 -> {
                    // Lấy tối đa K cuốn sách có giá <= P
                    System.out.print("Nhập số lượng sách tối đa (K): ");
                    int k = x.nextInt();
                    System.out.print("Nhập giá tối đa (P): ");
                    double p = x.nextDouble();
                    
                    List<Book> list6 = listBook.stream()
                            .filter(book -> book.getPrice() <= p)
                            .limit(k)
                            .toList();
                    System.out.println("Danh sách sách thỏa mãn:");
                    list6.forEach(Book::output);
                }
                case 7 -> {
                    // Tìm sách theo danh sách tác giả
                    System.out.print("Nhập số lượng tác giả cần tìm: ");
                    int n = x.nextInt();
                    x.nextLine(); // clear buffer
                    
                    Set<String> authorSet = new HashSet<>();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Nhập tên tác giả " + (i + 1) + ": ");
                        authorSet.add(x.nextLine().toLowerCase());
                    }
                    
                    List<Book> list7 = listBook.stream()
                            .filter(book -> authorSet.contains(book.getAuthor().toLowerCase()))
                            .toList();
                    System.out.println("Sách của các tác giả đã nhập:");
                    list7.forEach(Book::output);
                }
                case 0 -> System.out.println("Tạm biệt!");
                default -> System.out.println("Chức năng không hợp lệ!");
            }
        } while (chon != 0);
        
        x.close();
    }
}
