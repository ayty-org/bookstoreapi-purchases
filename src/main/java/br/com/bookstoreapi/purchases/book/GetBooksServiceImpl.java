package br.com.bookstoreapi.purchases.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetBooksServiceImpl implements GetBooksService {

    private final BookRepository bookRepository;

    @Override
    public List<BookDTO> getBooksByUuid(List<UUID> ids) {
        return ids.stream()
                .map(bookRepository::getBook)
                .collect(Collectors.toList());
    }
}
