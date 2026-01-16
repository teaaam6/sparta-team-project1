package sparta.spartateamproject1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/items")
    public ResponseEntity<?> addItem(
        @RequestHeader("Authorization") String token,
        @Valid @RequestBody ItemAddDto.Request req
    ) {
        token = token.replace("Bearer ", "").trim();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                itemService.addItem(token, req));
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemGetDto.Response>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findAll());
    }
}

