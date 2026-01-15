package sparta.spartateamproject1.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.repository.ItemRepository;
import sparta.spartateamproject1.service.ItemService;
import sparta.spartateamproject1.type.Role;
import sparta.spartateamproject1.exception.AdminNotFoundException;
import sparta.spartateamproject1.exception.ForbiddenException;
import sparta.spartateamproject1.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final AdminRepository adminRepository;

    @Override
    @Transactional
    public ItemGetDto.Response addItem(Long adminId, ItemAddDto.Request req) {
        Admin admin = getAdminIfExistsAndActive(adminId);

        // 상품 등록 가는한 admin인지 확인
        if (!(admin.getRole() == Role.SUPER_ADMIN || admin.getRole() == Role.ADMIN)) {
            throw new ForbiddenException("상품 추가는 SUPER_ADMIN 혹은 ADMIN만 가능합니다.");
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

    @Override
    public List<ItemGetDto.Response> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemGetDto.Response> dtos = new ArrayList<>();
        for (Item item : items) {
            dtos.add(ItemGetDto.Response.fromEntity(item));
        }
        return dtos;
    }

    @Override
    public ItemGetDto.Response findOne(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(
            () -> new ItemNotFoundException("존재하지 않는 상품 입니다.")
        );
        return ItemGetDto.Response.fromEntity(item);
    }

    @Override
    @Transactional
    public ItemUpdateInfoDto.Response updateInfo(
        LoginSessionAttribute attr,
        Long itemId,
        ItemUpdateInfoDto.Request req
    ) {
        // 제 생각에 모든 admin이 수정이 가능 할거라 생각하지만,
        // 혹시 admin이 비활성이거나 정지된 admin일 경우를 대비하여
        // 체크를 하는 것이 맞다고 생각합니다.
        getAdminIfExistsAndActive(attr.getId());

        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ItemNotFoundException("존재하지 않는 상품 입니다.")
        );

        item.updateInfo(req.getName(), req.getCategory(), req.getPrice());

        return ItemUpdateInfoDto.Response.fromEntity(item);
    }

    @Override
    @Transactional
    public ItemUpdateStockDto.Response updateStock(
        LoginSessionAttribute attr,
        Long itemId,
        ItemUpdateStockDto.Request req
    ) {
        // TODO: item 재고 관리를 할수있는 관리자는 누구일까요?
        getAdminIfExistsAndActive(attr.getId());

        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ItemNotFoundException("존재하지 않는 상품 입니다.")
        );

        item.updateStock(req.getStock());

        return ItemUpdateStockDto.Response.fromEntity(item);
    }

    @Override
    @Transactional
    public ItemUpdateStatusDto.Response updateStatus(
        LoginSessionAttribute attr,
        Long itemId,
        ItemUpdateStatusDto.Request req
    ) {
        // TODO: item 상태 관리를 할수있는 관리자는 누구일까요?
        getAdminIfExistsAndActive(attr.getId());

        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new ItemNotFoundException("존재하지 않는 상품 입니다.")
        );

        item.updateStatus(req.getStatus());

        return ItemUpdateStatusDto.Response.fromEntity(item);
    }

    private Admin getAdminIfExistsAndActive(Long adminId) {
        // 일단 admin이 있는지 확인한다
        Admin admin = adminRepository.findById(adminId).orElseThrow(
            () -> new AdminNotFoundException("존재하지 않는 관리자입니다.")
        );

        // admin 상태가 ACTIVE인지 확인
        if (admin.getStatus() != AdminStatus.ACTIVE) {
            throw new ForbiddenException("활성된 관리가가 아닙니다.");
        }

        return admin;
    }
}
