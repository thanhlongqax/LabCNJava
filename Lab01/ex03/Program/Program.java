
import java.util.List;

public class Program {
    public static void main(String[] args) {
        
        // 1. khởi tạo danh sách sinh viên
        List<Student> list = StudentUtils.generate();
        System.out.print("in danh sach sinh vien");
        StudentUtils.print(list);
        // 2. sắp xếp theo tên và in ra kết quả
        System.out.print("sap xep theo ten ");
        StudentUtils.sortByName(list);
        StudentUtils.print(list);

        // 3. sắp xếp tăng dần theo điểm trung bình và in ra kết quả
        System.out.print("sap xep theo diem trung bình va in ket qua");
        StudentUtils.sortByAvg(list);
        StudentUtils.print(list);

     
        // @TODO: sắp xếp giảm dần theo tuổi rồi in kết quả
        System.out.print("sap xep theo tuoi giam dan");
        StudentUtils.sortByAgeDescending(list);
        StudentUtils.print(list);



        // @TODO: tính điểm trung bình của toàn bộ danh sách rồi in kết quả
        System.out.println("tinh diem trung binh cua toan bo danh sach");
        System.out.println(StudentUtils.avg(list));


       
        //@TODO: lấy danh sách TÊN học sinh giỏi rồi in kết quả
        System.out.println("in ten hoc sinh gioi");
        System.out.println(StudentUtils.goodStudentName(list));

    }
}
