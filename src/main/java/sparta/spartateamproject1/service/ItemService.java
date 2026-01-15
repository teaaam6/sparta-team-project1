package sparta.spartateamproject1.service;

import sparta.spartateamproject1.dto.ItemAddDto;
import sparta.spartateamproject1.dto.ItemGetDto;
import sparta.spartateamproject1.dto.ItemUpdateInfoDto;
import sparta.spartateamproject1.dto.ItemUpdateStockDto;
import sparta.spartateamproject1.dto.ItemUpdateStatusDto;
import sparta.spartateamproject1.dto.LoginSessionAttribute;

import java.util.List;

public interface ItemService {

    //전체조회
    List<ItemGetDto.Response> findAll();

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

    // //삭제
    // void delete(Long id, Long adminId);
}
