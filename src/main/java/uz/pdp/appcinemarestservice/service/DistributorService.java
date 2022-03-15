package uz.pdp.appcinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.payload.ApiResponse;

import uz.pdp.appcinemarestservice.repository.DistributorRepository;

import java.util.List;
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

    public ApiResponse addDistributor(Distributor distributor) {
        boolean existsByFullName = distributorRepository.existsByFullName(distributor.getFullName());
        if (existsByFullName) {
            return new ApiResponse("This distributor is exist", false);
        }
        Distributor distributor1 = new Distributor();
        distributor1.setFullName(distributor.getFullName());
        distributor1.setBio(distributor.getBio());
        distributor1.setCreatedAt(distributor.getCreatedAt());
        distributor1.setUpdatedAt(distributor.getUpdatedAt());
        distributorRepository.save(distributor1);
        return new ApiResponse("Distributor saved",true);

    }
}
