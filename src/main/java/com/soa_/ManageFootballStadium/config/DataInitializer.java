//package com.soa_.ManageFootballStadium.config;
//
//import com.soa_.ManageFootballStadium.model.Stadium;
//import com.soa_.ManageFootballStadium.repository.StadiumRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//    private final StadiumRepository stadiumRepository;
//
//    public DataInitializer(StadiumRepository stadiumRepository) {
//        this.stadiumRepository = stadiumRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<Stadium> stadiums = Arrays.asList(
//                new Stadium(null, "SÂN VẬN ĐỘNG A", "123 ĐƯỜNG CHÍNH", 1000.0, 4.5, "NƠI THÍCH HỢP ĐỂ CHƠI THỂ THAO.", "CỎ, ĐÈN"),
//                new Stadium(null, "SÂN VẬN ĐỘNG B", "456 ĐƯỜNG THÔNG", 1500.0, 4.2, "PHÙ HỢP CHO CÁC GIẢI ĐẤU.", "CỎ, BÃI ĐỖ XE"),
//                new Stadium(null, "SÂN VẬN ĐỘNG C", "789 ĐƯỜNG DẪN", 1200.0, 4.8, "TỐT NHẤT TRONG THÀNH PHỐ!", "CỎ, CHỖ NGỒI"),
//                new Stadium(null, "SÂN VẬN ĐỘNG D", "321 ĐƯỜNG PIN", 800.0, 4.0, "THÍCH HỢP CHO CÁC TRẬN ĐẤU ĐỊA PHƯƠNG.", "CỎ, PHÒNG TẮM"),
//                new Stadium(null, "SÂN VẬN ĐỘNG E", "654 ĐƯỜNG LIÊN KẾT", 2000.0, 4.9, "NƠI CỦA CÁC NHÀ VÔ ĐỊCH.", "CỎ, BẢNG TÍNH ĐIỂM"),
//                new Stadium(null, "SÂN VẬN ĐỘNG F", "987 ĐƯỜNG BẠCH ĐÀN", 900.0, 3.8, "DÀNH CHO CÁC TRẬN ĐẤU THƯ GIÃN.", "CỎ, THỨC ĂN"),
//                new Stadium(null, "SÂN VẬN ĐỘNG G", "147 ĐƯỜNG THÔNG", 1100.0, 4.3, "CƠ SỞ VẬT CHẤT ĐƯỢC BẢO TRÌ TỐT.", "CỎ, PHÒNG THAY ĐỒ"),
//                new Stadium(null, "SÂN VẬN ĐỘNG H", "258 ĐƯỜNG TÔM", 1800.0, 4.6, "LÝ TƯỞNG CHO CÁC SỰ KIỆN.", "CỎ NHÂN TẠO, KHU VỰC VIP"),
//                new Stadium(null, "SÂN VẬN ĐỘNG I", "369 ĐƯỜNG THÔNG", 950.0, 4.1, "MÔI TRƯỜNG THÂN THIỆN.", "CỎ, NHÀ VỆ SINH"),
//                new Stadium(null, "SÂN VẬN ĐỘNG J", "852 ĐƯỜNG LIÊN KẾT", 1300.0, 4.4, "THÍCH HỢP CHO CÁC TRẬN ĐẤU TỐI.", "CỎ, GHẾ NGỒI")
//        );
//
//        stadiumRepository.saveAll(stadiums);
//    }
//}