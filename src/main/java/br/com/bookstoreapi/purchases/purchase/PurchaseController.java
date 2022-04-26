package br.com.bookstoreapi.purchases.purchase;

import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.service.*;
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
    public List<PurchaseResultDTO> list(){
        return PurchaseResultDTO.fromAll(getAllPurchaseService.findAll());
    }

    @GetMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseResultDTO find(@PathVariable UUID purchaseId) throws EntityNotFoundException {
        return PurchaseResultDTO.from(getPurchaseService.getByUuid(purchaseId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseResultDTO save(@RequestBody @Valid PurchaseRecieveDTO purchase)
            throws EntityNotFoundException, BookOutOfStockException {
        return PurchaseResultDTO.from(postPurchaseService.save(PurchaseRecieveDTO.to(purchase)));
    }

    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseResultDTO update(@PathVariable UUID purchaseId, @RequestBody @Valid PurchaseRecieveDTO purchase)
            throws EntityNotFoundException, BookOutOfStockException{
        return PurchaseResultDTO.from(putPurchaseService.update(purchaseId, PurchaseRecieveDTO.to(purchase)));
    }

    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID purchaseId) throws EntityNotFoundException{
        deletePurchaseService.delete(purchaseId);
    }
}
