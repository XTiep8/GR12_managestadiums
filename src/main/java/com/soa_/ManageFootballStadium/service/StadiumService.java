package com.soa_.ManageFootballStadium.service;

import com.soa_.ManageFootballStadium.model.Review;
import com.soa_.ManageFootballStadium.model.Stadium;
import com.soa_.ManageFootballStadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    // Lấy tất cả sân bóng
    public List<Stadium> getStadiums() {
        return (List<Stadium>) stadiumRepository.findAll();
    }

    // Lọc sân bóng theo giá và điểm đánh giá
    public List<Stadium> filterStadiums( Double minPrice, Double maxPrice, Double minRating) {
        Iterable<Stadium> iterableStadiums = stadiumRepository.findAll();
        List<Stadium> stadiums = StreamSupport.stream(iterableStadiums.spliterator(), false)
                .toList();
        return stadiums.stream()
                .filter(stadium ->
                        (stadium.getPrice() >= minPrice && stadium.getPrice() <= maxPrice) &&
                                (stadium.getRating() >= minRating))
                .collect(Collectors.toList());
    }

    // Lấy thông tin chi tiết của sân bóng
    public Stadium getStadiumDetails(Long stadiumId) {
        Optional<Stadium> stadium = stadiumRepository.findById(stadiumId);
        return stadium.orElse(null); // Trả về null nếu không tìm thấy
    }

    // Thêm một sân bóng mới
    public Stadium addStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    // Cập nhật sân bóng
    public Stadium updateStadium(Long stadiumId, Stadium updatedStadium) {
        Optional<Stadium> existingStadium = stadiumRepository.findById(stadiumId);
        if (existingStadium.isPresent()) {
            Stadium stadium = existingStadium.get();
            stadium.setName(updatedStadium.getName());
            stadium.setAddress(updatedStadium.getAddress());
            stadium.setPrice(updatedStadium.getPrice());
            stadium.setRating(updatedStadium.getRating());
            stadium.setDescription(updatedStadium.getDescription());
            stadium.setFacilities(updatedStadium.getFacilities());
            return stadiumRepository.save(stadium);
        }
        return null; // Trả về null nếu không tìm thấy sân
    }

    // Xóa một sân bóng
    public boolean deleteStadium(Long stadiumId) {
        Optional<Stadium> stadium = stadiumRepository.findById(stadiumId);
        if (stadium.isPresent()) {
            stadiumRepository.delete(stadium.get());
            return true;
        }
        return false;
    }

    // Thêm đánh giá cho sân bóng
    public void addReview(Long stadiumId, Review review) {
        Optional<Stadium> stadium = stadiumRepository.findById(stadiumId);
        if (stadium.isPresent()) {
            stadium.get().getReviews().add(review);
            stadiumRepository.save(stadium.get());
        }
    }
}
