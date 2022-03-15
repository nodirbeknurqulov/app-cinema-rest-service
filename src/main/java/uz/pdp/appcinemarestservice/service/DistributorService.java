package uz.pdp.appcinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.payload.ApiResponse;

import uz.pdp.appcinemarestservice.payload.DistributorDto;
import uz.pdp.appcinemarestservice.repository.DistributorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Nurkulov Nodirbek 3/9/2022  7:35 AM

@Service
public class DistributorService {
    @Autowired
    DistributorRepository distributorRepository;

    public List<Distributor> getDistributors() {
        List<Distributor> distributorList = distributorRepository.findAll();
        return distributorList;
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
