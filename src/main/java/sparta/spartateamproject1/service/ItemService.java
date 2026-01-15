package sparta.spartateamproject1.service;

import sparta.spartateamproject1.dto.ItemAddDto;
import sparta.spartateamproject1.dto.ItemGetDto;

import java.util.List;

public interface ItemService {

    //전체조회
    List<ItemGetDto.Response> findAll();

    //세부조회
    ItemGetDto.Response findOne(Long id);

    // 추가
    ItemGetDto.Response addItem(Long adminId, ItemAddDto.Request req);

    // //수정
    // CustomerGetDto.Response update(Long id, Long adminId, AdminUpdateDto.Request request);
    // //삭제
    // void delete(Long id, Long adminId);
}
