package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.payload.ApiResponse;

import uz.pdp.appcinemarestservice.payload.DistributorDto;
import uz.pdp.appcinemarestservice.repository.DistributorRepository;

import java.util.List;
import java.util.Optional;

// Nurkulov Nodirbek 3/9/2022  7:35 AM

@Service
@RequiredArgsConstructor
public class DistributorService {

    private final DistributorRepository distributorRepository;

//    /**
//     * get all movies
//     * @return List
//     */
//    public List<Distributor> getDistributors() {
//        return distributorRepository.findAll();
//    }

    public List<Distributor> getAllDistributors(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Distributor> distributorPage = distributorRepository.findAll(pageable);
        return distributorPage.getContent();
    }

    public Distributor getDistributorById(Integer id) {
        Optional<Distributor> optionalDistributor = distributorRepository.findById(id);
        return optionalDistributor.orElse(null);
    }

    public ApiResponse addDistributor(DistributorDto distributorDto) {
        boolean existsByFullName = distributorRepository.existsByFullName(distributorDto.getFullName());
        if (existsByFullName) {
            return new ApiResponse("This distributor is exist", false);
        }
        Distributor distributor1 = new Distributor();
        distributor1.setFullName(distributorDto.getFullName());
        distributor1.setBio(distributorDto.getBio());
        distributor1.setCreatedAt(distributorDto.getCreatedAt());
        distributor1.setUpdatedAt(distributorDto.getUpdatedAt());
        distributorRepository.save(distributor1);
        return new ApiResponse("Distributor saved", true);
    }

    public ApiResponse editDistributor(Integer id, DistributorDto distributorDto) {
        boolean existsByFullNameAndId = distributorRepository.existsByFullNameAndId(distributorDto.getFullName(), id);
        if (existsByFullNameAndId) {
            return new ApiResponse("this distributor's fullName is already exist", false);
        }
        Optional<Distributor> optionalDistributor = distributorRepository.findById(id);
        if (!optionalDistributor.isPresent()) {
            return new ApiResponse("this distributor does not exist", false);
        }
        Distributor distributor = optionalDistributor.get();
        distributor.setFullName(distributorDto.getFullName());
        distributor.setBio(distributorDto.getBio());
        distributor.setUpdatedAt(distributorDto.getUpdatedAt());
        distributorRepository.save(distributor);
        return new ApiResponse("distributor edited!!!", true);
    }

    public ApiResponse deleteDistributor(Integer id) {
        try {
            distributorRepository.deleteById(id);
            return new ApiResponse("distributor deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }
}
