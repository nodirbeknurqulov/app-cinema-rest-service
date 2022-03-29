package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/16/2022  7:42 AM

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appcinemarestservice.entity.Attachment;
import uz.pdp.appcinemarestservice.entity.AttachmentContent;
import uz.pdp.appcinemarestservice.entity.Director;
import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.appcinemarestservice.repository.AttachmentRepository;
import uz.pdp.appcinemarestservice.repository.DirectorRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DirectorService {

    private final DirectorRepository directorRepo;

    /***
     * GET ALL DIRECTORS
     * @param page int
     * @param size int
     * @return List
     */
    public List<Director> getAllDirectors(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Director> directorPage = directorRepo.findAll(pageable);
        return directorPage.getContent();
    }

    public ApiResponse getDirectorById(Integer id) {
        Optional<Director> optionalDirector = directorRepo.findById(id);
        if (optionalDirector.isPresent()) {
            Director director = optionalDirector.get();
            return new ApiResponse("Success", true, director);
        }
        return new ApiResponse("Not found", false, null);
    }

    /**
     * ADD DIRECTOR
     *
     * @param director Director
     * @return Director
     */
    public Director addDirector(Director director) {
        return directorRepo.save(director);
    }

    /**
     * UPDATE DIRECTOR
     *
     * @param id       Integer
     * @param director Director
     * @return ApiResponse
     */
    public ApiResponse updateDirector(Integer id, Director director) {
        Optional<Director> optionalDirector = directorRepo.findById(id);
        if (!optionalDirector.isPresent()) {
            return new ApiResponse("Director not found!");
        }
        Director updatingDirector = new Director();
        updatingDirector.setFullName(director.getFullName());
        updatingDirector.setBio(director.getBio());
        return new ApiResponse("Director updated!");
    }

    /**
     * GET DIRECTOR BY ID
     * @param id Integer
     * @return boolean
     */
    public boolean deleteDirector(Integer id) {
        try {
            directorRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
