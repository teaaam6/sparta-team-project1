package sparta.spartateamproject1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<List<ItemGetDto.Response>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findAll());
    }
}

