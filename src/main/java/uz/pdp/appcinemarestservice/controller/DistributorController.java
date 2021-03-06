package uz.pdp.appcinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.AbstractResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.entity.Movie;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.payload.DistributorDto;
import uz.pdp.appcinemarestservice.service.DistributorService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Nurkulov Nodirbek 3/9/2022  7:30 AM

@RestController
@RequestMapping("/api/distributor")
@RequiredArgsConstructor
public class DistributorController {

    private final DistributorService distributorService;

    /**
     * GET ALL DISTRIBUTORS BY PAGE
     *
     * @param page int
     * @param size int
     * @return HttpEntity
     */
    @GetMapping
    public HttpEntity<?> getAllDistributorsByPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Distributor> allDistributors = distributorService.getAllDistributors(page, size);
        return ResponseEntity.ok(allDistributors);
    }

    @GetMapping("/{id}")
    public HttpEntity<Distributor> getDistributorById(@PathVariable Integer id) {
        Distributor distributor = distributorService.getDistributorById(id);
        return ResponseEntity.ok(distributor);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping
    public ResponseEntity addDistributor(@Valid @RequestBody DistributorDto distributorDto) {
        ApiResponse apiResponse = distributorService.addDistributor(distributorDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity editDistributor(@PathVariable Integer id,
                                          @Valid @RequestBody DistributorDto distributorDto) {
        ApiResponse apiResponse = distributorService.editDistributor(id, distributorDto);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDistributor(@PathVariable Integer id) {
        ApiResponse apiResponse = distributorService.deleteDistributor(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}
