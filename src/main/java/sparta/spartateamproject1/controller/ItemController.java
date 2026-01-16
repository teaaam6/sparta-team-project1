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
import sparta.spartateamproject1.service.ItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/items")
    public ResponseEntity<?> addItem(
        @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
        @Valid @RequestBody ItemAddDto.Request req
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                itemService.addItem(loginSessionAttribute.getId(), req));
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
        @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
        @PathVariable Long itemId,
        @Valid @RequestBody ItemUpdateInfoDto.Request req
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateInfo(
            loginSessionAttribute,
            itemId,
            req
        ));
    }

    @PatchMapping("/items/{itemId}/stock")
    public ResponseEntity<ItemUpdateStockDto.Response> updateStock(
        @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
        @PathVariable Long itemId,
        @Valid @RequestBody ItemUpdateStockDto.Request req
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateStock(
            loginSessionAttribute,
            itemId,
            req
        ));
    }

    @PatchMapping("/items/{itemId}/status")
    public ResponseEntity<ItemUpdateStatusDto.Response> updateStatus(
        @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
        @PathVariable Long itemId,
        @Valid @RequestBody ItemUpdateStatusDto.Request req
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateStatus(
            loginSessionAttribute,
            itemId,
            req
        ));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> delete(
        @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
        @PathVariable Long itemId
    ) {
        itemService.delete(
            loginSessionAttribute,
            itemId
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

