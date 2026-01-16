package sparta.spartateamproject1.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.jwt.JWTUtil;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.repository.ItemRepository;
import sparta.spartateamproject1.service.ItemService;
import sparta.spartateamproject1.type.Role;
import sparta.spartateamproject1.exception.AdminNotFoundException;
import sparta.spartateamproject1.exception.ForbiddenException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final AdminRepository adminRepository;
    private final JWTUtil jwtUtil;

    @Override
    @Transactional
    public ItemGetDto.Response addItem(String token, ItemAddDto.Request req) {
        // 일단 이 상품을 등록하고 싶은 admin이 있는지 확인한다
        String email = jwtUtil.getEmail(token);

        Admin admin = adminRepository.findByEmail(email).orElseThrow(
            () -> new AdminNotFoundException("존재하지 않는 관리자입니다.")
        );

        // 상품 등록 가는한 admin인지 확인
        if (!(admin.getRole() == Role.SUPER_ADMIN || admin.getRole() == Role.ADMIN)) {
            throw new ForbiddenException("상품은 SUPER_ADMIN 혹은 ADMIN만 가능합니다.");
        }

        Item item = Item.builder()
            .name(req.getName())
            .category(req.getCategory())
            .price(req.getPrice())
            .stock(req.getStock())
            .status(req.getStatus())
            .admin(admin)
            .build();

        itemRepository.save(item);

        return ItemGetDto.Response.fromEntity(item);
    }

    public List<ItemGetDto.Response> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemGetDto.Response> dtos = new ArrayList<>();
        for (Item item : items) {
            dtos.add(ItemGetDto.Response.fromEntity(item));
        }
        return dtos;
    }
}
