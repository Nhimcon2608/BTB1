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
                    // Tìm sách theo từ khóa trong tựa đề (không phân biệt hoa thường)
                    System.out.print("Nhập từ khóa tìm kiếm: ");
                    x.nextLine(); // clear buffer
                    String keyword = x.nextLine().toLowerCase();
                    
                    List<Book> list5 = listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains(keyword))
                            .toList();
                    System.out.println("Kết quả tìm kiếm:");
                    list5.forEach(Book::output);
                }
                case 6 -> {
                    // Hiển thị sách có giá <= giá nhập vào
                    System.out.print("Nhập giá sách tối đa: ");
                    double p = x.nextDouble();
                    
                    List<Book> list6 = listBook.stream()
                            .filter(book -> book.getPrice() <= p)
                            .toList();
                    System.out.println("Danh sách sách có giá <= " + p + ":");
                    list6.forEach(Book::output);
                }
                case 7 -> {
                    // Hiển thị danh sách tác giả và chọn tác giả để xem sách
                    Set<String> authorSet = listBook.stream()
                            .map(Book::getAuthor)
                            .collect(Collectors.toSet());
                    
                    System.out.println("Danh sách tác giả:");
                    List<String> authorList = new ArrayList<>(authorSet);
                    for (int i = 0; i < authorList.size(); i++) {
                        System.out.println((i + 1) + ". " + authorList.get(i));
                    }
                    
                    System.out.print("Chọn số thứ tự tác giả: ");
                    int choice = x.nextInt();
                    
                    if (choice >= 1 && choice <= authorList.size()) {
                        String selectedAuthor = authorList.get(choice - 1);
                        List<Book> list7 = listBook.stream()
                                .filter(book -> book.getAuthor().equals(selectedAuthor))
                                .toList();
                        System.out.println("Sách của tác giả " + selectedAuthor + ":");
                        list7.forEach(Book::output);
                    } else {
                        System.out.println("Lựa chọn không hợp lệ!");
                    }
                }
                case 0 -> System.out.println("Tạm biệt!");
                default -> System.out.println("Chức năng không hợp lệ!");
            }
        } while (chon != 0);
        
        x.close();
    }
}
