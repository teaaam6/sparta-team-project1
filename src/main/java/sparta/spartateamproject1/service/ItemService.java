package sparta.spartateamproject1.service;

import sparta.spartateamproject1.dto.ItemAddDto;
import sparta.spartateamproject1.dto.ItemGetDto;
import sparta.spartateamproject1.dto.ItemGetPageDto;
import sparta.spartateamproject1.dto.ItemSearchCondition;
import sparta.spartateamproject1.dto.ItemUpdateInfoDto;
import sparta.spartateamproject1.dto.ItemUpdateStockDto;
import sparta.spartateamproject1.dto.ItemUpdateStatusDto;
import sparta.spartateamproject1.dto.LoginSessionAttribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ItemService {

    //전체조회
    Page<ItemGetPageDto.Response> findAll(ItemSearchCondition conditionDto, PageRequest pageRequest);

    //세부조회
    ItemGetDto.Response findOne(Long id);

    // 추가
    ItemGetDto.Response addItem(Long adminId, ItemAddDto.Request req);

    // 정보 수정
    ItemUpdateInfoDto.Response updateInfo(
            LoginSessionAttribute attr, 
            Long itemId, 
            ItemUpdateInfoDto.Request request
    );

    // 재고 수정
    ItemUpdateStockDto.Response updateStock(
            LoginSessionAttribute attr, 
            Long itemId, 
            ItemUpdateStockDto.Request request
    );

    // 상태 수정
    ItemUpdateStatusDto.Response updateStatus(
            LoginSessionAttribute attr, 
            Long itemId, 
            ItemUpdateStatusDto.Request request
    );

    // 삭제
    void delete(
            LoginSessionAttribute attr, 
            Long itemId
    );
}
