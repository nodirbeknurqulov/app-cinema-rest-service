package uz.pdp.appcinemarestservice.controller;
// Nurkulov Nodirbek 3/17/2022  7:58 AM

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcinemarestservice.entity.Row;
import uz.pdp.appcinemarestservice.service.RowService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/row")
@RequiredArgsConstructor
public class RowController {

    private final RowService rowService;

    @GetMapping
    public HttpEntity<List<Row>> getRows() {
        return rowService.getAllRows();
    }
}
