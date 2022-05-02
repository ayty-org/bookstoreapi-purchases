package br.com.bookstoreapi.purchases.purchase.v1;

import br.com.bookstoreapi.purchases.book.GetBooksService;
import br.com.bookstoreapi.purchases.client.GetClientService;
import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRecieveDTO;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import br.com.bookstoreapi.purchases.purchase.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
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

    private final GetBooksService getBooksService;
    private final GetClientService getClientService;

    private final ExistPurchaseByClientUuid existPurchaseByClientUuid;
    private final ExistPurchaseByBookUuid existPurchaseByBookUuid;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseResultDTO> list(){
        List<Purchase> purchases = getAllPurchaseService.findAll();
        List<PurchaseResultDTO> purchaseResultDTOS = new LinkedList<>();
        for(Purchase purchase: purchases){
            PurchaseResultDTO purchaseResultDTO = PurchaseResultDTO.from(purchase);
            purchaseResultDTO.setClientDTO(this.getClientService.getClientByUuid(purchase.getClientUuid()));
            purchaseResultDTO.setBookDTOS(this.getBooksService.getBooksByUuid(purchase.getBooksUuid()));
            purchaseResultDTOS.add(purchaseResultDTO);
        }
        return purchaseResultDTOS;
    }

    @GetMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseResultDTO find(@PathVariable UUID purchaseId) throws EntityNotFoundException {
        Purchase purchase = getPurchaseService.getByUuid(purchaseId);
        PurchaseResultDTO purchaseResultDTO = PurchaseResultDTO.from(purchase);
        purchaseResultDTO.setClientDTO(this.getClientService.getClientByUuid(purchase.getClientUuid()));
        purchaseResultDTO.setBookDTOS(this.getBooksService.getBooksByUuid(purchase.getBooksUuid()));
        return purchaseResultDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseResultDTO save(@RequestBody @Valid PurchaseRecieveDTO purchase)
            throws EntityNotFoundException, BookOutOfStockException {
        return postPurchaseService.save(PurchaseRecieveDTO.to(purchase));
    }

    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseResultDTO update(@PathVariable UUID purchaseId, @RequestBody @Valid PurchaseRecieveDTO purchase)
            throws EntityNotFoundException, BookOutOfStockException{
        return putPurchaseService.update(purchaseId, PurchaseRecieveDTO.to(purchase));
    }

    @DeleteMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID purchaseId) throws EntityNotFoundException{
        deletePurchaseService.delete(purchaseId);
    }

    @GetMapping("/existByClient/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public boolean existByClient(@PathVariable UUID uuid){
        return existPurchaseByClientUuid.existsByClientUuid(uuid);
    }

    @GetMapping("/existByBook/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public boolean existByBook(@PathVariable UUID uuid){
        return existPurchaseByBookUuid.existsByBookUuid(uuid);
    }
}
