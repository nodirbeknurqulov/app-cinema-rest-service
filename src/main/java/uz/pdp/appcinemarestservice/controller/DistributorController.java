package uz.pdp.appcinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import uz.pdp.appcinemarestservice.entity.Distributor;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.service.DistributorService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Nurkulov Nodirbek 3/9/2022  7:30 AM

@RestController
@RequestMapping("/api/distributor")
public class DistributorController {

    @Autowired
    DistributorService distributorService;

    @GetMapping()
    public ResponseEntity<List<Distributor>> getDistributors() {
        List<Distributor> distributors = distributorService.getDistributors();
        return ResponseEntity.ok(distributors);
    }

    @GetMapping("/{id}")
    public HttpEntity<Distributor> getDistributor(@PathVariable Integer id) {
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
    public HttpEntity<ApiResponse> addDistributor(@Valid @RequestBody Distributor distributor) {
        ApiResponse apiResponse = distributorService.addDistributor(distributor);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }



}
