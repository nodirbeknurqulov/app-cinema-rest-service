package uz.pdp.appcinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.appcinemarestservice.entity.Row;
import uz.pdp.appcinemarestservice.repository.RowRepository;

import java.util.List;

// Nurkulov Nodirbek 3/17/2022  7:59 AM

@Service
@RequiredArgsConstructor
public class RowService {

    private final RowRepository rowRepository;

    @GetMapping
    public ResponseEntity<List<Row>> getAllRows() {
        return null;
    }
}
