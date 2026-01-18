package sparta.spartateamproject1.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import jakarta.validation.Valid;

import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.jwt.JWTUtil;
import sparta.spartateamproject1.service.ItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ItemController {
    private final ItemService itemService;
    private final JWTUtil jwtUtil;

    @PostMapping("/items")
    public ResponseEntity<?> addItem(
        @RequestHeader("Authorization") String token,
        @Valid @RequestBody ItemAddDto.Request req
    ) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                itemService.addItem(id, req));
    }

    @GetMapping("/items")
    public ResponseEntity<Page<ItemGetPageDto.Response>> getAll(
        @ModelAttribute ItemSearchCondition conditionDto
    ) {
        // JPA는 0부터 시작
        int pageNumber = conditionDto.getPageNumber() - 1;
        int pageSize = conditionDto.getPageSize();
        boolean asc = conditionDto.isAsc();
        String sortBy = conditionDto.getSortBy();

        PageRequest pageRequest = PageRequest.of(
                pageNumber, pageSize, Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

        return ResponseEntity.status(HttpStatus.OK).body(itemService.findAll(
            conditionDto, pageRequest
        ));
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemGetDto.Response> getOne(
        @PathVariable Long itemId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findOne(itemId));
    }

    @PatchMapping("/items/{itemId}/info")
    public ResponseEntity<ItemUpdateInfoDto.Response> updateInfo(
        @RequestHeader("Authorization") String token,
        @PathVariable Long itemId,
        @Valid @RequestBody ItemUpdateInfoDto.Request req
    ) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateInfo(
            id,
            itemId,
            req
        ));
    }

    @PatchMapping("/items/{itemId}/stock")
    public ResponseEntity<ItemUpdateStockDto.Response> updateStock(
            @RequestHeader("Authorization") String token,
        @PathVariable Long itemId,
        @Valid @RequestBody ItemUpdateStockDto.Request req
    ) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateStock(
            id,
            itemId,
            req
        ));
    }

    @PatchMapping("/items/{itemId}/status")
    public ResponseEntity<ItemUpdateStatusDto.Response> updateStatus(
            @RequestHeader("Authorization") String token,
        @PathVariable Long itemId,
        @Valid @RequestBody ItemUpdateStatusDto.Request req
    ) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateStatus(
            id,
            itemId,
            req
        ));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> delete(
            @RequestHeader("Authorization") String token,
        @PathVariable Long itemId
    ) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        itemService.delete(
            id,
            itemId
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

