package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.exception.BookOutOfStockException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/purchases")
@RestController
public class PurchaseController {

    private final GetAllPurchaseService getAllPurchaseService;
    private final GetPurchaseService getPurchaseService;
    private final SavePurchaseService postPurchaseService;
    private final UpdatePurchaseService putPurchaseService;
    private final DeletePurchaseService deletePurchaseService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDTO> list(){
        return PurchaseDTO.fromAll(getAllPurchaseService.findAll());
    }

    @GetMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDTO find(@PathVariable UUID purchaseId) throws EntityNotFoundException {
        return PurchaseDTO.from(getPurchaseService.getByUuid(purchaseId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDTO save(@RequestBody @Valid PurchaseRecieveDTO purchase)
            throws EntityNotFoundException, BookOutOfStockException {
        return PurchaseDTO.from(postPurchaseService.save(PurchaseRecieveDTO.to(purchase)));
    }

    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDTO update(@PathVariable UUID purchaseId, @RequestBody @Valid PurchaseRecieveDTO purchase)
            throws EntityNotFoundException, BookOutOfStockException{
        return PurchaseDTO.from(putPurchaseService.update(purchaseId, PurchaseRecieveDTO.to(purchase)));
    }

    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID purchaseId) throws EntityNotFoundException{
        deletePurchaseService.delete(purchaseId);
    }
}
