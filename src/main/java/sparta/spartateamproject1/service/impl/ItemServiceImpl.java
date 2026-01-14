package sparta.spartateamproject1.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.repository.ItemRepository;
import sparta.spartateamproject1.service.ItemService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public List<ItemGetDto.Response> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemGetDto.Response> dtos = new ArrayList<>();
        for (Item item : items) {
            dtos.add(ItemGetDto.Response.fromEntity(item));
        }
        return dtos;
    }
}
